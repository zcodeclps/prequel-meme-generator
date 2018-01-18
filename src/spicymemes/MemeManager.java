package spicymemes;

import java.io.IOException;
import java.util.Scanner;


public class MemeManager {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		MemeLibrary library = new MemeLibrary();
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the Star Wars Prequel Meme Generator");
		System.out.println("What would you like to do? (add, make, done) ");
		String command = input.next();
		while (!command.toLowerCase().equals("done")) {
			if (command.equals("add")) {
				promptAdd(input, library);
			} else if (command.equals("make")) {
				promptMake(library);
			} else if (!command.equals("done")) {
				System.out.println("Please enter a valid command ");
			}
			System.out.println("Now What?");
			command = input.next();
		}
	}

	private static void promptMake(MemeLibrary library) throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("Think of a meme-able situation ");
		String situation = input.nextLine();
		System.out.println("How does this situation make you feel?");
		System.out.println("Here are the options:");
		System.out.println(library.getEmotions());
		String emotion = input.next();
		System.out.println("So it makes you feel " + emotion);
		System.out.println("What do you want to name the fresh meme?");
		String name = input.next();
		String memeLocation = library.pickMeme(emotion);
		Meme result = new Meme(memeLocation, library.getQuote(memeLocation));
		result.addSituation(situation);
		result.makeMeme(name + ".png");
		System.out.println("done");
		input.close();
		
	}

	private static void promptAdd(Scanner input, MemeLibrary library) {
		// TODO Auto-generated method stub
		
	}

}
