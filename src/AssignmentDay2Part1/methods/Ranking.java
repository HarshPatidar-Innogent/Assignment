package AssignmentDay2Part1.methods;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.inno.module.Address;
import com.inno.module.Classes;
import com.inno.module.Student;

public class Ranking {
	
	public static List<Student> studentList = Assignment2_1.studentList;
	public static List<Classes> classList = Assignment2_1.classList;
	public static List<Address> addList = Assignment2_1.addList;
	
	public static List<Student> ranking(List<Student> studentList) {
		List<Student> student = studentList.stream().sorted((a, b) -> (b.getMarks()).compareTo(a.getMarks()))
				.collect(Collectors.toList());

		int studentLen = student.size();
		for (int i = 0; i < studentLen; i++) {
			if (i == 0)
				student.get(i).setStatus("First");
			else if (i == 1)
				student.get(i).setStatus("Second");
			else if (i == 2)
				student.get(i).setStatus("Third");
			else if (student.get(i).getMarks() > 50)
				student.get(i).setStatus("Pass");
			else
				student.get(i).setStatus("Fail");

		}

		System.out.println();
		return studentList;
	}


	public static List<Student> getFailStudent(List<Student> studList, List<Address> addList, Character gender,
			Integer age, Integer class_id, String city, Integer pincode) {
		List<Student> studentList = ranking(studList);
		 new LinkedList<Student>();

		
		List<Student> failedStudent = studentList.stream().filter(s->s.getStatus().equals("fail")).collect(Collectors.toList());

		List<Integer> stud_id = addList.stream()
			    .filter(add -> add.getCity().equals(city) && add.getPin_code() == pincode)
			    .map(Address::getStudent_id)
			    .collect(Collectors.toList());
		
		List<Student> student = failedStudent.stream().filter(s->stud_id.contains(s.getId())).collect(Collectors.toList());

		if (student.isEmpty()) {
			return Collections.emptyList();
		} else {
			return student;
		}

	}
	
	public static List<Student> failStudentByAge(Integer age){
		
		List<Student> students = studentList.stream().filter(s->s.getAge()>20).peek(s->s.setStatus("Fail")).collect(Collectors.toList());
//		students.forEach(System.out::println);
		return students;
	}
}
