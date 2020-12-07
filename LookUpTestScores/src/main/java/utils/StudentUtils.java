package utils;

import java.util.List;

import entity.Student;

public interface StudentUtils {
	List<Student> searchByIdAndName(List<Student> students, String input);

	Object[][] convertValue(List<Student> students);

	Student getStudent(List<Student> students, String id);
}