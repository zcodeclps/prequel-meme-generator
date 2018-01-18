package spicymemes;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class MemeLibrary {
	private Map<String, List<String>> memesByEmotion; //Maps emotions to pictures
	private Map<String, String> picToQuote; //Maps each picture to it's associated Quote
	//private Map<String, String> keywords; // Maps memewords to pictures
	public static final String MEME_LIST = "memes.txt";
	
	public MemeLibrary() throws FileNotFoundException {
		memesByEmotion = new TreeMap<String, List<String>>();
		picToQuote = new TreeMap<String, String>();
		updateLibrary();
	}
	
	private void updateLibrary() throws FileNotFoundException {
		Scanner input = new Scanner(new File(MEME_LIST));
		while (input.hasNextLine()) {
			String nextFile = input.nextLine();
			String quote = input.nextLine();
			picToQuote.put(nextFile, quote);
			Scanner lineScan = new Scanner(input.nextLine());
			while (lineScan.hasNext()) {
				String nextEmotion = lineScan.next();
				if (!memesByEmotion.containsKey(nextEmotion)) {
					memesByEmotion.put(nextEmotion, new ArrayList<String>());
				}
				memesByEmotion.get(nextEmotion).add(nextFile);
			}
			lineScan.close();
		}
		input.close();
	}
	
	public String getQuote(String fileName) {
		return picToQuote.get(fileName);
	}
	
	public Boolean emotionPresent(String emotion) {
		return memesByEmotion.containsKey(emotion);
	}
	
	public Set<String> getEmotions() {
		return memesByEmotion.keySet();
	}
	
	public String pickMeme(String emotion) {
		/*if (!emotionPresent(emotion)) {
			throw new IllegalArgumentException();
		}*/
		List<String> potentialMemes = memesByEmotion.get(emotion);
		int item = new Random().nextInt(potentialMemes.size());
		String meme = potentialMemes.get(item);
		return meme;
		
	}
	
	public void addMeme(Meme m) throws IOException {
		Writer output = new BufferedWriter(new FileWriter(MEME_LIST, true));
		output.append(m.getFileName());
		output.append(m.getQuote());
		output.append(m.getEmotions());
		output.close();	
	}
}

