package cn.yhd.proxy.staticProxy;

public class StaticBookProxy {
	private StaticBookService staticBookService;
	public StaticBookProxy(StaticBookService staticBookService){
		this.staticBookService = staticBookService;
	}
	
	public void addBook(){
		System.out.println("proxy 做一些事情1");
		staticBookService.addBook();
		System.out.println("proxy 做一些事情2");
	}
}
