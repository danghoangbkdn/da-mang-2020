package controller;

import java.util.List;

import entity.Student;

public interface StudentController {
	Student searchStudent(String id, int year);

	int getAll(Object obj);

	List<Student> getListStudentsByRequest(Object obj);
}