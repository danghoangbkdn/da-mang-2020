package controller;

import java.util.List;
import java.util.stream.Collectors;

import entity.Student;
import service.StudentService;
import service.StudentServiceImpl;

public class StudentControllerImpl implements StudentController {

	private final StudentService service;
	private final List<Student> students;

	public StudentControllerImpl() {
		service = new StudentServiceImpl();
		students = service.getListStudents();
	}

	@Override
	public Student searchStudent(String id, int year) {
		Student student = new Student();
		for (Student s : getList(year)) {
			if (s.getId().equals(id) && s.getYear() == year) {
				student = s;
			}
		}
		return student;
	}

	@Override
	public List<Student> getListStudentsByYear(int year) {
		List<Student> students = getList(year);
		students.sort((s1, s2) -> s1.getId().compareTo(s2.getId()));
		return students;
	}

	private List<Student> getList(int year) {
		return students.stream().filter(s -> s.getYear() == year).collect(Collectors.toList());
	}
}