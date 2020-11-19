package entity;

import java.io.Serializable;

public class RequestSearch implements Serializable {

	private static final long serialVersionUID = -7479498537698420700L;

	private String id;
	private String request;
	private String inputId;
	private int inputYear;

	public RequestSearch() {
	}

	public RequestSearch(String id, String request, String inputId, int inputYear) {
		this.setId(id);
		this.request = request;
		this.inputId = inputId;
		this.inputYear = inputYear;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getInputId() {
		return inputId;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	public int getInputYear() {
		return inputYear;
	}

	public void setInputYear(int inputYear) {
		this.inputYear = inputYear;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}