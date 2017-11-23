import java.util.Arrays;

public class GenericStack<E> {
	//memberList 
	
	private E[] list = (E[])new Object[2];
	
	private int size = 0;

	private E[] tmpList;
	
	

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize() {
		this.size = size + 1;
	}
	
	public E peek() {
		return list[getSize()-1];
	}
	
	public void push(E o) {
		
		if (this.getSize() < list.length) {
		list[getSize()] = o;
		setSize();
		}
		
		else {
			tmpList = (E[])new Object[size*2];
			int i = 0;
			for (E tmp :list) {
				tmpList[i] = tmp;
				i++;
			}
			
			tmpList[size]= o;
			list = tmpList;
		setSize();
		}
	}
	
	public E pop() {
		E o = list[size-1];
		list[size-1] = null;
		size--;
		return o;
	}
	
	public boolean isEmpty() {
		if (size == 0)
			return true;
		else 
			return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenericStack [list=" + Arrays.toString(list) + ", size=" + size + "]";
	}
	
}
