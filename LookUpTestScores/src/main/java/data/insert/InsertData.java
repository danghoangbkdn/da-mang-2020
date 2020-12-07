package data.insert;

import java.util.Random;
import java.util.Scanner;

import dao.InsertDataImpl;

public class InsertData {
	private static Random rd = new Random();
	private static Scanner sc = new Scanner(System.in);
	private static InsertDataImpl dao;

	public static void main(String[] args) {
		dao = new InsertDataImpl();

		String[] s1 = getInfo(middleNameMale, firstNameMale, middleNameFemale, firstNameFemale, 2020);
		String[] s2 = getScore();
		String[] s3 = getScoreByTH();
		System.out.print("Bạn có muốn insert data vào database (Y/N) ? -> ");
		String key = sc.nextLine();
		System.out.println("=======================================");
		switch (key) {
		case "Y":
			System.out.println(dao.setData("thisinh", s1));
			System.out.println(dao.setData("diemthi", s2));
			System.out.println(dao.setData("diemthitohop", s3));
			break;
		default:
			break;
		}
	}

	private static String[] getInfo(String[] middleName, String[] firstName, String[] middleNameFemale,
			String[] firstNameFemale, int year) {
		String[] students = new String[500];
		int count = 0;
		String homeTown = "";
		for (int i = 2020501; i <= 2020999; i++) {
			homeTown = homeTowns[rd.nextInt(homeTowns.length)];
			if (i % 2 == 0) {
				if (i == 2020999) {
					students[count] = "('" + i + "', '" + lastName[rd.nextInt(7)] + " " + middleName[rd.nextInt(8)]
							+ " " + firstName[rd.nextInt(17)] + "', " + 1 + ", '2002-" + regex("month") + "-"
							+ regex("day") + "', " + homeTown + ", " + hightSchools[rd.nextInt(hightSchools.length)]
							+ ", " + examCluster(homeTown) + ", " + year + ")";
					break;
				}
				students[count++] = "('" + i + "', '" + lastName[rd.nextInt(7)] + " " + middleName[rd.nextInt(8)] + " "
						+ firstName[rd.nextInt(17)] + "', " + 1 + ", '2002-" + regex("month") + "-" + regex("day")
						+ "', " + homeTown + ", " + hightSchools[rd.nextInt(hightSchools.length)] + ", "
						+ examCluster(homeTown) + ", " + year + "),";
			} else {
				students[count++] = "('" + i + "', '" + lastName[rd.nextInt(7)] + " "
						+ middleNameFemale[rd.nextInt(middleNameFemale.length)] + " "
						+ firstNameFemale[rd.nextInt(firstNameFemale.length)] + "', " + 0 + ", '2002-" + regex("month")
						+ "-" + regex("day") + "', " + homeTown + ", " + hightSchools[rd.nextInt(hightSchools.length)]
						+ ", " + examCluster(homeTown) + ", " + year + "),";
			}
		}
		return students;
	}

	private static String[] getScore() {
		String[] scores = new String[500];
		int count = 0;
		for (int i = 2020501; i <= 2020999; i++) {
			if (i == 2020999) {
				scores[count] = "('" + i + "', " + (rd.nextInt(3) + 7) + random() + ", " + (rd.nextInt(3) + 7)
						+ random() + ", " + (rd.nextInt(3) + 7) + random() + ", " + (i % 2 == 0 ? 11 : 12) + ")";
				break;
			}
			scores[count++] = "('" + i + "', " + (rd.nextInt(3) + 7) + random() + ", " + (rd.nextInt(3) + 7) + random()
					+ ", " + (rd.nextInt(3) + 7) + random() + ", " + (i % 2 == 0 ? 11 : 12) + "),";
		}
		return scores;
	}

	private static String[] getScoreByTH() {
		String[] scores = new String[500];
		int count = 0;
		for (int i = 2020501; i <= 2020999; i++) {
			if (i == 2020999) {
				scores[count] = "('" + i + "', " + (rd.nextInt(3) + 7) + random() + ", " + (rd.nextInt(3) + 7)
						+ random() + ", " + (rd.nextInt(3) + 7) + random() + ")";
				break;
			}
			scores[count++] = "('" + i + "', " + (rd.nextInt(3) + 7) + random() + ", " + (rd.nextInt(3) + 7) + random()
					+ ", " + (rd.nextInt(3) + 7) + random() + "),";
		}
		return scores;
	}

	private static String random() {
		String[] num = new String[] { ".0", ".2", ".4", ".6", ".8" };
		return num[rd.nextInt(5)];
	}

	private static int examCluster(String homeTown) {
		if (homeTown.contains("Hà Nội")) {
			return 11;
		} else if (homeTown.contains("Bắc Ninh")) {
			return 12;
		} else if (homeTown.contains("Hải Phòng")) {
			return 13;
		} else if (homeTown.contains("Hà Tĩnh") || homeTown.contains("Nghệ An")) {
			return 21;
		} else if (homeTown.contains("Đà Nẵng") || homeTown.contains("Huế") || homeTown.contains("Quảng Nam")) {
			return 22;
		} else if (homeTown.contains("Khánh Hòa") || homeTown.contains("Bình Định")) {
			return 23;
		} else if (homeTown.contains("Hồ Chí Minh")) {
			return 31;
		} else if (homeTown.contains("Đồng Nai")) {
			return 32;
		} else {
			return 33;
		}
	}

	private static String regex(String regex) {
		if (regex.equals("month")) {
			int month = rd.nextInt(12) + 1;
			return month < 10 ? "0" + month : "" + month;
		} else {
			int day = rd.nextInt(30) + 1;
			return day < 10 ? "0" + day : "" + day;
		}
	}

	private static String[] lastName = new String[] { "Trần", "Nguyễn", "Lê", "Đặng", "Phan", "Phạm", "Dương" };
	private static String[] middleNameMale = new String[] { "Nhật", "Quang", "Quốc", "Duy", "Đình", "Văn", "Tuấn",
			"Tấn" };
	private static String[] firstNameMale = new String[] { "Hoàng", "Anh", "An", "Bình", "Đạt", "Bảo", "Huy", "Kiên",
			"Thành", "Sang", "Sơn", "Hiệp", "Khánh", "Trung", "Vũ", "Mạnh", "Đại" };
	private static String[] middleNameFemale = new String[] { "Bảo", "Thục", "Thiên", "Quỳnh", "Thị", "Ngọc", "Thúy",
			"Diễm", "Linh" };
	private static String[] firstNameFemale = new String[] { "Anh", "An", "Chi", "Diệu", "Duyên", "Gianh", "Hà", "Hoa",
			"Hoài", "Linh", "Lan", "Ngọc", "Ngân", "Thúy", "Uyên", "Yến" };
	private static String[] homeTowns = new String[] { "'Hạ Long-Hải Phòng'", "'Bắc Ninh-Bắc Ninh'", "'Ba Đình-Hà Nội'",
			"'Cầu Giấy-Hà Nội'", "'Đống Đa-Hà Nội'", "'Vinh-Nghệ An'", "'Hồng Lĩnh-Hà Tĩnh'", "'Thạnh Hà-Hà Tĩnh'",
			"'Huế-TT Huế'", "'Hải Châu-Đà Nẵng'", "'Liên Chiểu-Đà Nẵng'", "'Tam Kỳ-Quảng Nam'", "'Hội An-Quảng Nam'",
			"'Quy Nhơn-Bình Định'", "'Nha Trang-Khánh Hòa'", "'Biên Hòa-Đồng Nai'", "'Thủ Đức-Hồ Chí Minh'",
			"'Tân Bình-Hồ Chí Minh'", "'Bình Thủy-Cần Thơ'" };
	private static String[] hightSchools = new String[] { "'THPT Lê Qúy Đôn'", "'THPT Nguyễn Trãi'",
			"'THPT Nguyễn Huệ'", "'THPT Phan Đình Phùng'", "'THPT Hai Bà Trưng'", "'THPT Lý Tự Trọng'", "'THPT Lê Lợi'",
			"'THPT Nguyễn Biểu'", "'THPT Lê Hồng Phong'", "'THPT Nguyễn Du'", "'THPT Lê Hữu Trác'",
			"'THPT Phan Bội Châu'" };
}