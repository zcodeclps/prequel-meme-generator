package spicymemes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		BufferedImage image = ImageIO.read(new File("maxresdefault.jpg"));
		BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight() + 100, BufferedImage.TYPE_INT_ARGB);
		Graphics g = result.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, image.getWidth(), image.getHeight() + 100);
		g.setColor(Color.white);
		g.drawImage(image, 0, 50, null);
		g.setFont(scaleFont("When you lose a Snapchat streak", image.getWidth(), g));
		g.drawString("When you lose a Snapchat streak", 0, 30);
		g.dispose();
		
		ImageIO.write(result, "png", new File("test.png"));

	}
	
	public static Font scaleFont(String text, int width, Graphics g) {
		float initSize = 5f;
		Font font = g.getFont().deriveFont(initSize);
		int theWidth = g.getFontMetrics(font).stringWidth(text);
		initSize = (width/theWidth) * initSize;
		return g.getFont().deriveFont(initSize);
	}

}
