package utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import entity.Student;

public class StudentUtilsImpl implements StudentUtils {
	public StudentUtilsImpl() {
	}

	@Override
	public List<Student> searchByIdAndName(List<Student> students, String input) {
		return students.stream()
				.filter(s -> s.getId().contains(splitSpace(input)) || convert(s.getFullname()).contains(convert(input)))
				.collect(Collectors.toList());
	}

	@Override
	public Object[][] convertValue(List<Student> students) {
		Object[][] std = new Object[students.size()][6];
		int count = 0;
		for (Student s : students) {
			std[count][0] = s.getId();
			std[count][1] = s.getFullname();
			std[count][2] = s.getDayOfBirth();
			std[count][3] = "Trường " + s.getHightSchool();
			std[count][4] = s.getExamcluster();
			std[count++][5] = s.getYear();
		}
		return std;
	}

	@Override
	public Student getStudent(List<Student> students, String id) {
		return students.stream().filter(s -> s.getId().equals(id)).collect(Collectors.toList()).get(0);
	}

	private String splitSpace(String input) {
		return Pattern.compile(" ").splitAsStream(input).filter(in -> !in.equals("")).collect(Collectors.joining());
	}

	private String convert(String input) {
		return Pattern.compile(" ").splitAsStream(input).filter(in -> !in.equals("")).map(s -> wordsAfterConversion(s))
				.collect(Collectors.joining());
	}

	private String wordsAfterConversion(String input) {
		return Pattern.compile("").splitAsStream(input).map(s -> changeTo(s)).collect(Collectors.joining())
				.toLowerCase();
	}

	private String changeTo(String input) {
		Map<String, String> characters = new LinkedHashMap<>();
		characters.put("a", "á à ả ã ạ â ấ ầ ẩ ẫ ậ ă ắ ằ ẳ ẵ ặ");
		characters.put("A", "Á À Ả Ã Ạ Â Ấ Ầ Ẩ Ẫ Ậ Ă Ắ Ằ Ẳ Ẵ Ặ");
		characters.put("d", "đ");
		characters.put("D", "Đ");
		characters.put("e", "é è ẻ ẽ ẹ ê ế ề ể ễ ệ");
		characters.put("E", "É È Ẻ Ẽ Ẹ Ê Ế Ề Ể Ễ Ệ");
		characters.put("i", "í ì ỉ ĩ ị");
		characters.put("I", "Í Ì Ỉ Ĩ Ị");
		characters.put("o", "ó ò ỏ õ ọ ô ố ồ ổ ỗ ộ ơ ớ ờ ở ỡ ợ");
		characters.put("O", "Ó Ò Ỏ Õ Ọ Ô Ố Ồ Ổ Ỗ Ộ Ơ Ớ Ờ Ở Ỡ Ợ");
		characters.put("u", "ú ù ủ ũ ụ ư ứ ừ ử ữ ự");
		characters.put("U", "Ú Ù Ủ Ũ Ụ Ư Ứ Ừ Ử Ữ Ự");
		characters.put("y", "ý ỳ ỷ ỹ ỵ");
		characters.put("Y", "Ý Ỳ Ỷ Ỹ Ỵ");

		for (Entry<String, String> character : characters.entrySet()) {
			if (character.getValue().contains(input)) {
				return character.getKey();
			}
		}

		return input;
	}
}