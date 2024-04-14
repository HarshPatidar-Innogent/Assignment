package AssignmentDay2Part1.methods;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import AssignmentDay2Part1.module.Student;

public class FailedStudent {
	public static List<Student> getFailStudent(List<Student> studList, Character gender, Integer age, Integer class_id,
			String city, Integer pincode) {
		List<Student> rankedStudent = Ranking.ranking(studList);
		List<Student> FailStudent = Filters.studFailFilterByStatusAgeClassGender(rankedStudent, age, class_id,
				gender);
		List<Integer> stud_id = Filters.getStudentIdByAddress(FailStudent, city, pincode);

		List<Student> student = new LinkedList<Student>();


		student = FailStudent.stream().filter(s->stud_id.contains(s.getId())).collect(Collectors.toList());


		if (student.isEmpty()) {
			return Collections.emptyList();
		} else {
			return student;
		}

	}

}
