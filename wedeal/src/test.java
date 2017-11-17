import wedeal.bean.*;

public class test {
	
	
	static void test() {
		String id = "wodbs";
		int result = UserDBBean.getinstance().registerCheck(id);
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		test();
	}
}
