package cn.yhd.thread;

import java.io.IOException;
import java.util.Map;

public class SequentialNAV extends AbstractNAV{

	@Override
	public double computeNetAssetValue(Map<String, Integer> stocks) {
		double netAssetValue = 0.0;
		for(String ticker : stocks.keySet()){
			try {
				netAssetValue += stocks.get(ticker)*YahooFinance.getPrice(ticker);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

}
