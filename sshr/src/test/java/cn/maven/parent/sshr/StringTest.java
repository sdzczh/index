package cn.maven.parent.sshr;

public class StringTest {
	public static void main(String[] args) {
		upOrdown();
	}
	
	private static void upOrdown() {
		String str = "searlistTest";
		System.out.println(str.toLowerCase());
		System.out.println(str.toUpperCase());
	}

	public static void contains(){
		String str = "searlistTest";
		System.out.println(str.contains("list"));
	}
}
