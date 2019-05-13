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

	private static LiveOverlay lo;

	public static void main(String[] args) {
		setup();
		update();
	}

	private static void update() {
		for (;;) {
			lo.update();
		}

	}

	private static void setup() {

		lo = new LiveOverlay();

	}

}
