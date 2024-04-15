package day1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ListAndMap {
	
	public static void listImpl() {
		List<String> shoppingList = new LinkedList<String>();
		shoppingList.add("Bourbone");
		shoppingList.add("Lays");
		System.out.println(shoppingList);
	}
	
	public static void mapImpl() {
		Map<String, Integer> addBook = new HashMap<String, Integer>();
		addBook.put("Inno", 12345467);
		addBook.put("gent", 8976638);
		System.out.println(addBook);
	}
	
	public static void main(String[] args) {
		listImpl();
		mapImpl();
	}
}
