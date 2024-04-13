package AssignmentDay2Part1.methods;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.inno.module.Address;
import com.inno.module.Classes;
import com.inno.module.Student;

public class Filters {

	public static List<Student> studentList = Assignment2_1.studentList;
	public static List<Classes> classList = Assignment2_1.classList;
	public static List<Address> addList = Assignment2_1.addList;

	// filter student by status, age, class and gender
	public static List<Student> studFilterByStatusAgeClassGender(List<Student> studentList, Integer age,
			Integer class_id, Character gender) {
		List<Student> student = new LinkedList<Student>();

		student = studentList.stream()
				.filter(s -> s.getAge() > 50 && s.getClass_id() == class_id && s.getGender() == gender)
				.collect(Collectors.toList());

		return student;
	}

	public static List<Student> studFailFilterByStatusAgeClassGender(List<Student> studentList, Integer age,
			Integer class_id, Character gender) {
		List<Student> student = new LinkedList<Student>();

		student = studentList.stream().filter(stud -> stud.getMarks() <= 50 && stud.getAge() < age
				&& stud.getClass_id() == class_id && stud.getGender() == gender).collect(Collectors.toList());
		return student;
	}

	// filter student id by address
	public static List<Integer> getStudentIdByAddress(List<Student> stud, String city, Integer pincode) {
		List<Integer> student = new LinkedList<Integer>();

		student = addList.stream().filter(add -> add.getCity().equals(city) && add.getPin_code() == pincode)
				.map(Address::getStudent_id).collect(Collectors.toList());
		
		return student;
	}

	public static List<Student> getStudentByGenderAgeCityPincode(List<Student> studList, Character gender, Integer age,
			String city, Integer pincode) {
		List<Integer> studentIdByCityPincode = getStudentIdByAddress(studList, city, pincode);

		List<Student> students = new LinkedList<Student>();
		
		students = studList.stream().filter(student->student.getGender().equals(gender) && student.getAge() < age
					&& studentIdByCityPincode.contains(student.getId())).collect(Collectors.toList());
		
		return students;
	}
	
	
	static List<Integer> student_id = new LinkedList<Integer>();

	public static List<Student> filterByPincode(List<Student> studList, Integer pincode) {
		List<Student> students = new LinkedList<Student>();
		
		student_id = addList.stream().filter(add->add.getPin_code()==pincode).map(Address::getId).toList();
		
		students = studList.stream().filter(s->student_id.contains(s.getId())).collect(Collectors.toList());;
		return students;
	}

	public static List<Student> filterByGender(List<Student> studList, Character gender) {
		List<Student> students = new LinkedList<Student>();
		
		students = studList.stream().filter(s->s.getGender().equals(gender)).collect(Collectors.toList());
		
		return students;
	}

	public static List<Student> filterByAge(List<Student> studList, Integer age) {
		List<Student> students = new LinkedList<Student>();

		students = studList.stream().filter(s->s.getAge()>age).collect(Collectors.toList());

		return students;
	}

	public static List<Student> filterByCity(List<Student> studList, String city) {
		List<Integer> stud_id = new LinkedList<Integer>();
		List<Student> students = new LinkedList<Student>();
		
		student_id = addList.stream().filter(add->add.getCity().equals(city)).map(Address::getId).collect(Collectors.toList());
		
		students = studList.stream().filter(s->stud_id.contains(s.getId())).collect(Collectors.toList());
		
		return students;

	}
	
	public static Integer getClassIdByName(List<Classes> classes, Character class_name){
		Integer class_id = classes.stream().filter(c->c.getName()==class_name).map(Classes::getId).findFirst().orElse(null);
		return class_id;
	}

}
