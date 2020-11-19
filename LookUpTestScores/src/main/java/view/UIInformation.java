package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import controller.ListStudentControllerImpl;
import entity.Student;

import static utils.CompUtils.*;

@SuppressWarnings("serial")
public class UIInformation extends JFrame {

	private JScrollPane spInfor;
	private JTextArea taInfor;
	private JButton btCancel;

	private final Container con = getContentPane();
	private final Font font = new Font("Tahoma", Font.PLAIN, 13);
	private final String id;
	private final int year;
	private final ListStudentControllerImpl controller;

	public UIInformation(String id, int year) {
		this.id = id;
		this.year = year;
		controller = new ListStudentControllerImpl();

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
		List<Student> students = controller.getListStudentByYear(year);
		for (Student s : students) {
			if (s.getId().equals(id)) {
				DecimalFormat f = new DecimalFormat("##.00");
				String mediumCcore = f.format((s.getDiem1() + s.getDiem2() + s.getDiem3()) / 3);
				result += "SBD: " + id + "\nHọ Tên: " + s.getFullname() + "\t\tGiới Tính: " + s.isGender()
						+ "\nNgày Sinh: " + s.getDayOfBirth() + "\t\tCụm Thi: " + s.getExamcluster() + "\nTrường THPT: "
						+ s.getHightSchool() + "\nQuê Quán: " + s.getAddress() + "\nNăm Thi: " + s.getYear()
						+ "\t\t\tKhu Vực: " + s.getArea() + "\nKẾT QUẢ THI:\n" + "Toán: " + s.getToan() + "\t\tVăn: "
						+ s.getVan() + "\t\tTiếng Anh: " + s.getAnh() + "\nĐiểm " + s.getMonTH() + ": " + mediumCcore
						+ "\n" + s.getMon1() + ": " + s.getDiem1() + "\t\t" + s.getMon2() + ": " + s.getDiem2() + "\t\t"
						+ s.getMon3() + ": " + s.getDiem3();
				break;
			}
		}
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