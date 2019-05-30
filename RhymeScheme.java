
public class RhymeScheme {
	public static int ABAB = 0;
	public static int ABBA = 1;
	public static int AABB = 2;
	public static int ABABB = 3;
	public static int ABABBCBC = 4;
	public static int ABABCBC = 5;
	public static int AABBA = 6;
	public static int AAAAA = 7;
	public static int ABCBBB = 8;
	public static int ABAAB = 9;
	public static int AABA = 10;
	public static int ABCB = 11;
	private static String A;
	private static String B;
	private static String C;
	private static WordStructures lang = new WordStructures();

	public static boolean rhymes(String word, String word2) {
		int fromEnd = 2;
		if (word.length() < 2) {
			fromEnd = 1;
		}
		int fromEnd2 = 2;
		if (word2.length() < 2) {
			fromEnd2 = 1;
		}
		if (word.substring(word.length() - fromEnd).equalsIgnoreCase(word2.substring(word2.length() - fromEnd2))) {
			return true;
		}
		return false;
	}

	public static int scheme() {
		int random = (int) (Math.random() * 11);
		return random;
	}

	private static void initLetters() {
		if (Math.random() * 2 < 1) {
			A = lang.getVowel() + lang.getConsonant();
		} else {
			A = lang.getConsonant() + lang.getVowel();
		}
		if (Math.random() * 2 < 1) {
			B = lang.getVowel() + lang.getConsonant();
		} else {
			B = lang.getConsonant() + lang.getVowel();
		}
		if (Math.random() * 2 < 1) {
			C = lang.getVowel() + lang.getConsonant();
		} else {
			C = lang.getConsonant() + lang.getVowel();
		}
	}

	private static String rhymeA() {
		String sentence = lang.makePoemLine();
		String sentence2 = "";
		sentence2 += sentence.substring(0, sentence.length()-3) + A;
		return sentence2;
	}

	private static String rhymeB() {
		String sentence = lang.makePoemLine();
		String sentence2 = "";
		sentence2 += sentence.substring(0, sentence.length() - 2) + B;
		return sentence2;
	}

	private static String rhymeC() {
		String sentence = lang.makePoemLine();
		String sentence2 = "";
		sentence2 += sentence.substring(0, sentence.length() - 2) + C;
		return sentence2;
	}

	public static void poem(int scheme) {
		initLetters();
		switch (scheme) {
		case 0:// ABAB
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			break;
		case 1:// ABBA
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeB());
			System.out.println(rhymeA());
			break;
		case 2:// AABB
			System.out.println(rhymeA());
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeB());
			break;
		case 3:// ABABB
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeB());
			break;
		case 4:// ABABBCBC
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeB());
			System.out.println(rhymeC());
			System.out.println(rhymeB());
			System.out.println(rhymeC());
			break;
		case 5:// ABABCBC
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeC());
			System.out.println(rhymeB());
			System.out.println(rhymeC());
			break;
		case 6:// AABA
			System.out.println(rhymeA());
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeA());
			break;
		case 7:// AAAAA
			System.out.println(rhymeA());
			System.out.println(rhymeA());
			System.out.println(rhymeA());
			System.out.println(rhymeA());
			System.out.println(rhymeA());
			break;
		case 8:// ABCBBB
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeC());
			System.out.println(rhymeB());
			System.out.println(rhymeB());
			System.out.println(rhymeB());
			break;
		case 9:// ABAAB
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeA());
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			break;
		case 10:// AABA
			System.out.println(rhymeA());
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeA());
			break;
		case 11:// ABCB
			System.out.println(rhymeA());
			System.out.println(rhymeB());
			System.out.println(rhymeC());
			System.out.println(rhymeB());
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {
		int schemeOfTheDay = (int)(Math.random()*12);
		for (int i = 0; i < 10; i++) {
			poem(schemeOfTheDay);
			System.out.println();
		}
	}

}
