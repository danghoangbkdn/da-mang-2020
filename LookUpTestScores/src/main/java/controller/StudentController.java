package controller;

import java.util.List;

import entity.Student;

public interface StudentController {
	Student searchStudent(String id, int year);

	Object[][] getListStudentObj(int year);

	List<Student> searchListStudents(int year, String input);
}