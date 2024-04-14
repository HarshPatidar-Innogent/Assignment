package AssignmentDay2Part1.methods;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import AssignmentDay2Part1.module.Classes;
import AssignmentDay2Part1.module.Student;

public class ClassStudent {
	
	public static Integer getStudentClass(Character class_name){
		Classes classId = null;
		List<Classes> classList = Assignment2_1.classList;
		for(Classes clas:classList) {
			if(clas.getName().equals(class_name))
				classId = clas;
		}
		return classId.getId();
	}
	
	public static List<Student> getStudentByClass(List<Student> studList, Character class_name, Character gender, Integer age, String city, Integer pincode){
		int classId = getStudentClass(class_name);
		
		List<Student> student = new LinkedList<Student>();
		
//		for(Student stud:studList) {
//			if(stud.getClass_id().equals(classId)) {
//				student.add(stud);
//			}
//		}
		
		student = studList.stream().filter(s->s.getClass_id().equals(classId)).collect(Collectors.toList());
		
		List<Student> students = Filters.getStudentByGenderAgeCityPincode(student, gender, age, city, pincode);
		
		return students;
	}
}
