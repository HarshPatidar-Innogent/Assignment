package AssignmentDay2Part1.methods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import AssignmentDay2Part1.module.Address;
import AssignmentDay2Part1.module.Classes;
import AssignmentDay2Part1.module.Student;

public class Assignment2_1 {
	public static List<Student> studentList = new LinkedList<Student>();
	public static List<Classes> classList = new LinkedList<Classes>();
	public static List<Address> addList = new LinkedList<Address>();

//	public static <T> void upload(File file, T student) throws IOException {
	public static void uploadStudent(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";

		// skipping the first line
		line = br.readLine();

		while ((line = br.readLine()) != null) {
			String[] studentArr = line.split(",");
			Student student1 = new Student();
			student1.setId(Integer.parseInt(studentArr[0]));
			student1.setName(studentArr[1]);
			student1.setClass_id(Integer.parseInt(studentArr[2]));
			student1.setMarks(Integer.parseInt(studentArr[3]));
			student1.setGender(studentArr[4].toCharArray()[0]);
			student1.setAge(Integer.parseInt(studentArr[5]));
			studentList.add(student1);
		}

//		studentList.stream().forEach(System.out::println);

		br.close();
	}

	public static void uploadClass(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));

		// skipping the first line
		String line = br.readLine();

		while ((line = br.readLine()) != null) {
			String[] classArr = line.split(",");
			Classes classes = new Classes();
			classes.setId(Integer.parseInt(classArr[0]));
			classes.setName(classArr[1].toCharArray()[0]);
			classList.add(classes);
		}
//		classList.stream().forEach(System.out::println);
		br.close();
	}

	public static void uploadAddress(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));

		// skipping the first line
		String line = br.readLine();

		while ((line = br.readLine()) != null) {
			String[] addArr = line.split(",");
			Address add = new Address();
			add.setId(Integer.parseInt(addArr[0]));
			add.setPin_code(Integer.parseInt(addArr[1]));
			add.setCity(addArr[2]);
			add.setStudent_id(Integer.parseInt(addArr[3]));
			addList.add(add);
		}

//		addList.stream().forEach(System.out::println);
		br.close();
	}

	public static List<Student> getByGender(char gender, int s, int e) {
		List<Student> student = new LinkedList<Student>();
		int count = 0;
		for (Student stud : studentList) {
			if (stud.getGender() == gender) {
				count++;
				if (count > s && count < e)
					student.add(stud);
			}
		}
		return student;
	}

	public static List<Student> getByPinCode(Long pincode, Character gender, Integer age, Integer classes) {
		List<Student> student = new LinkedList<Student>();
		List<Integer> studPin = new LinkedList<Integer>();

		for (Address add : addList) {
			if (add.getPin_code() == pincode) {
				studPin.add(add.getStudent_id());
			}
		}

		for (Student stud : studentList) {
			if (studPin.contains(stud.getId())) {
				if (stud.getGender() == gender && stud.getAge() < age && stud.getClass_id() == classes)
					student.add(stud);
			}
		}

		return student;
	}

	public static List<Student> getByCity(String city, Character gender, Integer age, Integer classes) {
		List<Student> student = new LinkedList<Student>();
		List<Integer> cit = new LinkedList<Integer>();

		for (Address add : addList) {
			if (add.getCity().equals(city)) {
				cit.add(add.getStudent_id());
			}
		}

		for (Student stud : studentList) {
			if (cit.contains(stud.getId())) {
				if (stud.getGender() == gender && stud.getAge() < age && stud.getClass_id() == classes)
					student.add(stud);
			}
		}
		return student;
	}

	public static String deleteStudent(Integer student_id) {
		int originalSize = studentList.size();

		studentList = studentList.stream().filter(s -> s.getId() != student_id).collect(Collectors.toList());
		addList = addList.stream().filter(s -> s.getStudent_id() != student_id).collect(Collectors.toList());

		int newSize = studentList.size();

		if (newSize != originalSize) {
			return "Student Deleted";
		} else
			return "Student Does Not Exist";
	}

	static int zeroId = 0;

	public static String deleteClass() {
		Map<Integer, Integer> studentsByClassId = new HashMap<Integer, Integer>();

		classList.stream().forEach(c -> {
			studentsByClassId.put(c.getId(), 0);
		});

		studentList.stream().forEach(c -> {
			studentsByClassId.put(c.getClass_id(), studentsByClassId.getOrDefault(c.getClass_id(), 0) + 1);
		});

		for (java.util.Map.Entry<Integer, Integer> e : studentsByClassId.entrySet()) {
			if (e.getValue().equals(0))
				zeroId = e.getKey();
		}
		classList = classList.stream().filter(c -> c.getId() != zeroId).collect(Collectors.toList());
		if (zeroId != 0)
			return "Class Deleted";
		return "CLass Not Deleted";

	}

//	like : read female students first 1-9
	public static List<Student> getSortedStudentByOrder(List<Student> studList, Character gender, int start, int end,
			String sortBy, boolean ReverseOrder) {
		Comparator<Student> comparator = getComparator(sortBy, ReverseOrder);
		return studList.stream().filter(s -> s.getGender().equals(gender)).sorted(comparator).skip(start - 1).limit(end)
				.collect(Collectors.toList());
	}

	public static Comparator<Student> getComparator(String sortBy, boolean reverseOrder) {
		Comparator<Student> comparator = null;

		switch (sortBy) {
		case "name":
			comparator = Comparator.comparing(Student::getName);
			break;
		case "marks":
			comparator = Comparator.comparing(Student::getMarks).reversed();
			break;
		case "class_id":
			comparator = Comparator.comparing(Student::getClass_id);
			break;
		default:
			comparator = Comparator.comparing(Student::getId);
		}
		return comparator;
	}

	public static void main(String[] args) throws IOException {
//		upload the csv data files
		uploadStudent(new File("C:\\Users\\Harsh Patidar\\Desktop\\Student.csv"));
		uploadClass(new File("C:\\Users\\Harsh Patidar\\Desktop\\Class.csv"));
		uploadAddress(new File("C:\\Users\\Harsh Patidar\\Desktop\\Address.csv"));

//		getByGender('F', 4, 9).forEach(System.out::println);

//		1.Find all students of pincode X(ex X = 482002). I can pass different filters like gender, age, class
//		getByPinCode(452002l, 'F', 45, 1).forEach(System.out::println);

//		2.Find all students of city ex X = Indore. I can pass different filters like gender, age, class
//		getByCity("indore", 'F', 45, 1).forEach(System.out::println);

//		3.marks < 50 failed else passed
//		Give ranks to highest mark achievers.
//		Highest marks - First
//		Third Highest marks - third
//		Rest of all pass / fail
//		List<Student> studRank = Ranking.ranking(studentList);
//		studRank.forEach(System.out::println);

//		4.Get the passed students. I can pass different filters like gender, age, class, city, pincode
//		List<Student> passedStudent = PassedStudent.getPassStudent(studentList, 'F', 35, 1, "indore", 452002);
//		passedStudent.forEach(System.out::println);

//		5.Get the failed students. I can pass different filters like gender, age, class, city, pincode
//		List<Student> failedStudent = FailedStudent.getFailStudent(studentList, 'F', 35, 3, "mumbai", 482002);
//		failedStudent.forEach(System.out::println);

//		6.Find all student of class X (ex X = A).  I can pass different filters like gender, age, class, city, pincode
//		List<Student> classStudent = ClassStudent.getStudentByClass(studentList, 'A', 'F', 35, "indore", 452002);
//		classStudent.forEach(System.out::println);

//		7.It should fail if student record is having age > 20.
//		List<Student> failedStudentByAge = Ranking.failStudentByAge(20);
//		studentList.forEach(System.out::println);

//		8.I should be able to delete student. After that it should delete the respective obj from Address & Student.
//		System.out.println(deleteStudent(1));
//		studentList.forEach(System.out::println);
//		addList.forEach(System.out::println);

//		9.I should be able to delete student. After that it should delete the respective obj from Address & Student.

//		10	If there is no student remaining in that class. Class should also be deleted.
//		System.out.println(deleteClass());
//		classList.forEach(System.out::println);

//		11	I should be able to read paginated students.
//		like : read female students first 1-9
//		like : read female students first 7-8 order by name	
//		like : read female students first 1-5 order by marks
//		like : read female students first 9-50 order by marks
//		getSortedStudentByOrder(studentList, 'F', 1, 4, "marks", false).forEach(System.out::println);;
		
//		List<Student> studList, Character gender, int start, int end, String sortBy, boolean ReverseOrder

	}

}
