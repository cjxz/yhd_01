package cn.yhd.thread.compute;

public abstract class AbstractPrimeFinder {
	public boolean isPrime(final int number){
		if(number <= 1)
			return false;
		for(int i = 2 ; i < Math.sqrt(number) ; i++){
			if(number % i == 0)
				return false;
		}
		return true;
	}
	
	public int countPrimesInRange(final int lower,final int upper){
		int total = 0 ; 
		for(int i = lower ; i <= upper ; i++){
			if(isPrime(i))
				total++;
		}
		return total;
	}
	
	public abstract int countPrimes(final int number);
	
	public void timeAndCompute(final int number){
		final long start = System.nanoTime();
		final long numberOfPrimes = countPrimes(number);
		final long end = System.nanoTime();
		System.out.println("number:"+number+" numberOfPrimes:"+numberOfPrimes);
		System.out.println("time : "+(end - start)/1.0e9);
	}
}
