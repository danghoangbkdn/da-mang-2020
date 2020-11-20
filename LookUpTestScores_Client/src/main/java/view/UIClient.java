package view;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.*;
import java.awt.CardLayout;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import static javax.swing.JSplitPane.*;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.plaf.FontUIResource;

import tcp.ClientHandling;

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

	private JLabel lbTitle;

	private final BorderLayout borderLayout = new BorderLayout();
	private final GridLayout gridLayout = new GridLayout(10, 0);
	private final CardLayout cardLayout = new CardLayout();

	private final Font titleFont = new FontUIResource("Tamaho", Font.BOLD, 36);
	private final Font textButton = new FontUIResource("Tamaho", Font.PLAIN, 12);

	private final Container con = getContentPane();

	private final ClientHandling client;

	public UIClient(Socket socket) {
		client = new ClientHandling(socket);
		client.run();

		initComponents();
		addEvent();
	}

	private void initComponents() {
		setSize(1080, 600);
		con.setLayout(borderLayout);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		setTitle("TraCứuĐiểmThi");
		Image image = new ImageIcon(getClass().getResource("/icon/icon.png")).getImage();
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

		pnTop.setPreferredSize(new Dimension(0, 100));
		pnTop.setBackground(Color.BLUE);
		pnTop.setLayout(null);
		lbTitle.setFont(titleFont);
		lbTitle.setForeground(Color.BLACK);
		lbTitle.setText("Tra Cứu Điểm Thi");
		lbTitle.setBounds((1080 - getPreWidth(lbTitle)) / 2, (100 - getPreHeight(lbTitle)) / 2, getPreWidth(lbTitle),
				getPreHeight(lbTitle));
		pnTop.add(lbTitle);
		con.add(pnTop, NORTH);

//		spPane.setOrientation(VERTICAL_SPLIT);
//		spPane.setOneTouchExpandable(true);
		spPane.setOneTouchExpandable(true);
		spPane.setOrientation(HORIZONTAL_SPLIT);

		pnLeft.setPreferredSize(new Dimension(200, 500));
		pnLeft.setBackground(Color.DARK_GRAY);
		pnLeft.setLayout(gridLayout);
		btHome.setFont(new Font("Tahoma", Font.BOLD, 15));
		btHome.setText("Home Page");
		btHome.setFocusable(false);
		btHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btSearch.setFont(textButton);
		btSearch.setText("Looking For Students");
		btSearch.setFocusable(false);
		btSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btShow.setFont(textButton);
		btShow.setText("Show List Students");
		btShow.setFocusable(false);
		btShow.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btExit.setFont(textButton);
		btExit.setText("Exit");
		btExit.setFocusable(false);
		btExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnLeft.add(btHome);
		pnLeft.add(btSearch);
		pnLeft.add(btShow);
		pnLeft.add(btExit);
		spPane.add(pnLeft, LEFT);

		pnCenter.setLayout(cardLayout);
		pnCenter.add(new PanelHomePage(), "Home Page");
		pnCenter.add(new PanelSearch(client), "Looking For Students");
		pnCenter.add(new PanelShowList(client), "Show List Students");

		spPane.add(pnCenter, RIGHT);

		con.add(spPane, CENTER);
	}

	private void addEvent() {
		btHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cardLayout.show(pnCenter, "Home Page");
				btHome.setFont(new Font("Tahoma", Font.BOLD, 15));
				btSearch.setFont(textButton);
				btShow.setFont(textButton);
			}
		});

		btSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cardLayout.show(pnCenter, "Looking For Students");
				btSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
				btHome.setFont(textButton);
				btShow.setFont(textButton);
			}
		});

		btShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cardLayout.show(pnCenter, "Show List Students");
				btShow.setFont(new Font("Tahoma", Font.BOLD, 15));
				btHome.setFont(textButton);
				btSearch.setFont(textButton);
			}
		});

		btExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
	}
}
