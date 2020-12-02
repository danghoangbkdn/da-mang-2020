package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import entity.Student;

import static utils.CompUtils.*;

@SuppressWarnings("serial")
public class UIInformation extends JFrame {
	private JPanel pnTop;
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
	private JButton btCancel;

	private final Font textFontTwo = new FontUIResource("Tamaho", Font.BOLD, 12);
	private final Font titleFontTwo = new FontUIResource("Tamaho", Font.BOLD, 15);
	private final BorderLayout borderLayout = new BorderLayout();
	private final Border borderTop = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "THÔNG TIN",
			TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 11), new Color(0, 0, 204));

	private final Student student;

	public UIInformation(Student student) {
		this.student = student;

		setUndecorated(true);
		initComponents();
		initEvents();
	}

	private void initComponents() {
		setSize(500, 290);
		setLayout(borderLayout);
		setLocationRelativeTo(null);

		pnTop = new JPanel();
		pnTop.setLayout(null);
		pnTop.setBackground(new Color(0, 153, 255));
		pnTop.setPreferredSize(new Dimension(0, 260));
		pnTop.setBorder(borderTop);

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
		btCancel = new JButton();

		lbId.setFont(textFontTwo);
		pnTop.add(lbId);
		lbName.setFont(textFontTwo);
		pnTop.add(lbName);
		lbGender.setFont(textFontTwo);
		pnTop.add(lbGender);
		lbDayOfBirth.setFont(textFontTwo);
		pnTop.add(lbDayOfBirth);
		lbAddress.setFont(textFontTwo);
		pnTop.add(lbAddress);
		lbSchool.setFont(textFontTwo);
		pnTop.add(lbSchool);
		lbExamCluster.setFont(textFontTwo);
		pnTop.add(lbExamCluster);
		lbArea.setFont(textFontTwo);
		pnTop.add(lbArea);
		lbId.setFont(textFontTwo);
		pnTop.add(lbExamYear);
		lbId.setFont(textFontTwo);
		pnTop.add(lbExamYear);
		lbTitleTwo.setFont(titleFontTwo);
		lbTitleTwo.setText("KẾT QUẢ THI THPT");
		pnTop.add(lbTitleTwo);
		lbScoreMath.setFont(textFontTwo);
		pnTop.add(lbScoreMath);
		lbLiterature.setFont(textFontTwo);
		pnTop.add(lbLiterature);
		lbEnglish.setFont(textFontTwo);
		pnTop.add(lbEnglish);
		lbMediumCcore.setFont(textFontTwo);
		pnTop.add(lbMediumCcore);
		lbScoreMF.setFont(textFontTwo);
		pnTop.add(lbScoreMF);
		lbScoreMS.setFont(textFontTwo);
		pnTop.add(lbScoreMS);
		lbScoreMT.setFont(textFontTwo);
		pnTop.add(lbScoreMT);

		lbId.setFont(textFontTwo);
		lbId.setText("SDB: " + student.getId());
		lbId.setBounds(15, 15, getPreWidth(lbId), getPreHeight(lbId));

		lbName.setFont(textFontTwo);
		lbName.setText("Họ và tên: " + student.getFullname());
		lbName.setBounds(15, 45, getPreWidth(lbName), getPreHeight(lbName));

		lbGender.setFont(textFontTwo);
		lbGender.setText("Giới tính: " + student.isGender());
		lbGender.setBounds(215, 45, getPreWidth(lbGender), getPreHeight(lbGender));

		lbDayOfBirth.setFont(textFontTwo);
		lbDayOfBirth.setText("Ngày sinh: " + student.getDayOfBirth());
		lbDayOfBirth.setBounds(365, 45, getPreWidth(lbDayOfBirth), getPreHeight(lbDayOfBirth));

		lbAddress.setFont(textFontTwo);
		lbAddress.setText("Quê quán: " + student.getAddress());
		lbAddress.setBounds(15, 75, getPreWidth(lbAddress), getPreHeight(lbAddress));

		lbSchool.setFont(textFontTwo);
		lbSchool.setText("Trường THPT: " + student.getHightSchool());
		lbSchool.setBounds(215, 75, getPreWidth(lbSchool), getPreHeight(lbSchool));

		lbExamCluster.setFont(textFontTwo);
		lbExamCluster.setText("Cụm thi: " + student.getExamcluster());
		lbExamCluster.setBounds(15, 105, getPreWidth(lbExamCluster), getPreHeight(lbExamCluster));

		lbArea.setFont(textFontTwo);
		lbArea.setText("Khu vực: " + student.getArea());
		lbArea.setBounds(215, 105, getPreWidth(lbArea), getPreHeight(lbArea));

		lbExamYear.setFont(textFontTwo);
		lbExamYear.setText("Năm thi: " + student.getYear());
		lbExamYear.setBounds(365, 105, getPreWidth(lbExamYear), getPreHeight(lbExamYear));

		lbTitleTwo.setBounds(15, 140, getPreWidth(lbTitleTwo), getPreHeight(lbTitleTwo));

		lbScoreMath.setFont(textFontTwo);
		lbScoreMath.setText("Toán: " + student.getToan());
		lbScoreMath.setBounds(15, 175, getPreWidth(lbScoreMath), getPreHeight(lbScoreMath));

		lbLiterature.setFont(textFontTwo);
		lbLiterature.setText("Văn: " + student.getVan());
		lbLiterature.setBounds(215, 175, getPreWidth(lbLiterature), getPreHeight(lbScoreMath));

		lbEnglish.setFont(textFontTwo);
		lbEnglish.setText("Tiếng Anh: " + student.getAnh());
		lbEnglish.setBounds(365, 175, getPreWidth(lbEnglish), getPreHeight(lbScoreMath));

		DecimalFormat f = new DecimalFormat("##.00");
		String mediumCcore = f.format((student.getDiem1() + student.getDiem2() + student.getDiem3()) / 3);
		lbMediumCcore.setFont(textFontTwo);
		lbMediumCcore.setText("Điểm TB " + student.getMonTH() + ": " + mediumCcore);
		lbMediumCcore.setBounds(15, 205, getPreWidth(lbMediumCcore), getPreHeight(lbMediumCcore));

		lbScoreMF.setFont(textFontTwo);
		lbScoreMF.setText(student.getMon1() + ": " + student.getDiem1());
		lbScoreMF.setBounds(15, 235, getPreWidth(lbScoreMF), getPreHeight(lbScoreMF));

		lbScoreMS.setFont(textFontTwo);
		lbScoreMS.setText(student.getMon2() + ": " + student.getDiem2());
		lbScoreMS.setBounds(215, 235, getPreWidth(lbScoreMS), getPreHeight(lbScoreMS));

		lbScoreMT.setFont(textFontTwo);
		lbScoreMT.setText(student.getMon3() + ": " + student.getDiem3());
		lbScoreMT.setBounds(365, 235, getPreWidth(lbScoreMT), getPreHeight(lbScoreMT));

		pnBot = new JPanel();
		pnBot.setLayout(null);
		pnBot.setBackground(new Color(0, 102, 102));

		btCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btCancel.setBackground(Color.RED);
		btCancel.setText("Cancel");
		btCancel.setFocusable(false);
		btCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btCancel.setBounds(485 - getPreWidth(btCancel), 2, getPreWidth(btCancel), getPreHeight(btCancel));
		pnBot.add(btCancel);

		add(pnTop, BorderLayout.NORTH);
		add(pnBot, BorderLayout.CENTER);
	}

	private void initEvents() {
		btCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				UIInformation.this.setVisible(false);
			}
		});
	}
}