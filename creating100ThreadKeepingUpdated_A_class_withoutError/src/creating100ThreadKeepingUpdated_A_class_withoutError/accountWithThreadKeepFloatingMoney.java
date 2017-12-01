package creating100ThreadKeepingUpdated_A_class_withoutError;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

//import AccountWithSyncUsingLock.AddAPennyTask;

public class accountWithThreadKeepFloatingMoney {
	static Account account = new Account();
	
	public static void main(String[] args) {
		
		
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i=0;i<100;i++) {
			// CREAT A THREAD POOL
//			executor.execute(new threadToAddMoney());
			 executor.execute(new threadToAddMoney());
		}
		
		executor.shutdown();
		
		if(account.balance != 100) {
			System.out.println("GET error: " + account.balance);
		}else
		System.out.println(account.balance);
		
		
	}
	
	public static class Account{
		private int balance;
		private static Lock lock = new ReentrantLock();
		public void deposite(){
			
			lock.lock();
			balance++;
			lock.unlock();
			
		}
	}
	
	
	public static class threadToAddMoney implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			account.deposite();
		}

	}
	
}
