package assignmentDay2Part2.methods;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import assignmentDay2Part2.module.Employee;

public class EmployeeDataHandler {

	List<Employee> empList = Task2.employeeList;

//	1	How many male and female employees are there in the organization?																								
	public static void countMaleAndFeamle(List<Employee> employees) {
		Long maleCount = employees.stream().filter(s -> s.getGender().equals("Male")).count();
		Long femaleCount = employees.stream().filter(s -> s.getGender().equals("Female")).count();

		System.out.println("Total Male Count: " + maleCount);
		System.out.println("Total Female Count: " + femaleCount);

	}

//	2	Print the name of all departments in the organization?
//	static List<String> allDept = new ArrayList<String>();
	public static Set<String> getDistinctDepartment(List<Employee> employees) {
		return employees.stream().map(Employee::getDepartment).collect(Collectors.toSet());
	}

//	3	What is the average age of male and female employees?																								

	public static void getAvgMaleFemale(List<Employee> employees) {

		Double maleAge = employees.stream().filter(e->e.getGender().equals("Male")).collect(Collectors.averagingLong(Employee::getAge));
		Double femaleAge = employees.stream().filter(e->e.getGender().equals("Female")).collect(Collectors.averagingLong(Employee::getAge));
		
		System.out.println("Male Average Age: "+maleAge);
		System.out.println("Female Average Age: "+femaleAge);

	}

//	4	Get the details of highest paid employee in the organization?	
	public static Employee highestEmployee(List<Employee> employees) {
		return employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);
	}

//	5	Get the names of all employees who have joined after 2015?																								
	public static List<Employee> getEmpOf2015(List<Employee> employees) {
		List<Employee> employee = employees.stream().filter(e -> e.getYearOfJoining() == 2015)
				.collect(Collectors.toList());

		return employee;
	}

//	6	Count the number of employees in each department?																								
	public static Map<String, Integer> getEmpCountInDept(List<Employee> employees) {
//		Set<String> allDept = getDistinctDepartment(employees);
//		Map<String, Integer> mapping = allDept.stream().collect(Collectors.toMap(dept -> dept, dept -> 0));
		Map<String, Integer> mapping = new HashMap<String, Integer>();

		employees.stream().forEach(e -> {
			mapping.put(e.getDepartment(), mapping.getOrDefault(e.getDepartment(), 0) + 1);
		});

		return mapping;
	}

//	7	What is the average salary of each department?		
	public static Map<String, Double> getDeptAvgSalary(List<Employee> employees) {
		Map<String, Double> mapping = new HashMap<String, Double>();
		mapping = employees.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		return mapping;
	}

//	8	Get the details of youngest male employee in the product development department?		
	public static Employee getYoungestEmployee(List<Employee> employees) {
		return employees.stream().min(Comparator.comparingInt(Employee::getAge)).orElse(null);
	}

//	9	How many male and female employees are there in the sales and marketing team?		
	public static void getEmpByGenderInSalesMarketing(List<Employee> employees) {
		Map<String, Long> salesDept = employees.stream().filter(e -> e.getDepartment().equals("Sales And Marketing"))
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(salesDept);
	}

//	10	What is the average salary of male and female employees?																								
	public static Map<String, Double> getAvgSalOfMaleAndFemale(List<Employee> employees) {
		return employees.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
	}

//	11	List down the names of all employees in each department?		
	public static Map<String, List<String>> getEmpByDept(List<Employee> employees) {
		return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
				Collectors.mapping(Employee::getName, Collectors.toList())));
	}

//	12	What is the average salary and total salary of the whole organization?																								
	public static void getAvgAndTotalSal(List<Employee> employees) {
		Double avgSal = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
		Double totalSal = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println("Average Salary =" + avgSal);
		System.out.println("Total Salary =" + totalSal);
	}

//	13	Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years?																								
	public static void getYoungEmployee(List<Employee> employees) {
		List<Employee> young25 = new LinkedList<Employee>();
		List<Employee> old25 = new LinkedList<Employee>();
		employees.stream().forEach(e -> {
			if (e.getAge() <= 25)
				young25.add(e);
			else
				old25.add(e);
		});
		young25.forEach(System.out::println);
		System.out.println();
		old25.forEach(System.out::println);
	}

//	14	Who is the oldest employee in the organization? What is his age and which department he belongs to?																								
	public static Employee getOldestEmployee(List<Employee> employees) {
		return employees.stream().max(Comparator.comparingInt(Employee::getAge)).orElse(null);
	}

}
