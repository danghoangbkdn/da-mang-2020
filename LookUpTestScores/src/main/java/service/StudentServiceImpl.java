package service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import dao.ListStudentDao;
import dao.ListStudentDaoImpl;
import entity.Student;

public class StudentServiceImpl implements StudentService {

	private final ListStudentDao dao;

	public StudentServiceImpl() {
		dao = new ListStudentDaoImpl();
	}

	@Override
	public List<Student> getListStudents() {
		return Optional.ofNullable(dao.getListStudents()).orElse(Collections.emptyList());
	}
}