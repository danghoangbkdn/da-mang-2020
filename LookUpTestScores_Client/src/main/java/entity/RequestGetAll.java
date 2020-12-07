package entity;

import java.io.Serializable;

public class RequestGetAll implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nameRequest;
	private Object request;
	private int year;

	public RequestGetAll() {
	}

	public RequestGetAll(String nameRequest, Object request, int year) {
		this.nameRequest = nameRequest;
		this.request = request;
		this.year = year;
	}

	public String getNameRequest() {
		return nameRequest;
	}

	public void setNameRequest(String nameRequest) {
		this.nameRequest = nameRequest;
	}

	public Object getRequest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}