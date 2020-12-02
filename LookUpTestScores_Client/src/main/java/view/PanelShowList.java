package view;

import static utils.CompUtils.*;
import static java.awt.BorderLayout.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import entity.RequestGetListStudentsByYear;
import entity.Student;
import tcp.ClientHandler;
import utils.StudentUtils;
import utils.StudentUtilsImpl;

@SuppressWarnings("serial")
public class PanelShowList extends JPanel {

	private JPanel pnTop;
	private JPanel pnLeftTop;
	private JPanel pnCenterTop;
	private JTextField tfSearch;
	private JLabel lbNot;
	private JButton btSearch;
	private JLabel lbYear;
	private JComboBox<String> cbYears;
	private JLabel lbExamCluster;
	private JComboBox<String> cbExamCluster;
	private JLabel lbTopStudent;
	private JComboBox<String> cbTopStudent;
	private JPanel pnBot;
	private JScrollPane spResult;
	private JTable tbResult;
	private DefaultTableModel tbmdResult;
	private String[] columnNames;

	private final BorderLayout borderLayout = new BorderLayout();
	private final BorderLayout borderLayoutPanelTop = new BorderLayout();
	private final Font font = new FontUIResource("Tamaho", Font.PLAIN, 13);
	private final Border borderTop = BorderFactory.createLineBorder(new Color(0, 51, 51), 2);
	private final Border borderLeftTop = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
			"TÌM KIẾM", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 13), new Color(0, 0, 204));
	private final Border borderCenterTop = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
			"TIỆN ÍCH", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 13), new Color(0, 0, 204));
	private final Border outsideBorderBot = BorderFactory.createLineBorder(new Color(204, 0, 102), 2);
	private final Border insideBorderBot = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
			"DANH SÁCH", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 13),
			new Color(0, 0, 204));
	private final Border borderBot = BorderFactory.createCompoundBorder(outsideBorderBot, insideBorderBot);

	private final ClientHandler client;
	private final StudentUtils studentUtils;
	private List<Student> students;
	private List<Student> stds;
	private int year = 2020;

	public PanelShowList(ClientHandler client) {
		this.client = client;
		studentUtils = new StudentUtilsImpl();

		initComponents();
		initEvents();
	}

	private void initComponents() {
		setSize(880, 500);
		setLayout(borderLayout);

		pnTop = new JPanel();
		pnLeftTop = new JPanel();
		pnCenterTop = new JPanel();
		tfSearch = new JTextField();
		lbNot = new JLabel();
		btSearch = new JButton();
		lbYear = new JLabel();
		cbYears = new JComboBox<>();
		lbExamCluster = new JLabel();
		cbExamCluster = new JComboBox<>();
		lbTopStudent = new JLabel();
		cbTopStudent = new JComboBox<>();
		pnBot = new JPanel();
		spResult = new JScrollPane();
		tbResult = new JTable();
		tbmdResult = new DefaultTableModel();
		columnNames = new String[] { "SBD", "Họ Tên", "Ngày Sinh", "Trường THPT", "Cụm Thi", "Năm Thi" };

		pnTop.setLayout(borderLayoutPanelTop);
		pnTop.setPreferredSize(new Dimension(0, 70));
		pnTop.setBorder(borderTop);

		pnLeftTop.setLayout(null);
		pnLeftTop.setPreferredSize(new Dimension(340, 0));
		pnLeftTop.setBackground(new Color(0, 255, 255));
		pnLeftTop.setBorder(borderLeftTop);

		tfSearch.setFont(font);
		tfSearch.setBounds(20, 23, 235, 35);
		pnLeftTop.add(tfSearch);

		lbNot.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbNot.setForeground(Color.RED);
		lbNot.setText("Không tìm thấy !");
		lbNot.setBounds(20, 6, getPreWidth(lbNot), getPreHeight(lbNot));
		lbNot.setVisible(false);
		pnLeftTop.add(lbNot);

		btSearch.setFont(font);
//		btnSearch.setBackground(new Color(0, 0, 0, 0));
//		btnSearch.setAutoscrolls(false);
//		btnSearch.setBorderPainted(false);
		Icon icon = new ImageIcon(getClass().getResource("/icons/search.jpg"));
		btSearch.setIcon(icon);
		btSearch.setBounds(270, 23, 50, 35);
		btSearch.setFocusable(false);
		btSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnLeftTop.add(btSearch);

		pnTop.add(pnLeftTop, WEST);

		pnCenterTop.setLayout(null);
		pnCenterTop.setBackground(new Color(0, 255, 255));
		pnCenterTop.setBorder(borderCenterTop);

		lbYear.setFont(font);
		lbYear.setText("Chọn năm: ");
		lbYear.setBounds(18, 30, getPreWidth(lbYear), getPreHeight(lbYear));
		pnCenterTop.add(lbYear);

		cbYears.setFont(font);
		cbYears.setModel(new DefaultComboBoxModel<>(new String[] { "2019", "2020" }));
		cbYears.setSelectedItem("2020");
		cbYears.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cbYears.setBounds(18 + getPreWidth(lbYear), 23, getPreWidth(cbYears) + 23, 35);
		pnCenterTop.add(cbYears);

		lbExamCluster.setFont(font);
		lbExamCluster.setText("Cụm thi: ");
		lbExamCluster.setBounds(182, 30, getPreWidth(lbExamCluster), getPreHeight(lbExamCluster));
		pnCenterTop.add(lbExamCluster);

		cbExamCluster.setFont(font);
		cbExamCluster.setModel(new DefaultComboBoxModel<String>(new String[] { "Hải Phòng", "Hà Nội", "Bắc Ninh",
				"Hà Tĩnh", "Đà Nẵng", "Khánh Hòa", "Đồng Nai", "Hồ Chí Minh", "Cần Thơ" }));
		cbExamCluster.setSelectedItem("Hà Nội");
		cbExamCluster.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cbExamCluster.setBounds(182 + getPreWidth(lbExamCluster), 23, getPreWidth(cbExamCluster), 35);
		pnCenterTop.add(cbExamCluster);

		lbTopStudent.setFont(font);
		lbTopStudent.setText("TOP 5: ");
		lbTopStudent.setBounds(350, 30, getPreWidth(lbTopStudent), getPreHeight(lbTopStudent));
		pnCenterTop.add(lbTopStudent);

		cbTopStudent.setFont(font);
		cbTopStudent.setModel(new DefaultComboBoxModel<>(
				new String[] { "All Subject", "Khối A", "Khối A1", "Khối B", "Khối C", "Khối D" }));
		cbTopStudent.setSelectedItem("All Subject");
		cbTopStudent.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cbTopStudent.setBounds(350 + getPreWidth(lbTopStudent), 23, getPreWidth(cbTopStudent), 35);
		pnCenterTop.add(cbTopStudent);

		pnTop.add(pnCenterTop, CENTER);

		add(pnTop, NORTH);

		pnBot.setLayout(null);
		pnBot.setBackground(Color.WHITE);
		pnBot.setBorder(borderBot);

		tbResult.setModel(tbmdResult);
		tbResult.setFont(font);
		tbResult.setRowHeight(20);
		tbResult.setBackground(new Color(255, 204, 204));
		spResult.setBounds(10, 18, 832, 361);
		setTableColumns();
		getListStudentsByYear();
		setTableRowsByYear();
		spResult.setViewportView(tbResult);
		pnBot.add(spResult);

		add(pnBot, CENTER);
	}

	private void initEvents() {
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					search();
				}
			}
		});

		btSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				search();
			}
		});

		tbResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String id = tbResult.getValueAt(tbResult.getSelectedRow(), 0).toString();
				new UIInformation(studentUtils.getStudent(students, id)).setVisible(true);
			}
		});

		cbYears.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tbmdResult.setRowCount(0);
				year = Integer.parseInt((String) cbYears.getSelectedItem());
				getListStudentsByYear();
				setTableRowsByYear();
			}
		});

		cbExamCluster.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tbmdResult.setRowCount(0);
				setTableRowsByExamCluster(String.valueOf(cbExamCluster.getSelectedItem()));
			}
		});

		cbTopStudent.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tbmdResult.setRowCount(0);
				setTableRowsByTopStudent(String.valueOf(cbTopStudent.getSelectedItem()));
			}
		});
	}

	private void setTableColumns() {
		tbmdResult.addColumn(columnNames);

		tbmdResult.setColumnIdentifiers(columnNames);
		tbResult.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbResult.getColumnModel().getColumn(0).setPreferredWidth(100);
		tbResult.getColumnModel().getColumn(1).setPreferredWidth(180);
		tbResult.getColumnModel().getColumn(2).setPreferredWidth(110);
		tbResult.getColumnModel().getColumn(3).setPreferredWidth(220);
		tbResult.getColumnModel().getColumn(4).setPreferredWidth(110);
		tbResult.getColumnModel().getColumn(5).setPreferredWidth(90);
	}

	private void setTableRowsByYear() {
		for (Object[] student : studentUtils.convertValue(students)) {
			tbmdResult.addRow(new Object[] { student[0], student[1], student[2], student[3], student[4], student[5] });
		}
	}

	private void getListStudentsByYear() {
		RequestGetListStudentsByYear request = new RequestGetListStudentsByYear("Get List<Student> students by year",
				year);
		students = client.getListStudentsByYear(request);
	}

	private void setTableRowsByExamCluster(String examCluster) {
		stds = students.stream().filter(s -> s.getExamcluster().equals(examCluster)).collect(Collectors.toList());
		for (Object[] student : studentUtils.convertValue(stds)) {
			tbmdResult.addRow(new Object[] { student[0], student[1], student[2], student[3], student[4], student[5] });
		}
	}

	private void setTableRowsByTopStudent(String request) {
		stds = studentUtils.getTopStudents(students, request);
		for (Object[] student : studentUtils.convertValue(stds)) {
			tbmdResult.addRow(new Object[] { student[0], student[1], student[2], student[3], student[4], student[5] });
		}
	}

	private void search() {
		String input = tfSearch.getText();
		String pattern = "[!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~}]+";

		if (input.isEmpty()) {
			return;
		} else if (!input.trim().matches(pattern)) {
			tbmdResult.setRowCount(0);
			List<Student> students = studentUtils.searchByIdAndName(this.students, input);
			if (students.size() > 0) {
				lbNot.setVisible(false);
				for (Object[] student : studentUtils.convertValue(students)) {
					tbmdResult.addRow(
							new Object[] { student[0], student[1], student[2], student[3], student[4], student[5] });
				}
			} else {
				lbNot.setVisible(true);
			}
		} else {
			lbNot.setVisible(true);
		}
	}
}