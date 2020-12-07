package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entity.RequestGetAll;
import entity.RequestGetListStudentsByRequest;
import entity.Student;
import service.StudentService;
import service.StudentServiceImpl;

public class StudentControllerImpl implements StudentController {

	private final StudentService service;
	private List<Student> students;
	private List<Student> studentsByCluster;
	private int top;

	public StudentControllerImpl() {
		service = new StudentServiceImpl();
	}

	@Override
	public Student searchStudent(String id, int year) {
		Student student = new Student();
		for (Student s : service.getListStudentsByYear(year)) {
			if (s.getId().equals(id) && s.getYear() == year) {
				student = s;
			}
		}
		return student;
	}

	@Override
	public int getAll(Object obj) {
		RequestGetAll request = (RequestGetAll) obj;
		students = service.getListStudentsByYear(request.getYear());
		students.sort((s1, s2) -> s1.getId().compareTo(s2.getId()));
		if (request.getNameRequest().equals("GetAll")) {
			return students.size();
		} else if (request.getNameRequest().equals("GetByExamCluster")) {
			int count = 0;
			for (Student student : students) {
				if (student.getExamcluster().equals(request.getRequest())) {
					count++;
				}
			}
			return count;
		} else {
			top = (int) request.getRequest();
			return top;
		}
	}

	@Override
	public List<Student> getListStudentsByRequest(Object obj) {
		RequestGetListStudentsByRequest request = (RequestGetListStudentsByRequest) obj;
		int page = (int) request.getPage();
		List<Student> result = new ArrayList<>();

		if (request.getNameRequest().equals("Year")) {
			for (int i = 50 * (page - 1); i < students.size(); i++) {
				if (result.size() > 49) {
					break;
				}
				result.add(students.get(i));
			}
		} else if (request.getNameRequest().equals("ExamCluster")) {
			studentsByCluster = students.stream().filter(s -> s.getExamcluster().equals(request.getRequest()))
					.collect(Collectors.toList());
//			studentsByCluster.sort((s1, s2) -> s1.getId().compareTo(s2.getId()));
			for (int i = 50 * (page - 1); i < studentsByCluster.size(); i++) {
				if (result.size() > 49) {
					break;
				}
				result.add(studentsByCluster.get(i));
			}
		} else {
			result = getTopStudents(students, (String) request.getRequest());
		}

		return result;
	}

	private List<Student> getTopStudents(List<Student> students, String request) {
		switch (request) {
		case "Khối A":
			students.sort((s1, s2) -> String.valueOf((s2.getToan() + s2.getDiem1() + s2.getDiem2()))
					.compareTo(String.valueOf((s1.getToan() + s1.getDiem1() + s1.getDiem2()))));
			return students.stream().limit(top).collect(Collectors.toList());
		case "Khối A1":
			students.sort((s1, s2) -> String.valueOf((s2.getToan() + s2.getDiem1() + s2.getAnh()))
					.compareTo(String.valueOf((s1.getToan() + s1.getDiem1() + s1.getAnh()))));
			return students.stream().limit(top).collect(Collectors.toList());
		case "Khối B":
			students.sort((s1, s2) -> String.valueOf((s2.getToan() + s2.getDiem2() + s2.getDiem3()))
					.compareTo(String.valueOf((s1.getToan() + s1.getDiem2() + s1.getDiem3()))));
			return students.stream().limit(top).collect(Collectors.toList());
		case "Khối C":
			students.sort((s1, s2) -> String.valueOf((s2.getVan() + s2.getDiem1() + s2.getDiem2()))
					.compareTo(String.valueOf((s1.getVan() + s1.getDiem1() + s1.getDiem2()))));
			return students.stream().limit(top).collect(Collectors.toList());
		case "Khối D":
			students.sort((s1, s2) -> String.valueOf((s2.getToan() + s2.getVan() + s2.getAnh()))
					.compareTo(String.valueOf((s1.getToan() + s1.getVan() + s1.getAnh()))));
			return students.stream().limit(top).collect(Collectors.toList());
		default:
			students.sort((s1, s2) -> String
					.valueOf((s2.getToan() + s2.getVan() + s2.getAnh()) + s2.getDiem1() + s2.getDiem2() + s2.getDiem3())
					.compareTo(String.valueOf((s1.getToan() + s1.getVan() + s1.getAnh() + s1.getDiem1() + s1.getDiem2()
							+ s1.getDiem3()))));
			return students.stream().limit(top).collect(Collectors.toList());
		}
	}
}