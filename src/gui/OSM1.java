package gui;

import javax.swing.*;

public class OSM1 {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();

			}
		});

	}

}
