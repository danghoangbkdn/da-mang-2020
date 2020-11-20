package view;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelHomePage extends JPanel {

	private JLabel lbImage;

	public PanelHomePage() {
		initComponents();
		initEvents();
	}

	private void initComponents() {
		setSize(880, 500);
		setLayout(null);

		lbImage = new JLabel();
		lbImage.setIcon(new ImageIcon(getClass().getResource("/images/1.jpg")));
		lbImage.setBounds(0, 0, 880, 500);
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
			int image = new Random().nextInt(6) + 1;
			String path = "/images/" + image + ".jpg";
			if (image != tmp) {
				lbImage.setIcon(new ImageIcon(getClass().getResource(path)));
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