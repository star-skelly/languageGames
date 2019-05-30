import java.util.*;

public class Languages {

	public static Scanner kbr = new Scanner(System.in);
	public static Scanner kbr2 = new Scanner(System.in);

	String[][] vowels = { { "a", "e", "o", "u", "i", /* English */ },
			{ "á", "é", "ó", "ú", "í", "a", "e", "o", "u", "i", "a", "e", "o", "u", "i" /* Spanish */ },
			{ "a", "e", "o", "u", "i", "ae", "ai", "oa", "a", "e", "o", "u", "i"/* Aelgai */ },
			{ "a", "e", "o", "u", "i", "ia", "i'i", "ei", "oa","a", "e", "o", "u", "i" /* K'hili */ } };

	String[][] consonants = {
			{ "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y",
					"z"/* English */ },
			{ "b", "c", "cc", "ch", "d", "f", "g", "h", "j", "k", "l", "ll", "m", "n", "ñ", "p", "q", "r", "rr", "s",
					"t", "v", "x", "y", "z"/* Spanish */ },
			{ "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z",
					"l", "z", "x", "q", "g" /* Aelgai */ },
			{ "b", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "r", "s", "t", "v", "w", "y", "h", "l", "w",
					"p"/* K'hili */ } };

	public String makeWordFromLang(int vLang, int cLang) {
		String word = "";
		switch ((int) (Math.random() * 15)) {
		case 0: // VC
			word += getVowel(vLang);
			word += getConsonant(cLang);
			break;
		case 1: // CV
			word += getConsonant(cLang);
			word += getVowel(vLang);
			break;
		case 2: // VCV
			word += getVowel(vLang);
			word += getConsonant(cLang);
			word += getVowel(vLang);
			break;
		case 3: // CVC
			word += getConsonant(cLang);
			word += getVowel(vLang);
			word += getConsonant(cLang);
			break;
		case 4: // VCVC
			word += getVowel(vLang);
			word += getConsonant(cLang);
			word += getVowel(vLang);
			word += getConsonant(cLang);
			break;
		case 5: // CVCV
			word += getConsonant(cLang);
			word += getVowel(vLang);
			word += getConsonant(cLang);
			word += getVowel(vLang);
			break;
		case 6: // CVVC
			word += getConsonant(cLang);
			word += getVowel(vLang);
			word += getVowel(vLang);
			word += getConsonant(cLang);
			break;
		case 7: // VCCV
			word += getVowel(vLang);
			word += getConsonant(cLang);
			word += getConsonant(cLang);
			word += getVowel(vLang);
			break;
		case 8: // V
			word += getVowel(vLang);
			break;
		case 9: // VCC
			word += getVowel(vLang);
			word += getConsonant(cLang);
			word += getConsonant(cLang);
			break;
		case 10: // CVCCV
			word += getConsonant(cLang);
			word += getVowel(vLang);
			word += getConsonant(cLang);
			word += getConsonant(cLang);
			word += getVowel(vLang);
			break;
		case 11: // VCVVC
			word += getVowel(vLang);
			word += getConsonant(cLang);
			word += getVowel(vLang);
			word += getVowel(vLang);
			word += getConsonant(cLang);
			break;
		case 12: // VCVCV
			word += getVowel(vLang);
			word += getConsonant(cLang);
			word += getVowel(vLang);
			word += getConsonant(cLang);
			word += getVowel(vLang);
			break;
		case 13: // CVCVC
			word += getConsonant(cLang);
			word += getVowel(vLang);
			word += getConsonant(cLang);
			word += getVowel(vLang);
			word += getConsonant(cLang);
			break;
		case 14: // VVC
			word += getVowel(vLang);
			word += getVowel(vLang);
			word += getConsonant(cLang);
			break;
		default:
			break;
		}
		return word;
	}

	public String makeSentence() {
		int c = (int) (Math.random() * consonants.length);
		int v = (int) (Math.random() * vowels.length);
		String sentence = "";
		for (int i = 0; i <= (int) (Math.random() * 7) + 3; i++) {
			sentence += makeWordFromLang(v, c) + " ";
		}
		return sentence;
	}

	public String makeSentence(int vLang, int cLang) {
		String sentence = "";
		for (int i = 0; i <= (int) (Math.random() * 7) + 3; i++) {
			sentence += makeWordFromLang(vLang, cLang) + " ";
		}
		return sentence;
	}

	public String getConsonant() {
		int num = (int) (Math.random() * consonants.length);
		return consonants[num][(int) (Math.random() * consonants[num].length)];
	}

	public String getVowel() {
		int num = (int) (Math.random() * vowels.length);
		return vowels[num][(int) (Math.random() * vowels[num].length)];
	}

	public String getConsonant(int lang) {
		return consonants[lang][(int) (Math.random() * consonants[lang].length)];
	}

	public String getVowel(int lang) {
		return vowels[lang][(int) (Math.random() * vowels[lang].length)];
	}

	public String makeWordStructure() {
		String word = "";
		switch ((int) (Math.random() * 15)) {
		case 0: // VC
			word += getVowel();
			word += getConsonant();
			break;
		case 1: // CV
			word += getConsonant();
			word += getVowel();
			break;
		case 2: // VCV
			word += getVowel();
			word += getConsonant();
			word += getVowel();
			break;
		case 3: // CVC
			word += getConsonant();
			word += getVowel();
			word += getConsonant();
			break;
		case 4: // VCVC
			word += getVowel();
			word += getConsonant();
			word += getVowel();
			word += getConsonant();
			break;
		case 5: // CVCV
			word += getConsonant();
			word += getVowel();
			word += getConsonant();
			word += getVowel();
			break;
		case 6: // CVVC
			word += getConsonant();
			word += getVowel();
			word += getVowel();
			word += getConsonant();
			break;
		case 7: // VCCV
			word += getVowel();
			word += getConsonant();
			word += getConsonant();
			word += getVowel();
			break;
		case 8: // V
			word += getVowel();
			break;
		case 9: // VCC
			word += getVowel();
			word += getConsonant();
			word += getConsonant();
			break;
		case 10: // CVCCV
			word += getConsonant();
			word += getVowel();
			word += getConsonant();
			word += getConsonant();
			word += getVowel();
			break;
		case 11: // VCVVC
			word += getVowel();
			word += getConsonant();
			word += getVowel();
			word += getVowel();
			word += getConsonant();
			break;
		case 12: // VCVCV
			word += getVowel();
			word += getConsonant();
			word += getVowel();
			word += getConsonant();
			word += getVowel();
			break;
		case 13: // CVCVC
			word += getConsonant();
			word += getVowel();
			word += getConsonant();
			word += getVowel();
			word += getConsonant();
			break;
		case 14: // VVC
			word += getVowel();
			word += getVowel();
			word += getConsonant();
			break;
		default:
			break;
		}
		return word;
	}

	public static void main(String[] args) {
		Languages randomLang = new Languages();
		for (int i = 0; i <= 20; i++) {
			System.out.println(randomLang.makeSentence());
		}
	}
}
