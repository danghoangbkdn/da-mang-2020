package common;

public enum EnumPanel {
	HOME, SEARCH, LIST;

	public static EnumPanel from(String name) {
		switch (name) {
		case "HOME":
			return HOME;
		case "SEARCH":
			return SEARCH;
		case "LIST":
			return LIST;
		}
		return HOME;
	}
}