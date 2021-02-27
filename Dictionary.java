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
	//variables for stripping sentences
	//arrays to hold punctuation positions
	private ArrayList<Integer> ppositions = new ArrayList<Integer>();
	private ArrayList<Integer> cpositions = new ArrayList<Integer>();
	private ArrayList<Integer> epositions = new ArrayList<Integer>();
	private ArrayList<Integer> qpositions = new ArrayList<Integer>();
	//number of each type (i know this is unnecessary)
	private int numP = 0;
	private int numC = 0;
	private int numE = 0;
	private int numQ = 0;

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
			String line = "";
			String temp = "";
			ArrayList<String> temp1 = new ArrayList<String>();
			line+=iFile.nextLine();
			String[] unQuoted = line.split("\"");
			for (String word : unQuoted) {
				temp += word;
			}
			int prev = 0;
			String singleWord = "";

			for(int a = 0; a < temp.length(); a++){
				if(temp.substring(a,a+1).equals("/")){
					temp1.add(temp.substring(prev,a));
					//System.out.print(temp.substring(prev,a)+" ");
					prev=a+1; //adds the words to arraylist of english words meaning the same Aelgai word (but doesn't get the last one)
				}else {
					singleWord=temp;
				}
			}
			if(prev==0) {
				temp1.add(singleWord);
			} else{
				temp1.add(singleWord.substring(prev));//adds the last one
			}
			engWords.add(temp1);
			aelgarWords.add(dFile.nextLine());
		}
	}

	public void printDictionary() {
		for (int i = 0; i < englishNouns.size(); i++) {
			System.out.println(englishNouns.get(i) + " : " + equivNouns.get(i));
		}
	}
	public String[] stripSentence(String sentence){
		//Strings we will be filling
		String translation = "";
		String stripped = "";

		//finding punctuation placement
		//for loop goes through each character
		for(int i = 0; i<sentence.length(); i++){
			int spaceCount = 0;
			//checks for each type of punctuation
			//counts the number of spaces before it
			//adds it to the positions
			switch (sentence.substring(i, i + 1)) {
				case "." -> {
					numP++;
					for (int B = 0; B < i; B++) {
						if (sentence.substring(B, B + 1).equals(" ")) {
							spaceCount++;
						}
					}
					ppositions.add(spaceCount);
				}case "," -> {
					numC++;
					for (int B = 0; B < i; B++) {
						if (sentence.substring(B, B + 1).equals(" ")) {
							spaceCount++;
						}
					}
					cpositions.add(spaceCount);
				}case "?" -> {
					numQ++;
					for (int B = 0; B < i; B++) {
						if (sentence.substring(B, B + 1).equals(" ")) {
							spaceCount++;
						}
					}
					qpositions.add(spaceCount);
				}case "!" -> {
					numE++;
					for (int B = 0; B < i; B++) {
						if (sentence.substring(B, B + 1).equals(" ")) {
							spaceCount++;
						}
					}
					epositions.add(spaceCount);
				}
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
		return words;
	}

	public String trueTranslate(String sentence) {
		String[] words=stripSentence(sentence);
		String translation = "";
		int counter = 0;
		for (String word : words) {
			translation += checkForConjugation(word);
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

	public String checkForConjugation(String word){
		//maybe add prefixes & suffixes: re- pre- anti- de- dis- -ist -ism
		if(translate(word).equals("(no translation for " + word + ")") && word.length() != 0 && !word.equals(" ")){
			if(word.substring(word.length()-1, word.length()).equals("s")){
				return translate(word.substring(0, word.length()-1)) + translate("s");
			}
			if(word.substring(word.length()-2,word.length()).equals("ed")){
				return translate(word.substring(0, word.length()-2)) + translate("ed");
			}
			if(word.substring(word.length()-3,word.length()).equals("ing")) {
				return translate(word.substring(0, word.length() - 3)) + translate("ing");
			}
			if(word.substring(word.length()-2,word.length()).equals("er")) {
				return translate(word.substring(0, word.length() - 2)) + translate("er");
			}
			if(word.substring(word.length()-3,word.length()).equals("ion")) {
				return translate(word.substring(0, word.length() - 3) + "e") + translate("ion");
			}
		}
		if(!word.equals(" ") && !word.equals("")){
			return translate(word);
		}
		return word;
	}

	public String translateWordToAelgai(String word) {
		/*//toString for engWords for debugging purposes
		for(ArrayList<String> e: engWords){
			for(String b:e){
				System.out.print(b);
			}
			System.out.println();
		}*/
		String translation = "";
		for (int i = 0; i < engWords.size(); i++) {
			translation = ""+engWords.get(i).size();
			for(int a = 0; a < engWords.get(i).size();a++){
				translation += engWords.get(i).get(a);
				if(word.equalsIgnoreCase(engWords.get(i).get(a))) {
					return aelgarWords.get(i);
				}
			}
			if(engWords.get(i).size()==1) {
				translation = "no translation for "+word;
				if (word.equalsIgnoreCase(engWords.get(i).get(0))) {
					return aelgarWords.get(i);
				}
			}
		}
		return translation;
	}

	public String translateSentenceToAelgai(String sentence){
		String[] words=stripSentence(sentence);
		String translation = "";
		int counter = 0;
		for (String word : words) {
			translation += translateWordToAelgai(word);
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
