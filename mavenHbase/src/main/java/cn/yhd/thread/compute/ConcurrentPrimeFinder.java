package cn.yhd.thread.compute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ConcurrentPrimeFinder extends AbstractPrimeFinder {
	
	private final int poolSize;
	private final int numberOfParts;
	
	public ConcurrentPrimeFinder(final int poolSize,final int numberOfParts){
		this.poolSize = poolSize;
		this.numberOfParts = numberOfParts;
	}
	
	@Override
	public int countPrimes(int number) {
		int count = 0 ;
		try{
			final List<Callable<Integer>> partitions = new ArrayList<Callable<Integer>>();
			
			//拆分子任务
			final int chunksPerPartition = number / numberOfParts;
			for(int i = 0 ; i < numberOfParts ; i++){
				final int lower = (i * chunksPerPartition) + 1;
				final int upper = (i == numberOfParts - 1) ? number : lower + chunksPerPartition -1 ;
				partitions.add(new Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
						return countPrimesInRange(lower, upper);
					}
				});
			}
			
			final ExecutorService executorPool = Executors.newFixedThreadPool(poolSize);
			
			final List<Future<Integer>> resultFromParts = executorPool.invokeAll(partitions, 10000, TimeUnit.SECONDS);
			
			executorPool.shutdown();
			
			for(final Future<Integer> result : resultFromParts){
				count += result.get();
			}
		}catch(Exception e){
			
		}
		return count;
	}
	
	public static void main(String[] args) {
		new ConcurrentPrimeFinder(4, 10).timeAndCompute(10000000);
//		线程池：4 任务拆分4
//		number:10000000 numberOfPrimes:665025
//		time : 9.165917919
		
//		线程池：4  任务拆分：10
//		number:10000000 numberOfPrimes:665025
//		time : 9.113199954
	}
}
