package entity;

import java.io.Serializable;
import java.util.Objects;

public class RequestShowAll implements Serializable {

	private static final long serialVersionUID = -7479498537698420700L;

	private String request;
	private int year;

	public RequestShowAll(String request, int year) {
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof RequestShowAll)) {
			return false;
		}
		RequestShowAll that = (RequestShowAll) obj;
		return that.getRequest().equals(getRequest());
	}

	@Override
	public int hashCode() {
		return Objects.hash(request);
	}

	@Override
	public String toString() {
		return "RequestShowAll [request=" + request + ", year=" + year + "]";
	}
}