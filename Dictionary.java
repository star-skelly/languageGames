import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
	public static WordStructures Lang;
	private static ArrayList<String> englishNouns;
	private static ArrayList<String> equivNouns;
	private static ArrayList<ArrayList<String>> engWords;
	private static ArrayList<String> aelgarWords;

	public Dictionary() {
		Lang = new WordStructures();
		englishNouns = new ArrayList<String>();
		equivNouns = new ArrayList<String>();
		engWords = new ArrayList<ArrayList<String>>();
		aelgarWords = new ArrayList<String>();
		try {
			fillNounList();
			fillEngWordList();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void fillNounList() throws IOException {
		Scanner iFile = new Scanner(new File("Words.txt"));
		while (iFile.hasNext()) {
			englishNouns.add(iFile.nextLine());
			equivNouns.add(Lang.makeWord());
		}
	}

	public void fillEngWordList() throws IOException {
		Scanner iFile = new Scanner(new File("eng.txt"));
		Scanner dFile = new Scanner(new File("aelgai.txt"));
		while (iFile.hasNext()) {
			ArrayList<String> temp = new ArrayList<String>();
			ArrayList<String> temp1 = new ArrayList<String>();
			temp.add(iFile.nextLine());
			for(int a = 0; a < temp.size(); a++){
				int prev = 0;
				int sized = temp.size()-1;
				String wordeds = temp.get(sized);
				if(wordeds.substring(a,a+1).equals("/")){
					temp1.add(wordeds.substring(prev,a));
					prev=a+1;
				}
			}
			engWords.add(temp);
			aelgarWords.add(dFile.nextLine());
		}
	}

	public void printDictionary() {
		for (int i = 0; i < englishNouns.size(); i++) {
			System.out.println(englishNouns.get(i) + " : " + equivNouns.get(i));
		}
	}

	public String cleanTranslate(String sentence) {
		String[] separate = sentence.split(" ");
		boolean cont = true;
		while (cont) {
			for (String word : separate) {
				if (!(word.equals("\\."))) {

				}
			}
		}
		return "";
	}

	public String trueTranslate(String sentence) { // Oof just ended it
		//arrays to hold punctuation positions
		ArrayList<Integer> ppositions = new ArrayList<Integer>();
		ArrayList<Integer> cpositions = new ArrayList<Integer>();
		ArrayList<Integer> epositions = new ArrayList<Integer>();
		ArrayList<Integer> qpositions = new ArrayList<Integer>();
		//number of each type (i know this is unnecessary)
		int numP = 0;
		int numC = 0;
		int numE = 0;
		int numQ = 0;
		//Strings we will be filling
		String translation = "";
		String stripped = "";

		//finding punctuation placement
		//for loop goes through each character
		for(int i = 0; i<sentence.length(); i++){
			int spaceCount = 0;
			//checks for each type of punctuation
			if(sentence.substring(i, i+1).equals(".")){
				numP++;
				//counts the number of spaces before it
				for (int B = 0; B < i; B++) {
					if (sentence.substring(B, B + 1).equals(" ")) {
						spaceCount++;
					}
				}
				//adds it to the positions
				ppositions.add(spaceCount);
			} else if(sentence.substring(i, i+1).equals(",")){
				numC++;
				for (int B = 0; B < i; B++) {
					if (sentence.substring(B, B + 1).equals(" ")) {
						spaceCount++;
					}
				}
				cpositions.add(spaceCount);
			}else if(sentence.substring(i, i+1).equals("?")){
				numQ++;
				for (int B = 0; B < i; B++) {
					if (sentence.substring(B, B + 1).equals(" ")) {
						spaceCount++;
					}
				}
				qpositions.add(spaceCount);
			} else if(sentence.substring(i, i+1).equals("!")){
				numE++;
				for (int B = 0; B < i; B++) {
					if (sentence.substring(B, B + 1).equals(" ")) {
						spaceCount++;
					}
				}
				epositions.add(spaceCount);
			}

		}
		String[] noPeriods = sentence.split("\\.");
		for (String word : noPeriods) {
			stripped += word;
		}
		String[] noCommas = stripped.split(",");
		stripped = "";
		for (String word : noCommas) {
			stripped += word;
		}
		String[] noExclaiming = stripped.split("!");
		stripped = "";
		for (String word : noExclaiming) {
			stripped += word;
		}
		String[] noQuestions = stripped.split("\\?");
		stripped = "";
		for (String word : noQuestions) {
			stripped += word;
		}
		String[] words = stripped.split(" ");
		int counter = 0;
		for (String word : words) {
			translation += translate(word);
			int countP = 0;
			if (numP != 0)
				for (int pos : ppositions) {
					if (countP <= numP && pos == counter) {
						translation += ".";
						countP++;
					}
				}
			int countC = 0;
			if (numC != 0)
				for (int pos : cpositions) {
					if (countC <= numC && pos == counter) {
						translation += ",";
						countC++;
					}
				}
			int countE = 0;
			if (numE != 0)
				for (int pos : epositions) {
					if (countE <=numE && pos == counter) {
						translation += "!";
						countE++;
					}
				}
			int countQ = 0;
			if (numQ != 0)
				for (int pos : qpositions) {
					if (countQ <= numQ && pos == counter) {
						translation += "?";
						countQ++;
					}
				}
			translation += " ";
			counter++;
		}
		String capital = translation.substring(0, 1).toUpperCase() + translation.substring(1);
		return capital;
	}

	public String translateToAelgai(String word) {
		String translation = "";
		translation = "sa";
		for (int i = 0; i < engWords.size(); i++) {
			translation = "blech";
			for(int a = 0; a < engWords.get(i).size();a++){
			    translation = "huh";
				if(word.equalsIgnoreCase(engWords.get(i).get(a))) {
					translation = aelgarWords.get(i);
				} else{
				    translation = "(no translation for " + word + ")";
				}
			}
		}
		return translation;
	}

	public String translate(String word) {
		String translation = "";
		for (int i = 0; i < englishNouns.size(); i++) {
			if (word.equalsIgnoreCase(englishNouns.get(i))) {
				translation = equivNouns.get(i);
				return translation;
			} else {
				translation = "(no translation for " + word + ")";
			}
		}
		for (int i = 0; i < equivNouns.size(); i++) {
			if (word.equalsIgnoreCase(englishNouns.get(i))) {
				translation = equivNouns.get(i);
				return translation;
			} else {
				translation = "(no translation for " + word + ")";
			}
		}
		return translation;
	}
}
