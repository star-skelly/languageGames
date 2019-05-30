import java.util.*;

public class MoreComplicatedLanguage {

	public static Scanner kbr = new Scanner(System.in);
	public static Scanner kbr2 = new Scanner(System.in);
	private int langDiversity = 1;
	private String[][] vowels = { { "a", "á", "à", "a'a", "ai", "ae", "ao", "au", "ӓ", "ӕ", "ã", "â" /* A (if you need labeling for this part, you're an idiot) */ },
			{ "e", "é", "è", "ei", "ea", "eu", "eo", "ê", "ẽ", "ë" /* E */ },
			{ "o", "oa", "oi", "ou", "oe", "ö", "ô", "õ", "ò", "ó"/* O */ },
			{ "u", "ue", "ua", "ui", "uo", "ú", "ù", "ü", "û", "ũ"/* U */ },
			{ "i", "ia", "ie", "io", "iu", "i'i", "ĩ", "ì", "í", "î", "ï"/* I */ }, 
			{ "'", "-", "`" /* Silence */ } };

	private String[] vowelset;
	private String[] conset;
	private String[][] consonants = { { "b", "d", "v", "g"/* hard-soft */ },
			{ "w", "l", "m", "n", "r", "s", "y"/* soft */ }, { "c", "z", "s" /* s-ish */ },
			{ "h", "f", "th", "v", "z", "j"/* th-ish */ }, { "q", "k", "c", "t", "p", "x" /* hard */ },
			{ "sh", "j", "wh", "f", "s"/* super soft */ },
			{ "b", "c", "d", "g", "l", "m", "n", "p", "r", "s", "t"/* normality */ },
			{ "d", "g", "l", "n", "r", "s", "t"/* super-normal */ },
			{ "st", "dj", "tr", "dr", "sd", "kj", "kr", "cr", "ck", "rx"/* doubles */ } };
	private int[] wordStructure;
	
	public MoreComplicatedLanguage() {
		vowelset = new String[vowels.length * langDiversity];
		conset = new String[consonants.length * langDiversity];
		wordStructure = new int[langDiversity*4];
		fillVowels();
		fillConsonants();
		fillWordStructure();
	}

	public void fillVowels() {
		int a = 0;
		for (int b = 0; b < langDiversity; b++) {
			for (int i = 0; i < vowels.length; i++) {
				int random = (int) (Math.random() * 15);
				if (i != vowels.length - 1) {
					if (random < 10) {
						vowelset[a] = vowels[i][0];
					} else {
						vowelset[a] = vowels[i][(int) (Math.random() * vowels[i].length)];
					}
				} else {
					if (random == 9) {
						vowelset[a] = vowels[i][(int) (Math.random() * vowels[i].length)];
					} else {
						vowelset[a] = vowels[i - 3][(int) (Math.random() * vowels[i].length)];
					}
				}
				a++;
			}
		}
	}

	public void changeLanguage() {
		fillConsonants();
		fillVowels();
		fillWordStructure();
	}

	public void fillConsonants() {
		int a = 0;
		for (int b = 0; b < langDiversity; b++) {
			for (int i = 0; i < consonants.length; i++) {
				conset[a] = consonants[i][(int) (Math.random() * consonants[i].length)];
				a++;
			}
		}
	}

	public String makeSentence() {
		String sentence = "";
		String firstWord = makeWord();
		sentence += firstWord.substring(0, 1).toUpperCase() + firstWord.substring(1);
		for (int i = 0; i <= (int) (Math.random() * 7) + 3; i++) {
			sentence += " " + makeWord();
		}
		int random = (int) (Math.random() * 10);
		if(random == 6){
			sentence += "?";
		} else if(random == 7){
			sentence += "!";
		} else{
			sentence+=".";
		}
		return sentence;
	}

	public String getConsonant() {
		return conset[(int) (Math.random() * conset.length)];
	}

	public String getVowel() {
		return vowelset[(int) (Math.random() * vowelset.length)];
	}

	public String getConsonant(int lang) {
		return consonants[lang][(int) (Math.random() * consonants[lang].length)];
	}

	public String getVowel(int lang) {
		return vowels[lang][(int) (Math.random() * vowels[lang].length)];
	}
	
	public void fillWordStructure(){
		for(int i = 0; i<wordStructure.length; i++){
			wordStructure[i]= (int) (Math.random() * 12);
		}
	}

	public String makeWord() {
		String word = "";
		switch (wordStructure[(int) (Math.random() * wordStructure.length)]) {
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
		case 6: // VCCV
			word += getVowel();
			word += getConsonant();
			word += getConsonant();
			word += getVowel();
			break;
		case 7: // V
			word += getVowel();
			break;
		case 8: // VCC
			word += getVowel();
			word += getConsonant();
			word += getConsonant();
			break;
		case 9: // CVCCV
			word += getConsonant();
			word += getVowel();
			word += getConsonant();
			word += getConsonant();
			word += getVowel();
			break;
		case 10: // VCVCV
			word += getVowel();
			word += getConsonant();
			word += getVowel();
			word += getConsonant();
			word += getVowel();
			break;
		case 11: // CVCVC
			word += getConsonant();
			word += getVowel();
			word += getConsonant();
			word += getVowel();
			word += getConsonant();
			break;
		default:
			break;
		}
		return word;
	}

	public static void main(String[] args) {
		MoreComplicatedLanguage randomLang = new MoreComplicatedLanguage();
		for (int i = 0; i <= 5; i++) {
			System.out.println(randomLang.makeSentence());
		}
	}
}
