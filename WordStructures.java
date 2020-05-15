import java.util.*;

public class WordStructures {

	public static Scanner kbr = new Scanner(System.in);
	public static Scanner kbr2 = new Scanner(System.in);
	private final int langDiversity = 3;
	private final String C = "1";
	private final String V = "0";
	private final String[][] vowels = { { "a", "a", "\u00E1", "\u00E0"  /* A (if you need labeling for this part, you're an idiot) */ },
			{ "e", "e", "\u00E9", "\u00E8","ee" /* E */ },
			{ "o", "o", "\u00F2", "\u00F3","oo"/* O */ },
			{ "u", "u", "\u00FA", "\u00F9" /* U */ },
			{ "i", "i", "\u00EC", "\u00ED"/* I */ },
			{"a'a","aa", "a'a", "ai", "ae", "ao", "au","e'e", "ei", "ea", "eu", "eo","o'o","oa", "oi", "ou", "oe","u'u", "uu","ue", "ua", "ui", "uo","i'i","ia", "ii","ie", "io", "iu"/*doubles*/},
			{ "'"," ","-"/* Silence */ } };

	private final String[] vowelset;
	private final String[] conset;
	private final String[][] consonants = { { "b", "d", "v", "g"/* hard-soft */ },
			{ "w", "l", "m", "n", "r", "s", "y"/* soft */ },
			{ "c", "z", "s" /* s-ish */ },
			{ "h", "f", "th", "v", "z", "j"/* th-ish */ },
			{ "q", "k", "c", "t", "p", "x" /* hard */ },
			{ "sh", "j", "wh", "f", "s"/* super soft */ },
			{ "b", "c", "d", "g", "l", "m", "n", "p", "r", "s", "t"/* normality */ },
			{ "d", "g", "l", "n", "r", "s", "t"/* super-normal */ },
			{ "st", "dj", "tr", "dr", "sd", "kj", "kr", "cr", "ck", "rx"/* hard doubles */ },
			{"mw","nw","mb","nb","ng" /*soft doubles*/}};
	private final String[] wordStructure;

	public WordStructures() {
		vowelset = new String[vowels.length * langDiversity];
		conset = new String[consonants.length * langDiversity];
		wordStructure = new String[langDiversity * 2];
		fillVowels();
		fillConsonants();
		autoFillWordStructure();
		System.out.println(".......generating.......");
	}

	public void fillVowels() {
		int a = 0;
		for (int b = 0; b < langDiversity; b++) {
			for (int i = 0; i < vowels.length; i++) {
				int random = (int) (Math.random() * 101);
				if (i<5) { //if it's in the single normal zone
					if (random < 98) { //98% chance you get a normal vowel for each (10% chance you'll have an accented vowel somewhere)
						vowelset[a] = vowels[i][0];
					} else {
						vowelset[a] = vowels[i][(int) (Math.random() * vowels[i].length)];
					}
				} else if(i==5){ //if it's the weird-ass double zone
					if(random<1){//5% chance to actually take a double
						vowelset[a] = vowels[i][(int) (Math.random() * vowels[i].length)];
					} else{//99% to skip it
						int newPos = i-(int) (Math.random() * (vowels.length-2));
						vowelset[a] = vowels[newPos][(int) (Math.random() * vowels[newPos].length)];
					}
				}else { //if it's in the silent zone
					if (random < 5) { //5% chance to get any of the silences
						vowelset[a] = vowels[i][(int) (Math.random() * vowels[i].length)];
					} else{ //95% to skip it
						int newPos = i-(int) (Math.random() * (vowels.length-2));
						vowelset[a] = vowels[newPos][(int) (Math.random() * vowels[newPos].length)];
					}
				}
				a++;
			}
		}
	}

	public void changeLanguage() {
		fillConsonants();
		fillVowels();
		autoFillWordStructure();
	}

	public void fillConsonants() {
		int a = 0;
		for (int b = 0; b < langDiversity; b++) {
			for (String[] consonant : consonants) {
				conset[a] = consonant[(int) (Math.random() * consonant.length)];
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
		if (random == 6) {
			sentence += "?";
		} else if (random == 7) {
			sentence += "!";
		} else {
			sentence += ".";
		}
		return sentence;
	}

	public String makePoemLine() {
		String sentence = "";
		String firstWord = makeWord();
		sentence += firstWord.substring(0, 1).toUpperCase() + firstWord.substring(1);
		for (int i = 0; i <= (int) (Math.random() * 3); i++) {
			sentence += " " + makeWord();
		}
		int random = (int) (Math.random() * 10);
		if (random == 6) {
			sentence += "?";
		} else if (random == 7) {
			sentence += "!";
		} else {
			sentence += ".";
		}
		return sentence;
	}

	public String getConsonant() {
		return conset[(int) (Math.random() * conset.length)];
	}

	public String getVowel() {
		return vowelset[(int) (Math.random() * vowelset.length)];
	}

	private String getConsonant(int lang) {
		return consonants[lang][(int) (Math.random() * consonants[lang].length)];
	}

	private String getVowel(int lang) {
		return vowels[lang][(int) (Math.random() * vowels[lang].length)];
	}

	private String addLetter(int probability /* of 1 (c) Out of 100 */) {
		int random = (int) (Math.random() * 100) + 1;
		if (random < probability) {
			return C; // c
		} else {
			return V; // v
		}
	}

	public void autoFillWordStructure() {
		for (int i = 0; i < wordStructure.length; i++) {
			String word = "";
			int length;
			int randomize = (int) (Math.random() * 100) + 1;
			if (randomize <= 75)
				length = (int) (Math.random() * 4) + 1;
			else if (randomize <= 80)
				length = (int) (Math.random() * 3) + 4;
			else if (randomize <= 90)
				length = (int) (Math.random() * 3) + 7;
			else
				length = 2;
			
			for (int b = 0; b < length; b++) {
				if(b==0 && length==1){
					word+=addLetter(0);
				} else if (b != 0 && word.substring(b - 1, b).equalsIgnoreCase(C)) {
					word += addLetter(5);
				} else if (b != 0 && word.substring(b - 1, b).equalsIgnoreCase(V)) {
					word += addLetter(90);
				} else {
					word += addLetter(50);
				}
			}
			wordStructure[i] = word;
		}
	}

	public String makeWord() {
		String word = "";
		int num = (int) (Math.random() * wordStructure.length);
		for (int i = 0; i < wordStructure[num].length(); i++) {
			if (wordStructure[num].substring(i, i + 1).equals(C)) {
				word += getConsonant();
			} else {
				word += getVowel();
			}
		}
		return word;
	}
}
