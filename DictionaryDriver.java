import java.util.Scanner;

public class DictionaryDriver{
	public static Scanner kbr = new Scanner(System.in);
    public static Scanner kbr2 = new Scanner(System.in);
	private static Dictionary lang;
	
	public static void main (String args[]){
		boolean keepGoing = true;
		System.out.println("Let's generate a language:");
		lang = new Dictionary();
		while(keepGoing){
			/*System.out.println("What do you want to translate?");
			String line = kbr.nextLine();
			System.out.println("--"+lang.trueTranslate(line)+"--");
			System.out.println("Do you want to translate more, generate a new language or stop?");
			System.out.println("Type STOP, NEW, or MORE.");
			String line2 = kbr.nextLine();
			if(line2.equalsIgnoreCase("STOP")){
				keepGoing = false;
			}else if(line2.equalsIgnoreCase("NEW")){
				lang = new Dictionary();
			}*/
			System.out.println("English to Aelgai:");
			String line = kbr.nextLine();
			System.out.println("--"+lang.translateSentenceToAelgai(line)+"--");
		}
	}
}
