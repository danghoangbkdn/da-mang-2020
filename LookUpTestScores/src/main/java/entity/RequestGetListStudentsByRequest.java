package entity;

import java.io.Serializable;

public class RequestGetListStudentsByRequest implements Serializable {

	private static final long serialVersionUID = -7479498537698420700L;

	private String nameRequest;
	private Object request;
	private int year;
	private int numPages;
	private int page;

	public RequestGetListStudentsByRequest(String nameRequest, Object request, int year, int numPages, int page) {
		this.nameRequest = nameRequest;
		this.setRequest(request);
		this.setYear(year);
		this.setNumPages(numPages);
		this.page = page;
	}

	public String getNameRequest() {
		return nameRequest;
	}

	public void setNameRequest(String request) {
		this.nameRequest = request;
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

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}