package max_in_array_using_thread;

public class using_thread_to_cocurrently_count_the_sequence_array {
	
	public static void main(String args[]) {
		
		int[] sum = new int[2]; 
		int[] arr = new int[100];
		int totalSum = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        
        System.out.println("no in the array");
        
        for(int j :arr) {
        System.out.print(j + " ");
        }
        //find out the last index of the array
        double theLastIndex = arr.length - 1 ;
        
        //no of cluster to deliver the job
        int cluster = 2; 
        //using double so that we could get back the last index after multiply with no of cluster 
        double range = theLastIndex/cluster;
        System.out.println("");
        Thread t1 = new Thread(new threadFindingMax(arr,0,(int)range,sum,0));
        Thread t2 = new Thread(new threadFindingMax(arr,(int)(range + 1),(int)(range*2),sum,1));
        t1.start();
        t2.start();
        
        
       
		for ( int i : sum) {
        	totalSum =totalSum + i;
        }
        System.out.println("totalSum by using two thread" + totalSum);
	}
	
}

class threadFindingMax implements Runnable {
	
	int[] arr; 
	int firstIndex;
	int lastIndex;
	int[] sum;
	int no;
	
	threadFindingMax(int[] arr,int firstIndex,int lastIndex,int[] sum,int no){
		this.firstIndex = firstIndex;
		this.lastIndex = lastIndex;
		this.arr = arr; 
		this.sum = sum;
		this.no = no;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(sumOfTheArray());
		
	}
	private int sumOfTheArray() {
		// TODO Auto-generated method stub
		int result = 0;
		for (int i= firstIndex; i<=lastIndex ; i++) {
			result = result+ arr[i];
			
		}
		this.sum[no] = result;
		return result;
		
	}
	
}
