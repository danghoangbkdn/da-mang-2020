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
	public List<Student> getListStudentsByYear(int year) {
		return Optional.ofNullable(dao.getListStudentsByYear(year)).orElse(Collections.emptyList());
	}
}