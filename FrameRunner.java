import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FrameRunner {

	private static LiveText liveCost;
	private static LiveText liveTitle;

	private static double xCoor = 0, yCoor = 0;

	private static JFrame mainOverlay;
	private static JLabel liveLabel;

	private static int width = (int) (754f * (100f / 309f)), height = (int) (309 * (100f / 309f));
	private static double screenWidth, screenHeight;

	public static void main(String[] args) {
		setup();
		update();
	}

	private static void update() {
		for (;;) {
			liveLabel.setText(liveCost.toString());
		}

	}

	private static void setup() {

		// Constants
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screenSize.getWidth();
		screenHeight = screenSize.getHeight();

		xCoor = screenWidth - width;
		yCoor = screenHeight - height;

		mainOverlay = new JFrame("Overlay");
		liveCost = new BPSetCost("21002-1");
		liveTitle = new BPSetTitle("21002-1");

		liveCost.start();
		liveTitle.start();

		liveLabel = new JLabel(liveCost.toString(), SwingConstants.CENTER);

		Font segoeBold = null;
		File f = null;
		try {
			f = new File("segoe-ui-bold.ttf");
			FileInputStream input = new FileInputStream(f);
			segoeBold = Font.createFont(Font.TRUETYPE_FONT, input);

		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Whoops FontFormatException");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Whoops IOException");
		}

		liveLabel.setOpaque(false);
		liveLabel.setForeground(Color.white);
		liveLabel.setFont(segoeBold.deriveFont(40f));

		mainOverlay.getContentPane().setBackground(new Color(6, 27, 45));

		mainOverlay.setUndecorated(true);
		mainOverlay.setAlwaysOnTop(true);
		// Without this, the window is draggable from any non transparent
		// point, including points inside textboxes.
		mainOverlay.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);

		mainOverlay.setSize(width, height);
		mainOverlay.add(liveLabel, BorderLayout.CENTER);
		mainOverlay.setLocation((int) xCoor - 5, (int) yCoor - 40);
		mainOverlay.setVisible(true);

	}

}
