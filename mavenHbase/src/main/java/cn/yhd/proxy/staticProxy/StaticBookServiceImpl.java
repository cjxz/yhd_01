package cn.yhd.proxy.staticProxy;

public class StaticBookServiceImpl implements StaticBookService {

	@Override
	public void addBook() {
		System.out.println("增加一条记录");
	}
	
}
