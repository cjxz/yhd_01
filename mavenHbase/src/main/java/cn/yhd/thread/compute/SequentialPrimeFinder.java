package cn.yhd.thread.compute;

public class SequentialPrimeFinder extends AbstractPrimeFinder {

	@Override
	public int countPrimes(final int number) {
		return countPrimesInRange(1, number);
	}
	
	public static void main(String[] args) {
		new SequentialPrimeFinder().timeAndCompute(10000000);
//		number:10000000 numberOfPrimes:665025
//		time : 19.843616585
	}

}
