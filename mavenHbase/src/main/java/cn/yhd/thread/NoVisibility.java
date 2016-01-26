package cn.yhd.thread;

public class NoVisibility {
	private static boolean ready;
	private static int number;
	private static class ReadyThread extends Thread{
		public void run(){
			while(!ready){
				Thread.yield();
			}
			System.out.println(number);
		}
	}
	public static void main(String[] args) {
		new ReadyThread().start();
		ready = true;
		number = 42;
	}
}
