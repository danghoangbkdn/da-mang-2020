package dao;

import java.util.List;

import entity.Student;

public interface ListStudentDao {
	List<Student> getListStudentsByYear(int year);
}