package cn.yhd.proxy.staticProxy;

public class StaticProxyDemo {
	public static void main(String[] args) {
		StaticBookService staticBookService = new StaticBookServiceImpl();
		StaticBookProxy proxy = new StaticBookProxy(staticBookService);
		proxy.addBook();
	}
}
