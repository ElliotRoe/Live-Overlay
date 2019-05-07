import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FrameRunner {

	private static LiveText liveString;

	private static double xCoor = 0, yCoor = 0;

	private static JFrame mainOverlay;
	private static JLabel liveLabel;

	private static int width = 200, height = 100;
	private static double screenWidth, screenHeight;

	public static void main(String[] args) {
		setup();
		update();
	}

	private static void update() {
		// TODO Auto-generated method stub

	}

	private static void setup() {

		// Constants
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screenSize.getWidth();
		screenHeight = screenSize.getHeight();

		xCoor = screenWidth - width;
		yCoor = screenHeight - height;

		mainOverlay = new JFrame("Overlay");
		liveLabel = new JLabel(liveString.toString(), SwingConstants.CENTER);

		Font segoeBold = Font.createFont(Font.TRUETYPE_FONT, new File("/data/segoe-ui-bold.ttf"));

		liveLabel.setOpaque(false);
		liveLabel.setForeground(Color.white);
		liveLabel.setFont(segoeBold);

		mainOverlay.getContentPane().setBackground(new Color(7, 27, 45));

		mainOverlay.setUndecorated(true);
		mainOverlay.setAlwaysOnTop(true);
		// Without this, the window is draggable from any non transparent
		// point, including points inside textboxes.
		mainOverlay.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);

		mainOverlay.setSize(width, height);

	}

}
