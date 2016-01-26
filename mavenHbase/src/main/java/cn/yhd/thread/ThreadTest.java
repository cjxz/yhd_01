package cn.yhd.thread;

public class ThreadTest extends Thread{
	private static int i = 0;
	private int j = 0;
	private static int k = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub 
		for(int n = 0 ; n < 1000 ; n++){
			//System.out.print("i:"+getI());
			System.out.print(this.getName()+"   j:"+getJ1());
			//System.out.print(this.getName()+"   k:" +getK());
		}
	}
	
	public int getI(){
		return i++;
	}
	public synchronized int getJ(){
		return j++;
	}
	public int getJ1(){
		return j++;
	}
	
	public static int getK(){
		System.out.print("sdfsfsf");
		return k++;
	}
	
	public static void main(String[] args) {
		for(int m = 0 ; m < 5 ; m++){
			ThreadTest t = new ThreadTest();
			t.start();
		}
	}
}

