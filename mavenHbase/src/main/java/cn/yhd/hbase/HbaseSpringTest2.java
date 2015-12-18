package cn.yhd.hbase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;

import org.junit.Before;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
/**  
 * @author myx
 * @createTime 2015年5月14日 下午5:21:21  
 * 
 */
public class HbaseSpringTest2 {
	
	HbaseTemplate htemplate = null;
	
	@Before
	public void init() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-hbase.xml");
		BeanFactory factory = (BeanFactory)applicationContext;
		htemplate = (HbaseTemplate)factory.getBean("htemplate");
	}
	
//	public Pagination<Wl2LogInfo> getLogInfoDetailDataPage(int pageNo, int pageSize, Wl2LogInfo wl2LogInfo, Date repStartTime, Date repEndTime) {
//		//String tableName = tableMap.get("mobile_log");
//		String tableName = "mobile_test1";
//		//为分页创建的封装类对象
//		Pagination<Wl2LogInfo> pagination = null;
//		//创建list存放查询结果数据 每个成员map保存，key属性名，value结果值
//		List<Map<String, String>> mapList = new LinkedList<Map<String,String>>();
//		ResultScanner scanner = null;
//		try {
//			if(pageNo < 1){
//				pageNo = 1;
//			}
//			if(pageSize < 0){
//				pageSize = 30;
//			}
//			//设置扫描范围
//			//Scan scan = new Scan();
//			Scan scan = getScanTimeRange(repStartTime, repEndTime);
//			// 缓存1000条数据
//			scan.setCaching(1000);
//			scan.setCacheBlocks(false);
//			scan.setFilter(packageFilters(wl2LogInfo, repStartTime, repEndTime));
//			long a1 = System.currentTimeMillis();
//			List<byte[]> list = getRowKey(tableName,scan);
//			long b1 = System.currentTimeMillis();
//			System.out.println("======================> get row time:" + (b1-a1));
//			System.out.println("======================> get row count:" + list.size());
//			//一般这个查询结果list是直接依次取出就可以了，但是本表中多了一个EventTotal这个统计的Column，如果直接取出会出现异常，进行过滤处理。
//			List<byte[]> rowList = new LinkedList<byte[]>();
//			int totalPage = 0;
//			if(list != null && !list.isEmpty()) {
//				List<List<byte[]>> partition = Lists.partition(list, pageSize);
//				totalPage = partition.size();
//				rowList = partition.get(pageNo-1);
//			}
//			long a2 = System.currentTimeMillis();
//			for (byte[] row : rowList) {
//				String rowKey = toStr(row);
//				Map<String, String> rmap = getRow(tableName, rowKey);
//				mapList.add(rmap);
//			}
//			long b2 = System.currentTimeMillis();
//			System.out.println("======================> get result time:" + (b2-a2));
//			
//			//封装Pagination对象
//			pagination = new Pagination<Wl2LogInfo>();
//			pagination.setPageNo(pageNo);
//			pagination.setPageSize(pageSize);
//			pagination.setTotalCount(list.size());
//			pagination.setTotalPage(totalPage);
//			List<Wl2LogInfo> rows = new ArrayList<Wl2LogInfo>();
//			for (int j = 0; j < mapList.size(); j++) {
//				rows.add(reflectEntity(mapList.get(j)));
//			}
//			pagination.setRows(rows);
//		} catch (Exception e) {
//			System.out.println("VenusWl2LogDAOImpl getLogInfoDetailDataPage() : " + e);
//		}finally {
//			closeScanner(scanner);
//		}
//		return pagination;
//	}
	
	/**
	 * 根据Row key获得行记录
	 * @param table 表名
	 * @param rowKey 行健
	 * @return
	 */
	public Map<String,String> getRow(String table, String rowKey){
		Map<String,String> hmap =  htemplate.get(table, rowKey, new RowMapper<Map<String,String>>() {

			public Map<String,String> mapRow(Result result, int rowNum)
					throws Exception {
				Map<String,String> map = new HashMap<String,String>();
				List<Cell> listCells = result.listCells();
				if(listCells != null && listCells.size()>0){
					for (Cell cell : listCells) {
						String key = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
						String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
						map.put(key, value);
					}
				}
				return map;
			}
			
		});
		return hmap;
	}
	
//	/**
//	 * 
//	 * @param table
//	 * @param scan
//	 * @return
//	 * @throws IOException
//	 */
//	public List<byte[]> getRowKey(String table,Scan scan) throws IOException {
//		List<byte[]> rowList = htemplate.find(table, scan, new RowMapper<byte[]>() {
//			public byte[] mapRow(Result result, int rowNum) throws Exception {
//				byte[] row = result.getRow();
//				return row;
//			}
//		});
//		
//		return rowList;
//	}
//	
//	/* 将map映射到实体中 */
//	public Wl2LogInfo reflectEntity(Map<String, String> map){
//		Iterator<String> iterator = map.keySet().iterator();
//		Wl2LogInfo wl2LogInfo = new Wl2LogInfo();
//		while (iterator.hasNext()) {
//			String key = (String) iterator.next();
//			try {
//				Field field = wl2LogInfo.getClass().getDeclaredField(key);
//				field.setAccessible(true);
//				Type type = field.getGenericType();
//				if(!"null".equals(map.get(key))) {
//					if(type.equals(Long.class)){
//						field.set(wl2LogInfo, Long.parseLong((String)map.get(key)));
//					}else if(type.equals(Integer.class)){
//						field.set(wl2LogInfo, Integer.parseInt((String)map.get(key)));
//					}else if(type.equals(String.class)){
//						field.set(wl2LogInfo, map.get(key));
//					}
//				}
//					
//			} catch (Exception e) {
//				System.out.println("VenusWl2LogDAOImpl reflectEntity : " + e);
//			}
//		}
//		
//		return wl2LogInfo;
//	}
//	
//	
//	private int getTotalPage(int pageSize, int totalCount) {
//		int n = totalCount / pageSize;
//		if (totalCount % pageSize == 0) {
//			return n;
//		} else {
//			return ((int) n) + 1;
//		}
//	}
//	
//	
//	/* 根据Column 添加过滤条件  */
//	public FilterList packageFilters(Wl2LogInfo wl2LogInfo,Date repStartTime,Date repEndTime) {
//		FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
//		String urlPathMethod = wl2LogInfo.getUrlPathMethod();
//		if(StringUtils.isNotBlank(urlPathMethod)) {
//			Filter filter1 = new RowFilter(CompareOp.EQUAL,new RegexStringComparator("\\w+(_"+urlPathMethod+"_)\\w+"));
//			filterList.addFilter(filter1);
//		}
//		
//		String traderName = wl2LogInfo.getTraderName();
//		if(StringUtils.isNotBlank(traderName)) {
//			Filter filter2 = new RowFilter(CompareOp.EQUAL,new RegexStringComparator("\\w+(_"+traderName+"_)\\w+"));
//			filterList.addFilter(filter2);
//		}
//		
//		String userId = wl2LogInfo.getUserId();
//		if(StringUtils.isNotBlank(userId)) {
//			Filter filter3 = new RowFilter(CompareOp.EQUAL,new RegexStringComparator("\\w+(_"+userId+"_)\\w+"));
//			filterList.addFilter(filter3);
//		}
//		
//		Integer errorType = wl2LogInfo.getErrorType();
//		if(errorType != null){
//			String errorTypeStr = Integer.toString(errorType); 
//			if(StringUtils.isNotBlank(errorTypeStr)) {
//				Filter filter4 = new RowFilter(CompareOp.EQUAL,new RegexStringComparator("\\w+(_"+errorTypeStr+"_)\\w+"));
//				filterList.addFilter(filter4);
//			}
//		}
//		
//		
//		String clientAppVersion = wl2LogInfo.getClientAppVersion();
//		if(StringUtils.isNotBlank(clientAppVersion)) {
//			Filter filter5 = new RowFilter(CompareOp.EQUAL,new RegexStringComparator("\\w+_"+clientAppVersion+"_\\w+"));
//			filterList.addFilter(filter5);
//		}
//		
//		
//		if(repStartTime !=null && repEndTime !=null) {
//			String startTime = Long.toString(repStartTime.getTime());
//			String endTime = Long.toString(repEndTime.getTime());
//			if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
//				Filter filter6 = new SingleColumnValueFilter(getBytes("message"), getBytes("serverStartTime"),
//						CompareOp.GREATER_OR_EQUAL, getBytes(startTime));
//				filterList.addFilter(filter6);
//				Filter filter7 = new SingleColumnValueFilter(getBytes("message"), getBytes("serverStartTime"),
//						CompareOp.LESS_OR_EQUAL, getBytes(endTime));
//				filterList.addFilter(filter7);
//			}
//		}
//		
//		return filterList;
//	}
//	
//	private Scan getScanTimeRange(Date startTime, Date endTime) {
//		Scan scan = new Scan();
//		if(startTime != null && endTime != null) {
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(startTime);
//			cal.set(Calendar.MINUTE, 0);
//			cal.set(Calendar.SECOND, 0);
//			cal.set(Calendar.MILLISECOND, 0);
//			long minStamp = cal.getTimeInMillis();
//			
//			String startRow = Long.toString(startTime.getTime())+'#';
//			
//			cal.setTime(endTime);
//			cal.add(Calendar.HOUR_OF_DAY, 1);
//			cal.set(Calendar.MINUTE, 0);
//			cal.set(Calendar.SECOND, 0);
//			cal.set(Calendar.MILLISECOND, 0);
//			long maxStamp = cal.getTimeInMillis();
//			
//			String stopRow = Long.toString(endTime.getTime())+':';
//			
//			try {
//				scan.setTimeRange(minStamp, maxStamp);
//				scan.setStartRow(getBytes(startRow));
//				scan.setStopRow(getBytes(stopRow));
//			} catch (IOException e) {
//				System.out.println("VenusWl2LogDAOImpl getScanTimeRange: " + e);
//			}
//		}
//		return scan;
//	}
//	
//	private static void closeScanner(ResultScanner scanner) {
//		if (scanner != null)
//			scanner.close();
////	}
//	
//	public byte[] getBytes(String str) {
//		if(str == null)
//			str = "";
//		return Bytes.toBytes(str);
//	}
//	
//	public String toStr(byte[] bt){
//		return Bytes.toString(bt);
//	} 
//	
//	@Test
//	public void	test1() {
//		Wl2LogInfo wl2LogInfo = new Wl2LogInfo();
//		wl2LogInfo.setErrorType(0);
//		wl2LogInfo.setUrlPathMethod("/baobao/getBabyInfo");
//		long a = System.currentTimeMillis();
//		Pagination<Wl2LogInfo> pagination = getLogInfoDetailDataPage(0, 30, wl2LogInfo, new Date(1432091402000L), new Date(1432091702000L));
//		long b = System.currentTimeMillis();
//		System.out.println("====================================> " + (b-a));
//		System.out.println(pagination);
//	}
}
