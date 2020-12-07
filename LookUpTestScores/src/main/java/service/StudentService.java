package service;

import java.util.List;

import entity.Student;

public interface StudentService {
	List<Student> getListStudentsByYear(int year);
}