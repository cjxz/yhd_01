package cn.yhd.hbase;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;

/**  
 * @author myx
 * @createTime 2015年3月31日 下午3:26:21  
 * 
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:applicationContext-hbase.xml")
public class HbaseSpringTest {
	HbaseTemplate htemplate = null;
//	private HBaseAdmin hBaseAdmin;
	@Before
	public void init() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-hbase.xml");
		BeanFactory factory = (BeanFactory)applicationContext;
		htemplate = (HbaseTemplate)factory.getBean("htemplate");
//		Configuration conf = htemplate.getConfiguration();
//		HBaseAdmin hBaseAdmin = null;
//		String tableName = "documentContentTable";
//		try{
//			
//			hBaseAdmin = new HBaseAdmin(conf);
//			if (hBaseAdmin.tableExists(tableName)) {// 如果存在要创建的表，那么先删除，再创建  
//				hBaseAdmin.disableTable(tableName);  
//				hBaseAdmin.deleteTable(tableName);  
//				System.out.println(tableName + " is exist,detele....");  
//			}  
//			HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);  
//			tableDescriptor.addFamily(new HColumnDescriptor("documentContent"));  
//			hBaseAdmin.createTable(tableDescriptor);  
//		}catch(Exception e){
//			
//		}
		
	}
	
	//@Test
	public void testSaveHbaseTable(){
//		htemplate.
//		htemplate.execute("operatorLogTable", new TableCallback<String>() {
//			public String doInTable(HTableInterface table) throws Throwable {
//				String key = "1234567888889fff0";
//				byte[] rowkey = key.getBytes();  
//                Put put = new Put(rowkey);  
//                put.add("operatorLogContent".getBytes(), null, "aaa".getBytes());// 本行数据的第一列  
//                table.put(put);  
//				return key;
//			}
//		});
	}
	@Test
	public void aaa(){
		String hbaseKey = "2015-09-24 11:09:07_水电装修.txt";
		Scan scan = new Scan();
		byte[] result = htemplate.get("documentContentTable", hbaseKey, new RowMapper<byte[]>() {
			@Override
			public byte[] mapRow(Result result, int arg1) throws Exception {
				List<Cell> cells = result.listCells();
				if(cells != null && cells.size() > 0){
					for(Cell cell : cells){
						String t = Bytes.toString(ArrayUtils.subarray(cell.getValueArray(), cell.getValueOffset(), cell.getValueOffset()+cell.getValueLength()));
						System.out.println(t);
						System.out.println(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
						System.out.println(Bytes.toString(cell.getValueArray()));
						ByteBuffer bb = ByteBuffer.wrap(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
						System.out.println(Bytes.toString(bb.array()));
						System.out.println(cell.getValueLength());
						System.out.println(cell.getValueOffset());
						System.out.println(cell.getRowArray());
						System.out.println(cell.getRowLength());
						System.out.println(cell.getRowOffset());
					}
				}
				return null;
			}
			
		});
		
	}
//	
//	
//	@Test
//	public void testGetRowKey(){
//		
//		Map<String,Object> hmap =  htemplate.get("test5", "/mobileservice/logReporting_null_androidSystem_1430965141593_0_4.0.7_1430965763215", new RowMapper<Map<String,Object>>() {
//
//			public Map<String,Object> mapRow(Result result, int rowNum)
//					throws Exception {
//				Map<String,Object> map = new HashMap<String,Object>();
//				List<Cell> listCells = result.listCells();
//				if(listCells != null && listCells.size()>0){
//					for (Cell cell : listCells) {
//						//System.out.println(Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(), cell.getFamilyLength()));
//						String key = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
//						String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
//						map.put(key, value);
//					}
//				}
//				return map;
//			}
//			
//		});
//		showValue(hmap);
//	}
//	
//	@Test
//	public void testQueryByCondition() {
//		Scan scan = new Scan();
//		Filter filter1 = new SingleColumnValueFilter(Bytes.toBytes("message"), Bytes.toBytes("serverStartTime"), CompareOp.GREATER_OR_EQUAL, Bytes.toBytes("1429131600000"));
//		Filter filter2 = new SingleColumnValueFilter(Bytes.toBytes("message"), Bytes.toBytes("serverStartTime"), CompareOp.LESS_OR_EQUAL, Bytes.toBytes("1429135200000"));
//		Filter filter3 = new SingleColumnValueFilter(Bytes.toBytes("message"), Bytes.toBytes("urlPathMethod"), CompareOp.EQUAL, Bytes.toBytes("/global/getNavigationCategory"));
//		scan.setFilter(filter1);
//		scan.setFilter(filter2);
//		scan.setFilter(filter3);
//		//scan.setStartRow(Bytes.toBytes(1));
//		//scan.setStopRow(Bytes.toBytes(1000));
//		List<Map<String,Object>> hlist = htemplate.find("test5", scan, new RowMapper<Map<String,Object>>() {
//			public Map<String,Object> mapRow(Result result, int rowNum) throws Exception {
//				Map<String,Object> map = new HashMap<String,Object>();
//				List<Cell> listCells = result.listCells();
//				if(listCells != null && listCells.size()>0){
//					for (Cell cell : listCells) {
//						//System.out.println(Bytes.toString(cell.getRowArray(),cell.getRowOffset(),cell.getRowLength()));
//						String key = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
//						String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
//						map.put(key, value);
//					}
//				}
//				return map;
//			}
//		});
//		//一般这个查询结果list是直接依次取出就可以了，但是本表中多了一个EventTotal这个统计的Column，如果直接取出会出现异常，进行过滤处理。
//		for (int i = 0; i < hlist.size()-1; i++) {
//			showValue(hlist.get(i));
//			
//		}
//		
//	}
//	
//	
//	
//	@Test
//	public void testGetRowKeyByCondition() {
//		Scan scan = new Scan();
//		Filter filter = new SingleColumnValueFilter(Bytes.toBytes("message"), Bytes.toBytes("errorType"), CompareOp.EQUAL, Bytes.toBytes("0"));
//		scan.setFilter(filter);
//		//scan.setStartRow(Bytes.toBytes(1));
//		//scan.setStopRow(Bytes.toBytes(1000));
//		List<byte[]> rowList = htemplate.find("test5", scan, new RowMapper<byte[]>() {
//			public byte[] mapRow(Result result, int rowNum) throws Exception {
//				byte[] row = result.getRow();
//				System.out.println(Bytes.toString(row));
//				return row;
//			}
//		});
//		//一般这个查询结果list是直接依次取出就可以了，但是本表中多了一个EventTotal这个统计的Column，如果直接取出会出现异常，进行过滤处理。
//		for (byte[] row : rowList) {
//			getRow(Bytes.toString(row));
//		}
//	}
//	
//	@Test
//	public void testGetByCondition() throws IOException {
//		Scan scan = new Scan();
//		Filter filter1 = new RowFilter(CompareOp.EQUAL,new RegexStringComparator("^/baobao/getBabyInfo+_\\w+"));
//		scan.setFilter(filter1);
//		scan.setTimeRange(1431568800000L, 1431572400000L);
//		long a = System.currentTimeMillis();
//		List<byte[]> rowList = htemplate.find("mobile_test", scan, new RowMapper<byte[]>() {
//			public byte[] mapRow(Result result, int rowNum) throws Exception {
//				byte[] row = result.getRow();
//				//System.out.println(Bytes.toString(row));
//				return row;
//			}
//		});
//		long b = System.currentTimeMillis();
//		System.out.println("====================" + (b-a));
//		//一般这个查询结果list是直接依次取出就可以了，但是本表中多了一个EventTotal这个统计的Column，如果直接取出会出现异常，进行过滤处理。
//		/*for (byte[] row : rowList) {
//			getRow(Bytes.toString(row));
//		}*/
//		//System.out.println(rowList.size());
//		int startPage = 0;
//		int endPage = 30;
//		List<byte[]> rowList1 = new LinkedList<byte[]>();
//		for (int i = startPage; i < endPage; i++) {
//		//	if (i >=  && i < ) {
//				String row = Bytes.toString(rowList.get(i));
//				if(!row.equals("totalEvents")) {
//					rowList1.add(Bytes.toBytes(row));
//					
//				}
//		//	}
//		}
//		System.out.println(rowList1.size());
//		/*for (byte[] row : rowList1) {
//			
//			getRow(Bytes.toString(row));
//		}*/
//	}
//	
//	public void getRow(String rowKey){
//		Map<String,Object> hmap =  htemplate.get("mobile_test", rowKey, new RowMapper<Map<String,Object>>() {
//
//			public Map<String,Object> mapRow(Result result, int rowNum)
//					throws Exception {
//				Map<String,Object> map = new HashMap<String,Object>();
//				List<Cell> listCells = result.listCells();
//				if(listCells != null && listCells.size()>0){
//					for (Cell cell : listCells) {
//						String key = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
//						String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
//						map.put(key, value);
//					}
//				}
//				return map;
//			}
//			
//		});
//		showValue(hmap);
//	}
//	
	public void showValue(Map<String, Object> hmap){
		Iterator<String> iterator = hmap.keySet().iterator();
		Wl2LogInfo wl2LogInfo = new Wl2LogInfo();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			try {
				//System.out.println(key);
				Field field = wl2LogInfo.getClass().getDeclaredField(key);
				field.setAccessible(true);
				Type type = field.getGenericType();
				if(type.equals(Long.class)){
					field.set(wl2LogInfo, Long.parseLong((String)hmap.get(key)));
				}else if(type.equals(int.class)){
					field.set(wl2LogInfo, Integer.parseInt((String)hmap.get(key)));
				}else if(type.equals(String.class)){
					field.set(wl2LogInfo, hmap.get(key));
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(wl2LogInfo);
	}
}
