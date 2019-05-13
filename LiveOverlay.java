import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LiveOverlay {

	private LiveText liveCost;
	private LiveText liveTitle;

	private double xCoor = 0, yCoor = 0;

	private JFrame mainOverlay;
	private JLabel liveLabel;
	private JLabel liveLabelTitle;

	private int width = (int) (754f * (100f / 309f)), height = (int) (309 * (100f / 309f));
	private double screenWidth, screenHeight;
	private JPanel mainPanel;
	private JPanel titlePanel;

	public LiveOverlay() {
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
		liveLabelTitle = new JLabel(liveTitle.toString());

		Font segoeBold = null;
		Font segoeLight = null;
		File f = null;
		try {
			f = new File("segoe-ui-bold.ttf");
			FileInputStream input = new FileInputStream(f);
			segoeBold = Font.createFont(Font.TRUETYPE_FONT, input);

			f = new File("segoeuil.ttf");
			input = new FileInputStream(f);
			segoeLight = Font.createFont(Font.TRUETYPE_FONT, input);

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

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.CYAN);
		mainPanel.add(liveLabel);

		titlePanel = new JPanel();
		titlePanel.setBackground(Color.green);
		titlePanel.add(liveLabelTitle);

		liveLabelTitle.setOpaque(false);
		liveLabelTitle.setForeground(Color.white);
		liveLabelTitle.setFont(segoeLight.deriveFont(17.5f));

		mainOverlay.getContentPane().setBackground(new Color(6, 27, 45));

		mainOverlay.setUndecorated(true);
		mainOverlay.setAlwaysOnTop(true);
		// Without this, the window is draggable from any non transparent
		// point, including points inside textboxes.
		mainOverlay.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);

		mainOverlay.add(mainPanel, BorderLayout.CENTER);
		mainOverlay.add(titlePanel, BorderLayout.NORTH);

		mainOverlay.setSize(width, height);
		mainOverlay.setLocation((int) xCoor - 5, (int) yCoor - 40);
		mainOverlay.setVisible(true);

	}

	public void update() {

		for (;;) {
			liveLabel.setText(liveCost.toString());
			liveLabelTitle.setText(liveTitle.toString());
		}

	}

}
