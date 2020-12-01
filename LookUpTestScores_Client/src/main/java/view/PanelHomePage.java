package view;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PanelHomePage extends JPanel {

	private JLabel lbImage;
	private final Border outsideBorder = BorderFactory.createLineBorder(new Color(204, 0, 102), 2);
	private final Border insideBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "HOME",
			TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 13), new Color(0, 0, 204));
	private final Border border = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);

	public PanelHomePage() {
		initComponents();
		initEvents();
	}

	private void initComponents() {
		setSize(880, 500);
		setLayout(null);
		setBorder(border);

		lbImage = new JLabel();
		lbImage.setIcon(new ImageIcon(getClass().getResource("/images/1.jpg")));
		lbImage.setBounds(10, 20, 830, 430);
		add(lbImage);
	}

	private void initEvents() {
		new Thread(() -> {
			randomImage();
		}).start();
	}

	private void randomImage() {
		int tmp = -1;
		while (true) {
			int image = new Random().nextInt(2) + 1;
			String path = "/images/" + image + ".jpg";
			if (image != tmp) {
				lbImage.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage()));
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					System.out.print(e.getMessage());
				}
				tmp = image;
			}
		}
	}
}