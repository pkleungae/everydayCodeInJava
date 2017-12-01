package threadCooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class threadCooperation {
		//why static is so neccesary //////////////////////////////////////desmond
		static Account account = new Account(10);
		//inner class account
		private static class Account{
			int balance;
			private static Lock lock = new ReentrantLock();
			private  static Condition newMoney = lock.newCondition(); 
			public void addMoney(int amount) {
				lock.lock();
				balance+=amount;
				newMoney.signalAll();
				lock.unlock();
			}
			public void withDraw(int amount) {
				lock.lock();
				while(balance<amount) {
					try {
						newMoney.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
				balance-=amount;
				lock.unlock();
				
				
			}
			
			public Account(int balance) {
				super();
				this.balance = balance;
			}
		}
		
		//create two task for invoking addMonet and withdraw
		
		public static class addTask implements Runnable {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(account.balance < 70) {
				account.addMoney(10);
				}
				
			}
		}
		//why static is so neccessary//////////////////////////desmond ,if ommit static , cant work in line 72 73 
		public static class withdrawTask implements Runnable {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						account.withDraw(50);
					}
				}
		
		
		public static void main(String args[]) {
		//create thread to execute 
		ExecutorService	executor = Executors.newFixedThreadPool(2);
		executor.execute(new addTask());
		executor.execute(new withdrawTask());
		executor.shutdown();
		
		System.out.println("balance now is : "  + account.balance);
			
		}

		
}
