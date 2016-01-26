package cn.yhd.thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public abstract class AbstractNAV {
	public static Map<String,Integer> readTickers() throws Exception, IOException{
		final BufferedReader reader = new BufferedReader(new FileReader("stocks.txt"));
		final Map<String,Integer> stocks =new HashMap<String, Integer>();
		String stockInfo = null;
		while((stockInfo = reader.readLine()) != null){
			final String[] stockInfoData = stockInfo.split(",");
			final String stockTicker = stockInfoData[0];
			final Integer quantity = Integer.valueOf(stockInfoData[1]);  
			
			stocks.put(stockTicker, quantity);
		}
		return stocks;
	}
	
	public abstract double computeNetAssetValue(final Map<String,Integer> stocks) throws Exception;
	
	public void timeAndComputeValue() throws IOException, Exception{
		final long start = System.nanoTime();
		
		final Map<String,Integer> stocks = readTickers();
		final double nav = computeNetAssetValue(stocks);
		
		final long end = System.nanoTime();
		
		final String value = new DecimalFormat("$##,##0.00").format(nav);
		
		System.out.println("总资产："+value);
		System.out.println("总耗时："+(end-start)/1.0e9);
		
	}
	
}
