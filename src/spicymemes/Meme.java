package spicymemes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;

public class Meme {
	private Set<String> emotions;
	private String movieQuote;
	private String situation;
	private String fileName;
	
	public Meme(String fileName, String movieQuote) throws IOException {
		this.movieQuote = movieQuote;
		this.fileName = fileName;
		emotions = new TreeSet<String>();
	}
	
	public void addSituation(String s) {
		this.situation = s;
	}

	public void addEmotion(String s) {
		emotions.add(s);
	}
	
	public String getEmotions() {
		String result = "";
		for (String s : emotions) {
			result += s + " ";
		}
		return result;
	}
	
	public String getQuote() {
		return movieQuote;
	}
	
	public void makeMeme(String destination) throws IOException {
		BufferedImage image = ImageIO.read(new File(fileName));
		BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight() + 200, BufferedImage.TYPE_INT_ARGB);
		Graphics g = result.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, image.getWidth(), image.getHeight() + 200);
		g.setColor(Color.white);
		g.drawImage(image, 0, 100, null);
		g.setFont(scaleFont(situation, image.getWidth(), g));
		g.drawString(situation, 0, 70);
		if (!movieQuote.equals("none")) {
			g.setFont(scaleFont(movieQuote, image.getWidth(), g));
			g.drawString(movieQuote, 0, result.getHeight() - 50);	
		}

		g.dispose();
		
		ImageIO.write(result, "png", new File(destination));	
	}
	
	public Font scaleFont(String text, int width, Graphics g) {
		float initSize = 5f;
		Font font = g.getFont().deriveFont(initSize);
		int theWidth = g.getFontMetrics(font).stringWidth(text);
		initSize = (width/theWidth) * initSize;
		return g.getFont().deriveFont(initSize);
	}
	
	public String getFileName(){
		return fileName;
	}

}
