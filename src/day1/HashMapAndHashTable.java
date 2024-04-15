package day1;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HashMapAndHashTable {
	
	public static void hashMapImpl() {
		Map<String, Integer> empMap = new HashMap<String, Integer>();
		empMap.put("Inno", 1001);
		empMap.put("gent", 1002);
		System.out.println(empMap);
	}
	
	public static void hashTableImpl() {
		Map<String, Integer> userAuth = new Hashtable<String, Integer>();
		userAuth.put("inno@gmail.com", 123);
		userAuth.put("gent@gmail.com", 321);
		System.out.println(userAuth);
	}
	
	public static void main(String[] args) {
		hashMapImpl();
		hashTableImpl();
	}
}
