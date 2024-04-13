package AssignmentDay2Part1.methods;

import java.util.LinkedList;
import java.util.List;

import com.inno.module.Classes;
import com.inno.module.Student;

public class ClassStudent {
	
	public static int getStudentClass(Character class_name){
		int classId = 0;
		List<Classes> classList = Assignment2_1.classList;
		for(Classes clas:classList) {
			if(clas.getName().equals(class_name))
				classId = clas.getId();
		}
		return classId;
	}
	
	public static List<Student> getStudentByClass(List<Student> studList, Character class_name, Character gender, Integer age, String city, Integer pincode){
		int classId = getStudentClass(class_name);
		
		List<Student> student = new LinkedList<Student>();
		
		for(Student stud:studList) {
			if(stud.getClass_id().equals(classId)) {
				student.add(stud);
			}
		}
		List<Student> students = Filters.getStudentByGenderAgeCityPincode(student, gender, age, city, pincode);
		
		return students;
	}
}
