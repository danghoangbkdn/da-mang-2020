package view;

import static utils.CompUtils.*;
import static java.awt.BorderLayout.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import entity.RequestSearch;
import entity.Student;
import tcp.ClientHandler;

@SuppressWarnings("serial")
public class PanelSearch extends JPanel {

	private final BorderLayout borderLayout = new BorderLayout();

	private JPanel pnTop;
	private JLabel lbSuggestions;
	private JTextField tfSearch;
	private JButton btSearch;
	private JLabel lbYear;
	private JComboBox<String> cbYears;
	private JLabel lbNot;
	private JPanel pnBot;
	private JLabel lbId;
	private JLabel lbName;
	private JLabel lbGender;
	private JLabel lbDayOfBirth;
	private JLabel lbAddress;
	private JLabel lbSchool;
	private JLabel lbExamCluster;
	private JLabel lbArea;
	private JLabel lbExamYear;
	private JLabel lbTitleTwo;
	private JLabel lbScoreMath;
	private JLabel lbLiterature;
	private JLabel lbEnglish;
	private JLabel lbMediumCcore;
	private JLabel lbScoreMF;
	private JLabel lbScoreMS;
	private JLabel lbScoreMT;

	private final Font textFont = new FontUIResource("Tamaho", Font.PLAIN, 13);
	private final Font textFontTwo = new FontUIResource("Tamaho", Font.BOLD, 13);
	private final Font titleFont = new FontUIResource("Tamaho", Font.BOLD, 16);
	private final Border outsideBorderTop = BorderFactory.createLineBorder(new Color(0, 51, 51), 2);
	private final Border insideBorderTop = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
			"TÌM KIẾM", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 13), new Color(0, 0, 204));
	private final Border borderTop = BorderFactory.createCompoundBorder(outsideBorderTop, insideBorderTop);
	private final Border outsideBorderBot = BorderFactory.createLineBorder(new Color(0, 51, 51), 2);
	private final Border insideBorderBot = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
			"THÔNG TIN CHI TIẾT", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 13),
			new Color(0, 0, 204));
	private final Border borderBot = BorderFactory.createCompoundBorder(outsideBorderBot, insideBorderBot);

	private final ClientHandler client;

	public PanelSearch(ClientHandler client) {
		this.client = client;

		initComponents();
		initEvents();
	}

	private void initComponents() {
		setSize(880, 500);
		setLayout(borderLayout);

		lbSuggestions = new JLabel();
		tfSearch = new JTextField();
		btSearch = new JButton();
		lbYear = new JLabel();
		cbYears = new JComboBox<>();
		pnTop = new JPanel();
		lbNot = new JLabel();
		pnBot = new JPanel();
		lbId = new JLabel();
		lbName = new JLabel();
		lbGender = new JLabel();
		lbDayOfBirth = new JLabel();
		lbAddress = new JLabel();
		lbSchool = new JLabel();
		lbExamCluster = new JLabel();
		lbArea = new JLabel();
		lbExamYear = new JLabel();
		lbTitleTwo = new JLabel();
		lbScoreMath = new JLabel();
		lbLiterature = new JLabel();
		lbEnglish = new JLabel();
		lbMediumCcore = new JLabel();
		lbScoreMF = new JLabel();
		lbScoreMS = new JLabel();
		lbScoreMT = new JLabel();

		pnTop.setPreferredSize(new Dimension(0, 70));
		pnTop.setLayout(null);
		pnTop.setBackground(new Color(0, 255, 255));
		pnTop.setBorder(borderTop);

		lbSuggestions.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbSuggestions.setForeground(Color.RED);
		lbSuggestions.setText("Nhập SBD: (nămthi + xxx). VD: 2020001, ...");
		lbSuggestions.setBounds(330, 11, getPreWidth(lbSuggestions) + 2, getPreHeight(lbSuggestions));
		pnTop.add(lbSuggestions);

		tfSearch.setFont(textFont);
		tfSearch.setBounds(330, 25, 240, 35);
		pnTop.add(tfSearch);

		btSearch.setFont(textFont);
		btSearch.setText("Search");
		btSearch.setFocusable(false);
		btSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btSearch.setBounds(585, 25, getPreWidth(btSearch), 35);
		pnTop.add(btSearch);

		lbYear.setFont(textFont);
		lbYear.setText("Chọn năm: ");
		lbYear.setBounds(613 + getPreWidth(btSearch), 35, getPreWidth(lbYear), getPreHeight(lbYear));
		pnTop.add(lbYear);

		cbYears.setFont(textFont);
		cbYears.setModel(new DefaultComboBoxModel<>(new String[] { "2019", "2020" }));
		cbYears.setSelectedItem("2020");
		cbYears.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cbYears.setBounds(690 + getPreWidth(lbYear), 25, getPreWidth(cbYears) + 15, 35);
		pnTop.add(cbYears);

		add(pnTop, NORTH);

		pnBot.setLayout(null);
		pnBot.setBackground(new Color(0, 153, 255));
		pnBot.setBorder(borderBot);

		lbNot.setFont(titleFont);
		lbNot.setForeground(Color.RED);
		lbNot.setText("Không tìm thấy SBD này!");
		lbNot.setBounds((880 - getPreWidth(lbNot)) / 2, (250 - getPreHeight(lbNot)) / 2, getPreWidth(lbNot) + 5,
				getPreHeight(lbNot));
		lbNot.setVisible(false);
		pnBot.add(lbNot);

		lbId.setFont(textFontTwo);
		pnBot.add(lbId);

		lbName.setFont(textFontTwo);
		pnBot.add(lbName);

		lbGender.setFont(textFontTwo);
		pnBot.add(lbGender);

		lbDayOfBirth.setFont(textFontTwo);
		pnBot.add(lbDayOfBirth);

		lbAddress.setFont(textFontTwo);
		pnBot.add(lbAddress);

		lbSchool.setFont(textFontTwo);
		pnBot.add(lbSchool);

		lbExamCluster.setFont(textFontTwo);
		pnBot.add(lbExamCluster);

		lbArea.setFont(textFontTwo);
		pnBot.add(lbArea);

		lbId.setFont(textFontTwo);
		pnBot.add(lbExamYear);

		lbId.setFont(textFontTwo);
		pnBot.add(lbExamYear);

		lbTitleTwo.setFont(titleFont);
		lbTitleTwo.setText("KẾT QUẢ THI THPT");
		lbTitleTwo.setVisible(false);
		pnBot.add(lbTitleTwo);

		lbScoreMath.setFont(textFontTwo);
		pnBot.add(lbScoreMath);

		lbLiterature.setFont(textFontTwo);
		pnBot.add(lbLiterature);

		lbEnglish.setFont(textFontTwo);
		pnBot.add(lbEnglish);

		lbMediumCcore.setFont(textFontTwo);
		pnBot.add(lbMediumCcore);

		lbScoreMF.setFont(textFontTwo);
		pnBot.add(lbScoreMF);

		lbScoreMS.setFont(textFontTwo);
		pnBot.add(lbScoreMS);

		lbScoreMT.setFont(textFontTwo);
		pnBot.add(lbScoreMT);

		add(pnBot, CENTER);
	}

	private void initEvents() {
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int year = Integer.parseInt((String) cbYears.getSelectedItem());
					search(year);
				}
			}
		});

		btSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int year = Integer.parseInt((String) cbYears.getSelectedItem());
				search(year);
			}
		});
	}

	private void search(int year) {
		String id = tfSearch.getText();

		if (id.isEmpty()) {
			return;
		} else if (!id.trim().matches("[0-9]{7}")) {
			findNothing();
		} else {
			RequestSearch request = new RequestSearch("Search", id, year);
			System.out.println("[Request from Client] : " + request.getRequest());
			Student student = client.getStudent(request);
			if (student.getId() == null) {
				findNothing();
			} else {
				showInfor(student);
			}
		}
	}

	private void findNothing() {
		lbId.setVisible(false);
		lbName.setVisible(false);
		lbGender.setVisible(false);
		lbDayOfBirth.setVisible(false);
		lbAddress.setVisible(false);
		lbSchool.setVisible(false);
		lbExamCluster.setVisible(false);
		lbArea.setVisible(false);
		lbExamYear.setVisible(false);
		lbTitleTwo.setVisible(false);
		lbScoreMath.setVisible(false);
		lbLiterature.setVisible(false);
		lbEnglish.setVisible(false);
		lbMediumCcore.setVisible(false);
		lbScoreMF.setVisible(false);
		lbScoreMS.setVisible(false);
		lbScoreMT.setVisible(false);
		lbNot.setVisible(true);
	}

	private void showInfor(Student student) {
		DecimalFormat f = new DecimalFormat("##.00");
		String mediumCcore = f.format((student.getDiem1() + student.getDiem2() + student.getDiem3()) / 3);

		lbId.setFont(textFontTwo);
		lbId.setText("SDB: " + student.getId());
		lbId.setBounds(90, 35, getPreWidth(lbId), getPreHeight(lbId));
		lbId.setVisible(true);

		lbName.setFont(textFontTwo);
		lbName.setText("Họ Và Tên: " + student.getFullname());
		lbName.setBounds(90, 70, getPreWidth(lbName), getPreHeight(lbName));
		lbName.setVisible(true);

		lbGender.setFont(textFontTwo);
		lbGender.setText("Giới Tính: " + student.isGender());
		lbGender.setBounds(375, 70, getPreWidth(lbGender), getPreHeight(lbGender));
		lbGender.setVisible(true);

		lbDayOfBirth.setFont(textFontTwo);
		lbDayOfBirth.setText("Ngày Sinh: " + student.getDayOfBirth());
		lbDayOfBirth.setBounds(630, 70, getPreWidth(lbDayOfBirth), getPreHeight(lbDayOfBirth));
		lbDayOfBirth.setVisible(true);

		lbAddress.setFont(textFontTwo);
		lbAddress.setText("Quê Quán: " + student.getAddress());
		lbAddress.setBounds(90, 105, getPreWidth(lbAddress), getPreHeight(lbAddress));
		lbAddress.setVisible(true);

		lbSchool.setFont(textFontTwo);
		lbSchool.setText("Trường: " + student.getHightSchool());
		lbSchool.setBounds(375, 105, getPreWidth(lbSchool), getPreHeight(lbSchool));
		lbSchool.setVisible(true);

		lbExamCluster.setFont(textFontTwo);
		lbExamCluster.setText("Cụm Thi: " + student.getExamcluster());
		lbExamCluster.setBounds(90, 140, getPreWidth(lbExamCluster), getPreHeight(lbExamCluster));
		lbExamCluster.setVisible(true);

		lbArea.setFont(textFontTwo);
		lbArea.setText("Khu Vực: " + student.getArea());
		lbArea.setBounds(375, 140, getPreWidth(lbArea), getPreHeight(lbArea));
		lbArea.setVisible(true);

		lbExamYear.setFont(textFontTwo);
		lbExamYear.setText("Năm Thi: " + student.getYear());
		lbExamYear.setBounds(630, 140, getPreWidth(lbExamYear), getPreHeight(lbExamYear));
		lbExamYear.setVisible(true);

		lbTitleTwo.setBounds(90, 210, getPreWidth(lbTitleTwo), getPreHeight(lbTitleTwo));
		lbTitleTwo.setVisible(true);

		lbScoreMath.setFont(textFontTwo);
		lbScoreMath.setText("Toán: " + student.getToan());
		lbScoreMath.setBounds(90, 250, getPreWidth(lbScoreMath), getPreHeight(lbScoreMath));
		lbScoreMath.setVisible(true);

		lbLiterature.setFont(textFontTwo);
		lbLiterature.setText("Văn: " + student.getVan());
		lbLiterature.setBounds(375, 250, getPreWidth(lbLiterature), getPreHeight(lbScoreMath));
		lbLiterature.setVisible(true);

		lbEnglish.setFont(textFontTwo);
		lbEnglish.setText("Tiếng Anh: " + student.getAnh());
		lbEnglish.setBounds(630, 250, getPreWidth(lbEnglish), getPreHeight(lbScoreMath));
		lbEnglish.setVisible(true);

		lbMediumCcore.setFont(textFontTwo);
		lbMediumCcore.setText("Điểm TB " + student.getMonTH() + ": " + mediumCcore);
		lbMediumCcore.setBounds(90, 285, getPreWidth(lbMediumCcore), getPreHeight(lbMediumCcore));
		lbMediumCcore.setVisible(true);

		lbScoreMF.setFont(textFontTwo);
		lbScoreMF.setText(student.getMon1() + ": " + student.getDiem1());
		lbScoreMF.setBounds(90, 320, getPreWidth(lbScoreMF), getPreHeight(lbScoreMF));
		lbScoreMF.setVisible(true);

		lbScoreMS.setFont(textFontTwo);
		lbScoreMS.setText(student.getMon2() + ": " + student.getDiem2());
		lbScoreMS.setBounds(375, 320, getPreWidth(lbScoreMS), getPreHeight(lbScoreMS));
		lbScoreMS.setVisible(true);

		lbScoreMT.setFont(textFontTwo);
		lbScoreMT.setText(student.getMon3() + ": " + student.getDiem3());
		lbScoreMT.setBounds(630, 320, getPreWidth(lbScoreMT), getPreHeight(lbScoreMT));
		lbScoreMT.setVisible(true);

		lbNot.setVisible(false);
	}
}