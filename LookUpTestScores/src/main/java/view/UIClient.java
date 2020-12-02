package view;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import static javax.swing.JSplitPane.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import tcp.ClientHandler;

import static utils.CompUtils.*;

@SuppressWarnings("serial")
public class UIClient extends JFrame {

	private JPanel pnTop;
	private JPanel pnLeft;
	private JSplitPane spPane;
	private JButton btHome;
	private JButton btSearch;
	private JButton btShow;
	private JButton btExit;
	private JPanel pnCenter;
	private JButton defaultButton;

	private JLabel lbTitle;

	private final BorderLayout borderLayout = new BorderLayout();
	private final GridLayout gridLayout = new GridLayout(8, 0, 5, 5);
	private final BorderLayout layoutPanelCenter = new BorderLayout();

	private final Font titleFont = new FontUIResource("Tamaho", Font.BOLD, 36);
	private final Font textButton = new FontUIResource("Tamaho", Font.BOLD, 13);
	private final Font btFont = new FontUIResource("Tamaho", Font.BOLD, 17);
	private final Font btnFont = new FontUIResource("Tamaho", Font.BOLD, 20);
	private final Color btColor = new Color(0, 153, 255);
	private final Color btnColor = new Color(0, 153, 153);
	private final Border border = BorderFactory.createLineBorder(Color.RED, 2);
	private final Border defaultBorder = BorderFactory.createEmptyBorder();
	private final Border borderTop = BorderFactory.createLineBorder(new Color(0, 102, 0), 2);
	private final Border outsideBorderLeft = BorderFactory.createLineBorder(Color.RED, 2);
	private final Border insideBorderLeft = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
			"TÙY CHỌN", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 13), new Color(0, 0, 204));
	private final Border borderLeft = BorderFactory.createCompoundBorder(outsideBorderLeft, insideBorderLeft);

	private final Container con = getContentPane();

	private final ClientHandler client;

	public UIClient(Socket socket) {
		client = new ClientHandler(socket);
		client.run();

		initComponents();
		addEvent();
	}

	private void initComponents() {
		setSize(1080, 575);
		con.setLayout(borderLayout);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		setTitle("TraCứuĐiểmThi");
		Image image = new ImageIcon(getClass().getResource("/icons/icon.png")).getImage();
		setIconImage(image);

		addPanels();
	}

	private void addPanels() {
		pnTop = new JPanel();
		pnLeft = new JPanel();
		btHome = new JButton();
		btSearch = new JButton();
		btShow = new JButton();
		btExit = new JButton();
		pnCenter = new JPanel();
		spPane = new JSplitPane();

		lbTitle = new JLabel();

		pnTop.setPreferredSize(new Dimension(0, 75));
		pnTop.setBackground(new Color(0, 255, 0));
		pnTop.setBorder(borderTop);
		pnTop.setLayout(null);
		lbTitle.setFont(titleFont);
		lbTitle.setForeground(Color.BLACK);
		lbTitle.setText("Tra Cứu Điểm Thi");
		Image image = new ImageIcon(getClass().getResource("/icons/student.png")).getImage();
		Icon icon = new ImageIcon(image);
		lbTitle.setIcon(icon);
		lbTitle.setHorizontalTextPosition(SwingConstants.RIGHT);
		lbTitle.setBounds((1080 - getPreWidth(lbTitle)) / 2, (75 - getPreHeight(lbTitle)) / 2, getPreWidth(lbTitle),
				getPreHeight(lbTitle));
		pnTop.add(lbTitle);
		con.add(pnTop, NORTH);

//		spPane.setOrientation(VERTICAL_SPLIT);
//		spPane.setOneTouchExpandable(true);
		spPane.setOneTouchExpandable(true);
		spPane.setOrientation(HORIZONTAL_SPLIT);

		pnLeft.setPreferredSize(new Dimension(200, 500));
		pnLeft.setBackground(Color.DARK_GRAY);
		pnLeft.setBorder(borderLeft);
		pnLeft.setLayout(gridLayout);

		btHome.setFont(btFont);
		btHome.setText("Home");
		btHome.setForeground(Color.RED);
		btHome.setBackground(btnColor);
		btHome.setBorder(border);
		btHome.setFocusable(false);
		btHome.setCursor(new Cursor(Cursor.HAND_CURSOR));

		defaultButton = btHome;

		btSearch.setFont(textButton);
		btSearch.setText("Search");
		btSearch.setForeground(Color.BLACK);
		btSearch.setBackground(btColor);
		btSearch.setBorder(defaultBorder);
		btSearch.setFocusable(false);
		btSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btShow.setFont(textButton);
		btShow.setText("List");
		btShow.setForeground(Color.BLACK);
		btShow.setBackground(btColor);
		btShow.setBorder(defaultBorder);
		btShow.setFocusable(false);
		btShow.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btExit.setFont(textButton);
		btExit.setText("Exit");
		btExit.setForeground(Color.BLACK);
		btExit.setBackground(btColor);
		btExit.setBorder(defaultBorder);
		btExit.setFocusable(false);
		btExit.setCursor(new Cursor(Cursor.HAND_CURSOR));

		pnLeft.add(btHome);
		pnLeft.add(btSearch);
		pnLeft.add(btShow);
		pnLeft.add(btExit);
		spPane.add(pnLeft, LEFT);

//		pnCenter.setLayout(cardLayout);
//		pnCenter.add(new PanelHomePage(), "Home Page");
//		pnCenter.add(new PanelSearch(client), "Looking For Students");
//		pnCenter.add(new PanelShowList(client), "Show List Students");

		pnCenter.setLayout(layoutPanelCenter);
		pnCenter.add(new PanelHomePage(), CENTER);
		spPane.add(pnCenter, RIGHT);

		con.add(spPane, CENTER);
	}

	private void addEvent() {
		btHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				cardLayout.show(pnCenter, "Home Page");
				pnCenter.removeAll();
				pnCenter.add(new PanelHomePage(), CENTER);
				pnCenter.repaint();

				setDefaultButton(defaultButton);
				setButton(btHome);

				defaultButton = btHome;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (defaultButton == btHome) {
					btHome.setFont(btnFont);
				} else {
					btHome.setFont(btFont);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (defaultButton == btHome) {
					btHome.setFont(btFont);
				} else {
					btHome.setFont(textButton);
				}
			}
		});

		btSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				cardLayout.show(pnCenter, "Looking For Students");
				pnCenter.removeAll();
				pnCenter.add(new PanelSearch(client), CENTER);
				pnCenter.repaint();

				setDefaultButton(defaultButton);
				setButton(btSearch);

				defaultButton = btSearch;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (defaultButton == btSearch) {
					btSearch.setFont(btnFont);
				} else {
					btSearch.setFont(btFont);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (defaultButton == btSearch) {
					btSearch.setFont(btFont);
				} else {
					btSearch.setFont(textButton);
				}
			}
		});

		btShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				cardLayout.show(pnCenter, "Show List Students");
				pnCenter.removeAll();
				pnCenter.add(new PanelShowList(client), CENTER);
				pnCenter.repaint();

				setDefaultButton(defaultButton);
				setButton(btShow);

				defaultButton = btShow;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (defaultButton == btShow) {
					btShow.setFont(btnFont);
				} else {
					btShow.setFont(btFont);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (defaultButton == btShow) {
					btShow.setFont(btFont);
				} else {
					btShow.setFont(textButton);
				}
			}
		});

		btExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Image image = new ImageIcon(getClass().getResource("/icons/msg-error.png")).getImage();
				Icon icon = new ImageIcon(image);
				final int option = JOptionPane.showConfirmDialog(null, "Do you want to close ?", "Warning",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, icon);
				if (JOptionPane.YES_NO_OPTION == option) {
					System.exit(0);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btExit.setFont(btFont);
				btExit.setBackground(btColor);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btExit.setFont(textButton);
			}
		});
	}

	private void setDefaultButton(JButton bt) {
		bt.setFont(textButton);
		bt.setForeground(Color.BLACK);
		bt.setBackground(btColor);
		bt.setBorder(defaultBorder);
	}

	private void setButton(JButton bt) {
		bt.setFont(btFont);
		bt.setForeground(Color.RED);
		bt.setBackground(btnColor);
		bt.setBorder(border);
	}
}