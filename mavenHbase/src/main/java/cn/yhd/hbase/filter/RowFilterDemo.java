package cn.yhd.hbase.filter;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

/**
 * rowkey过滤器
 * @author zhuchao1
 *
 */
public class RowFilterDemo {
	public static void main(String[] args) throws Exception, ZooKeeperConnectionException, IOException {
		//初始化配置数据
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-hbase.xml");
		BeanFactory factory = (BeanFactory)applicationContext;
		HbaseTemplate htemplate = (HbaseTemplate)factory.getBean("htemplate");
		Configuration conf = htemplate.getConfiguration();
		//字节过滤器，判断rowkey字节大小做比较
		BinaryComparator rowComparator = new BinaryComparator(Bytes.toBytes("00000000-4159-5740-c7ff-e9390033c587"));
		Filter filter = new RowFilter(CompareOp.LESS_OR_EQUAL, rowComparator);
		Scan scan = new Scan();
		scan.setFilter(filter);
		scan.addColumn(Bytes.toBytes("mobileInfo"), Bytes.toBytes("IMEI"));
		scan.addColumn(Bytes.toBytes("mobileInfo"), Bytes.toBytes("traderName"));
		HTable table = new HTable(conf, "mobileDevicecodeAbRecord");
		ResultScanner reslult = table.getScanner(scan);
		for(Iterator<Result> it = reslult.iterator() ; it.hasNext() ; ){
			Result result = it.next();
			System.out.println("IMEI:"+Bytes.toString(result.getValue(Bytes.toBytes("mobileInfo"), Bytes.toBytes("IMEI")))+" traderName:"+Bytes.toString(result.getValue(Bytes.toBytes("mobileInfo"), Bytes.toBytes("traderName"))));
		}
		System.out.println("------------正则过滤器------------");
		//正则过滤器
		RegexStringComparator regex = new RegexStringComparator(".*587");
		Filter regexFilter = new RowFilter(CompareOp.EQUAL, regex);
		Scan regexScan = new Scan();
		regexScan.setFilter(regexFilter);
		regexScan.addColumn(Bytes.toBytes("mobileInfo"), Bytes.toBytes("IMEI"));
		regexScan.addColumn(Bytes.toBytes("mobileInfo"), Bytes.toBytes("traderName"));
		reslult = table.getScanner(regexScan);
		for(Iterator<Result> it = reslult.iterator() ; it.hasNext() ; ){
			Result result = it.next();
			System.out.println("rowkey:"+Bytes.toString(result.getRow())+" IMEI:"+Bytes.toString(result.getValue(Bytes.toBytes("mobileInfo"), Bytes.toBytes("IMEI")))+" traderName:"+Bytes.toString(result.getValue(Bytes.toBytes("mobileInfo"), Bytes.toBytes("traderName"))));
		}
		
		System.out.println("------------子字符串过滤器------------");
		SubstringComparator substring = new SubstringComparator("5d6f");
		Filter substringFilter = new RowFilter(CompareOp.EQUAL, substring);
		Scan substringScan = new Scan();
		substringScan.setFilter(substringFilter);
		reslult = table.getScanner(substringScan);
		for(Iterator<Result> it = reslult.iterator() ; it.hasNext() ; ){
			Result result = it.next();
			System.out.println("rowkey:"+Bytes.toString(result.getRow())+" IMEI:"+Bytes.toString(result.getValue(Bytes.toBytes("mobileInfo"), Bytes.toBytes("IMEI")))+" traderName:"+Bytes.toString(result.getValue(Bytes.toBytes("mobileInfo"), Bytes.toBytes("traderName"))));
		}
		
		
	}
}
