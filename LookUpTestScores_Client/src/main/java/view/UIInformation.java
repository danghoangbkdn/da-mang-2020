package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import entity.Student;

import static utils.CompUtils.*;

@SuppressWarnings("serial")
public class UIInformation extends JFrame {

	private JScrollPane spInfor;
	private JTextArea taInfor;
	private JButton btCancel;

	private final Container con = getContentPane();
	private final Font font = new Font("Tahoma", Font.PLAIN, 13);
	private final Student student;

	public UIInformation(Student student) {
		this.student = student;

		initComponents();
		initEvents();
	}

	private void initComponents() {
		setSize(500, 250);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		con.setBackground(Color.CYAN);
		setTitle("CHI TIẾT");

		spInfor = new JScrollPane();
		taInfor = new JTextArea();
		btCancel = new JButton();

		taInfor.setFont(font);
//		taInfor.setRequestFocusEnabled(false);
		taInfor.setText(setText());
		spInfor.setBounds(15, 5, 455, 170);
		spInfor.setViewportView(taInfor);
//		spInfor.setRequestFocusEnabled(false);
		add(spInfor);

		btCancel.setFont(font);
		btCancel.setText("Cancel");
		btCancel.setFocusable(false);
		btCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btCancel.setBounds((500 - getPreWidth(btCancel)) / 2 - 5, 180, getPreWidth(btCancel), getPreHeight(btCancel));
		add(btCancel);
	}

	private String setText() {
		String result = "";
		DecimalFormat f = new DecimalFormat("##.00");
		String mediumCcore = f.format((student.getDiem1() + student.getDiem2() + student.getDiem3()) / 3);
		result += "SBD: " + student.getId() + "\nHọ Tên: " + student.getFullname() + "\t\tGiới Tính: "
				+ student.isGender() + "\nNgày Sinh: " + student.getDayOfBirth() + "\t\tCụm Thi: "
				+ student.getExamcluster() + "\nTrường THPT: " + student.getHightSchool() + "\nQuê Quán: "
				+ student.getAddress() + "\nNăm Thi: " + student.getYear() + "\t\t\tKhu Vực: " + student.getArea()
				+ "\nKẾT QUẢ THI:\n" + "Toán: " + student.getToan() + "\t\tVăn: " + student.getVan() + "\t\tTiếng Anh: "
				+ student.getAnh() + "\nĐiểm " + student.getMonTH() + ": " + mediumCcore + "\n" + student.getMon1()
				+ ": " + student.getDiem1() + "\t\t" + student.getMon2() + ": " + student.getDiem2() + "\t\t"
				+ student.getMon3() + ": " + student.getDiem3();
		return result;
	}

	private void initEvents() {
		btCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setVisible(isUndecorated());
			}
		});
	}
}