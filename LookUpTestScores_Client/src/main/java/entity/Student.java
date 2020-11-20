package entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings("serial")
public class Student implements Serializable {
	private String id;
	private String fullname;
	private boolean gender;
	private Date dayOfBirth;
	private String address;
	private String hightSchool;
	private String examcluster;
	private String area;
	private Integer year;
	private Float toan;
	private Float van;
	private Float anh;
	private String mon1;
	private Float diem1;
	private String mon2;
	private Float diem2;
	private String mon3;
	private Float diem3;
	private String monTH;

	public Student() {
	}

	public Student(String id, String fullname, boolean gender, Date dayOfBirth, String address, String hightSchool,
			String examcluster, String area, Integer year, Float toan, Float van, Float anh, String mon1, Float diem1,
			String mon2, Float diem2, String mon3, Float diem3, String monTH) {
		this.id = id;
		this.fullname = fullname;
		this.gender = gender;
		this.dayOfBirth = dayOfBirth;
		this.address = address;
		this.hightSchool = hightSchool;
		this.examcluster = examcluster;
		this.area = area;
		this.year = year;
		this.toan = toan;
		this.van = van;
		this.anh = anh;
		this.mon1 = mon1;
		this.diem1 = diem1;
		this.mon2 = mon2;
		this.diem2 = diem2;
		this.mon3 = mon3;
		this.diem3 = diem3;
		this.monTH = monTH;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String isGender() {
		if (gender) {
			return "Nam";
		}
		return "Nữ";
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getDayOfBirth() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(dayOfBirth);
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHightSchool() {
		return hightSchool;
	}

	public void setHightSchool(String hightSchool) {
		this.hightSchool = hightSchool;
	}

	public String getExamcluster() {
		return examcluster;
	}

	public void setExamcluster(String examcluster) {
		this.examcluster = examcluster;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Float getToan() {
		return toan;
	}

	public void setToan(Float toan) {
		this.toan = toan;
	}

	public Float getVan() {
		return van;
	}

	public void setVan(Float van) {
		this.van = van;
	}

	public Float getAnh() {
		return anh;
	}

	public void setAnh(Float anh) {
		this.anh = anh;
	}

	public String getMon1() {
		return mon1;
	}

	public void setMon1(String mon1) {
		this.mon1 = mon1;
	}

	public Float getDiem1() {
		return diem1;
	}

	public void setDiem1(Float diem1) {
		this.diem1 = diem1;
	}

	public String getMon2() {
		return mon2;
	}

	public void setMon2(String mon2) {
		this.mon2 = mon2;
	}

	public Float getDiem2() {
		return diem2;
	}

	public void setDiem2(Float diem2) {
		this.diem2 = diem2;
	}

	public String getMon3() {
		return mon3;
	}

	public void setMon3(String mon3) {
		this.mon3 = mon3;
	}

	public Float getDiem3() {
		return diem3;
	}

	public void setDiem3(Float diem3) {
		this.diem3 = diem3;
	}

	public String getMonTH() {
		return monTH;
	}

	public void setMonTH(String monTH) {
		this.monTH = monTH;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Student)) {
			return false;
		}
		Student that = (Student) obj;
		return getId().equals(that.getId());
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", fullname=" + fullname + ", gender=" + gender + ", dayOfBirth=" + dayOfBirth
				+ ", address=" + address + ", hightSchool=" + hightSchool + ", examcluster=" + examcluster + ", area="
				+ area + ", year=" + year + ", toan=" + toan + ", van=" + van + ", anh=" + anh + ", mon1=" + mon1
				+ ", diem1=" + diem1 + ", mon2=" + mon2 + ", diem2=" + diem2 + ", mon3=" + mon3 + ", diem3=" + diem3
				+ ", monTH=" + monTH + "]";
	}
}