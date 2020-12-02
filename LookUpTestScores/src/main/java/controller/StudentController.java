package controller;

import java.util.List;

import entity.Student;

public interface StudentController {
	Student searchStudent(String id, int year);

	List<Student> getListStudentsByYear(int year);
}