package AssignmentDay2Part1.methods;

import java.util.List;

import AssignmentDay2Part1.module.Address;
import AssignmentDay2Part1.module.Student;

public class DeleteStudent {

	public static List<Student> studentList = Assignment2_1.studentList;
	public static List<Address> addList = Assignment2_1.addList;

	public static void deleteStudent(Integer studId) {
		if(studentList.stream().filter(s->s.getId()==studId).count()>0) {
//			Optional<Student> deleteStudent = studentList.stream().filter(s->s.getId()==studId).findFirst();
//			Student stud = deleteStudent.get();
//			studentList.remove(stud);
			
			studentList.removeIf(s->s.getId()==studId);
			
//			studentList = studentList.stream().filter(s->s.getId()!=studId).collect(Collectors.toList());

//			studentList.forEach(System.out::println);
			
//			addList = addList.stream().filter(a->a.getStudent_id()!=studId).collect(Collectors.toList());
			
			addList.removeIf(a->a.getStudent_id()==studId);
			System.out.println("Student Deleted from studentList and AddressList");
			return;
		}
		System.out.println("Student Not deleted");
	}
}
