package day1;

public class Singleton {
	public static Singleton single;
	
	private Singleton() {
		
	}
	
	public static Singleton getInstance() {
		if(single==null) {
			single = new Singleton();
		}
		return single;
	}
}
