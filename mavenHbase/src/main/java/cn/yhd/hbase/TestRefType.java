package cn.yhd.hbase;

public class TestRefType {
	public void test(Wl2LogInfo info){
//		info = new Wl2LogInfo();
//		info = null;
//		main方法中info变量和test方法中info变量是两个变量，不能弄混了。在方法调用是main方法的info变量的值交给了test方法的info
//		当在test中进行了new操作之后，info指向就改变了，所以在main中的info对象并没有被置空
		info.setAdapterCost(2);
		//return info;
	}
	
	public static void main(String[] args) {
		TestRefType test1 = new TestRefType();
		for(int i = 0 ; i < 2 ; i++){
			Wl2LogInfo info = new Wl2LogInfo();
			info.setAdapterCost(1);
			test1.test(info);
			if(info != null){
				System.out.println("info is not null");
			}else{
				System.out.println("info is null");
			}
		}
		
	}
}
