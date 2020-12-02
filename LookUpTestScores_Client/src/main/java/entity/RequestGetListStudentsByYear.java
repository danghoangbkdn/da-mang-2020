package entity;

import java.io.Serializable;

public class RequestGetListStudentsByYear implements Serializable {

	private static final long serialVersionUID = -7479498537698420700L;

	private String request;
	private int year;

	public RequestGetListStudentsByYear(String request, int year) {
		this.request = request;
		this.year = year;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}