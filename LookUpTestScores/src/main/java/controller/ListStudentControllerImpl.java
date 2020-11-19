package controller;

import java.util.List;
import java.util.stream.Collectors;

import entity.Student;
import service.ListStudentService;
import service.ListStudentServiceImpl;

public class ListStudentControllerImpl implements ListStudentController {

	private final ListStudentService service;
	private final List<Student> students;

	public ListStudentControllerImpl() {
		service = new ListStudentServiceImpl();
		students = service.getListStudents();
	}

	@Override
	public Student searchStudent(String id, int year) {
		Student student = new Student();
		for (Student s : getListStudentByYear(year)) {
			if (s.getId().equals(id) && s.getYear() == year) {
				student = s;
			}
		}
		return student;
	}

	@Override
	public Object[][] getListStudentObj(int year) {
		List<Student> students = getListStudentByYear(year);
		students.sort((s1, s2) -> s1.getId().compareTo(s2.getId()));
		Object[][] std = new Object[students.size()][6];
		int count = 0;
		for (Student s : students) {
			std[count][0] = s.getId();
			std[count][1] = s.getFullname();
			std[count][2] = s.getDayOfBirth();
			std[count][3] = s.getHightSchool();
			std[count][4] = s.getExamcluster();
			std[count++][5] = s.getYear();
		}
		return std;
	}

	@Override
	public List<Student> searchListStudents(int year, String input) {
		return getListStudentByYear(year).stream()
				.filter(s -> s.getId().contains(input) || s.getFullname().contains(input)).collect(Collectors.toList());
	}

	public List<Student> getListStudentByYear(int year) {
		return students.stream().filter(s -> s.getYear() == year).collect(Collectors.toList());
	}
}