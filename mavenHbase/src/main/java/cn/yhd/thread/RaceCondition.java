package cn.yhd.thread;

public class RaceCondition {
	private static volatile boolean done = false;
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i = 0 ;
				while(!done){
					i++;
				}
				System.out.println("done : ");
				
			}
		}).start();
		
		System.out.println("OS : "+System.getProperty("os.name"));
		Thread.sleep(1000);
		done = true;
		System.out.println("dont set value is true");
	}
}
