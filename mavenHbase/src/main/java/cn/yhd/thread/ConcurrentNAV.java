package cn.yhd.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ConcurrentNAV extends AbstractNAV {

	@Override
	public double computeNetAssetValue(final Map<String, Integer> stocks) throws Exception {
		//获得计算机的核数
		final int numberOfCores = Runtime.getRuntime().availableProcessors();
		//阻塞系数
		final double blockingCoefficient = 0.9;
		//线程池大小
		final int poolSize = (int)(numberOfCores/(1-blockingCoefficient));
		System.out.println("线程池大小："+poolSize);
		
		final List<Callable<Double>> partitions = new ArrayList<Callable<Double>>();
		for(final String ticker : stocks.keySet()){
			Callable<Double> callable = new Callable<Double>() {
				@Override
				public Double call() throws Exception {
					return stocks.get(ticker)*YahooFinance.getPrice(ticker);
				}
			};
			partitions.add(callable);
		}
		
		final ExecutorService executorPool = Executors.newFixedThreadPool(poolSize);
		
		final List<Future<Double>> valueOfStocks = executorPool.invokeAll(partitions,10000,TimeUnit.SECONDS);
		
		double netAssetValue = 0.0;
		for(final Future<Double> valueOfAstock : valueOfStocks){
			netAssetValue += valueOfAstock.get();
		}
		
		executorPool.shutdown();
		return netAssetValue;
	}
	
}
