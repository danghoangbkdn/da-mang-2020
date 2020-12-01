package utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ConvertUtils {
	private ConvertUtils() {
	}

	public static String wordsAfterConversion(String input) {
		return Pattern.compile("").splitAsStream(input).map(s -> changeTo(s)).collect(Collectors.joining())
				.toLowerCase();
	}

	private static String changeTo(String input) {
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