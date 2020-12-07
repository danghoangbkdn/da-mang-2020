package view;

import static utils.CompUtils.*;
import static java.awt.BorderLayout.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
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

import entity.RequestGetAll;
import entity.RequestGetListStudentsByRequest;
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
	private JComboBox<String> cbSearch;
	private JLabel lbYear;
	private JComboBox<String> cbYears;
	private JLabel lbExamCluster;
	private JComboBox<String> cbExamCluster;
	private JLabel lbTopStudent;
	private JComboBox<String> cbTopStudent;
	private JPanel pnCenter;
	private JScrollPane spResult;
	private JTable tbResult;
	private DefaultTableModel tbmdResult;
	private String[] columnNames;
	private JPanel pnBot;
	private JButton btBefore;
	private JButton btAfter;
	private JLabel lbPage;
	private JComboBox<Integer> cbPage;

	private final BorderLayout borderLayout = new BorderLayout();
	private final BorderLayout borderLayoutPanelTop = new BorderLayout();
	private final BorderLayout borderLayoutPanelCenter = new BorderLayout();
	private final FlowLayout flowLayout = new FlowLayout();
	private final Font font = new FontUIResource("Tamaho", Font.PLAIN, 13);
	private final Border borderTop = BorderFactory.createLineBorder(new Color(0, 51, 51), 2);
	private final Border borderLeftTop = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
			"TÌM KIẾM", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 13), new Color(0, 0, 204));
	private final Border borderCenterTop = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
			"TIỆN ÍCH", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 13), new Color(0, 0, 204));
	private final Border outsideBorderCenter = BorderFactory.createLineBorder(new Color(204, 0, 102), 2);
	private final Border insideBorderCenter = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
			"DANH SÁCH", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 13),
			new Color(0, 0, 204));
	private final Border borderCenter = BorderFactory.createCompoundBorder(outsideBorderCenter, insideBorderCenter);

	private final ClientHandler client;
	private final StudentUtils studentUtils;
	private List<Student> students;
	private Object requestName = 2020;
	private String name = "Year";
	private int allStudent;
	private int year = 2020;
	private int page = 1;
	private int numberPage;
	private Integer[] numberPages;

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
		cbSearch = new JComboBox<>();
		lbYear = new JLabel();
		cbYears = new JComboBox<>();
		lbExamCluster = new JLabel();
		cbExamCluster = new JComboBox<>();
		lbTopStudent = new JLabel();
		cbTopStudent = new JComboBox<>();
		pnCenter = new JPanel();
		spResult = new JScrollPane();
		tbResult = new JTable();
		tbmdResult = new DefaultTableModel();
		columnNames = new String[] { "SBD", "Họ Tên", "Ngày Sinh", "Trường THPT", "Cụm Thi", "Năm Thi" };
		pnBot = new JPanel();
		btBefore = new JButton();
		btAfter = new JButton();
		lbPage = new JLabel();
		cbPage = new JComboBox<>();

		pnTop.setLayout(borderLayoutPanelTop);
		pnTop.setPreferredSize(new Dimension(0, 70));
		pnTop.setBorder(borderTop);

		pnLeftTop.setLayout(null);
		pnLeftTop.setPreferredSize(new Dimension(280, 0));
		pnLeftTop.setBackground(new Color(0, 255, 255));
		pnLeftTop.setBorder(borderLeftTop);

		tfSearch.setFont(font);
		tfSearch.setBounds(22, 28, 235, 30);
		pnLeftTop.add(tfSearch);

		lbNot.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbNot.setForeground(Color.RED);
		lbNot.setText("Không tìm thấy !");
		lbNot.setBounds((getPreWidth(pnLeftTop) - getPreWidth(lbNot)) / 2, 14, getPreWidth(lbNot), getPreHeight(lbNot));
		lbNot.setVisible(false);
		pnLeftTop.add(lbNot);

//		cbSearch.setFont(font);
//		cbSearch.setModel(new DefaultComboBoxModel<String>(new String[] { "All", "Năm", "Cụm" }));
//		cbSearch.setSelectedItem("Năm");
//		cbSearch.setBounds(270, 23, getPreWidth(cbSearch), getPreHeight(cbSearch));
//		cbSearch.setFocusable(false);
//		cbSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		pnLeftTop.add(cbSearch);

		pnTop.add(pnLeftTop, WEST);

		pnCenterTop.setLayout(flowLayout);
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
		lbTopStudent.setText("TOP 10: ");
		lbTopStudent.setBounds(348, 30, getPreWidth(lbTopStudent), getPreHeight(lbTopStudent));
		pnCenterTop.add(lbTopStudent);

		cbTopStudent.setFont(font);
		cbTopStudent.setModel(new DefaultComboBoxModel<>(
				new String[] { "All Subject", "Khối A", "Khối A1", "Khối B", "Khối C", "Khối D" }));
		cbTopStudent.setSelectedItem("All Subject");
		cbTopStudent.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cbTopStudent.setBounds(348 + getPreWidth(lbTopStudent), 23, getPreWidth(cbTopStudent), 35);
		pnCenterTop.add(cbTopStudent);

		pnTop.add(pnCenterTop, CENTER);

		add(pnTop, NORTH);

		pnCenter.setLayout(borderLayoutPanelCenter);
		pnCenter.setPreferredSize(new Dimension(0, PanelShowList.this.getPreferredSize().height - getPreHeight(pnTop)));
		pnCenter.setBackground(Color.WHITE);
		pnCenter.setBorder(borderCenter);

		tbResult.setModel(tbmdResult);
		tbResult.setFont(font);
		tbResult.setRowHeight(20);
		tbResult.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tbResult.setBackground(new Color(255, 204, 204));
		spResult.setBounds(10, 18, getPreWidth(pnCenter) - 102, getPreHeight(pnCenter) - 77);
		setTableColumns();
		setListStudentsByYear();
		spResult.setViewportView(tbResult);
		pnCenter.add(spResult);

		add(pnCenter, CENTER);

		pnBot.setLayout(flowLayout);
		pnBot.setBorder(borderTop);
		pnBot.setBackground(new Color(0, 153, 153));
		flowLayout.setHgap(15);

		btBefore.setFont(font);
		btBefore.setText("Trước");
		btBefore.setFocusable(false);
		btBefore.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnBot.add(btBefore);

		lbPage.setFont(new Font("Tahome", Font.BOLD, 13));
		lbPage.setForeground(Color.BLUE);
		lbPage.setText("Trang");
		lbPage.setBounds(0, 0, getPreWidth(lbPage), getPreHeight(lbPage));
		pnBot.add(lbPage);

		cbPage.setFont(new Font("Tahome", Font.BOLD, 13));
		cbPage.setForeground(Color.BLUE);
		setComboxPage();
		cbPage.setModel(new DefaultComboBoxModel<>(numberPages));
		cbPage.setSelectedItem(numberPages[0]);
		cbPage.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnBot.add(cbPage);

		btAfter.setFont(font);
		btAfter.setText("Sau");
		btAfter.setFocusable(false);
		btAfter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btAfter.setBounds(0, 0, getPreWidth(btBefore), getPreHeight(btAfter));
		pnBot.add(btAfter);

		add(pnBot, SOUTH);
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

	private void setListStudentsByYear() {
		getAll("GetAll", year);
		RequestGetListStudentsByRequest request = new RequestGetListStudentsByRequest(name, year, year,
				numberPages.length, page);
		students = client.getListStudentsByRequest(request);

		for (Object[] student : studentUtils.convertValue(students)) {
			tbmdResult.addRow(new Object[] { student[0], student[1], student[2], student[3], student[4], student[5] });
		}
	}

	private void setListStudentsByYear1() {
		RequestGetListStudentsByRequest request = new RequestGetListStudentsByRequest(name, requestName, year,
				numberPages.length, page);
		System.out.println(name + "-" + requestName + "-" + year + "-" + page);
		students = client.getListStudentsByRequest(request);
		System.out.println(students.size());

		for (Object[] student : studentUtils.convertValue(students)) {
			tbmdResult.addRow(new Object[] { student[0], student[1], student[2], student[3], student[4], student[5] });
		}
	}

	private void getAll(String nameRequest, Object rq) {
		RequestGetAll request = new RequestGetAll(nameRequest, rq, year);
		allStudent = client.getAll(request);
		setComboxPage();
	}

	private void setComboxPage() {
		numberPage = (allStudent - 1) / 50 + 1;
		numberPages = new Integer[numberPage];
		for (int i = 1; i <= numberPage; i++) {
			numberPages[i - 1] = i;
		}
		cbPage.setModel(new DefaultComboBoxModel<>(numberPages));
		cbPage.setSelectedItem(numberPages[0]);
	}

	private void initEvents() {
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});

		cbSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

			}
		});

		tbResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String id = tbResult.getValueAt(tbResult.getSelectedRow(), 0).toString();
				new FrameInformation(studentUtils.getStudent(students, id)).setVisible(true);
			}
		});

		cbYears.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				page = 1;
				name = "Year";
				requestName = (String) cbYears.getSelectedItem();
				tbmdResult.setRowCount(0);
				year = Integer.parseInt((String) requestName);
				setListStudentsByYear();
			}
		});

		cbExamCluster.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				name = "ExamCluster";
				tbmdResult.setRowCount(0);
				requestName = (String) cbExamCluster.getSelectedItem();
				setTableRowsByExamCluster(String.valueOf(cbExamCluster.getSelectedItem()));
			}
		});

		cbTopStudent.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				name = "Top";
				tbmdResult.setRowCount(0);
				requestName = (String) cbTopStudent.getSelectedItem();
				setTableRowsByTopStudent(String.valueOf(cbTopStudent.getSelectedItem()));
			}
		});

		cbPage.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tbmdResult.setRowCount(0);
				page = (int) cbPage.getSelectedItem();
				setListStudentsByYear1();
			}
		});

		btBefore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (page > 1) {
					page--;
					setListStudentsByYear1();
					cbPage.setSelectedItem(page);
				}
			}
		});

		btAfter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (page < numberPage) {
					page++;
					setListStudentsByYear1();
					cbPage.setSelectedItem(page);
				}
			}
		});
	}

	private void setTableRowsByExamCluster(String examCluster) {
		page = 1;
		getAll("GetByExamCluster", examCluster);
		RequestGetListStudentsByRequest request = new RequestGetListStudentsByRequest("ExamCluster", examCluster, year,
				numberPages.length, page);
		students = client.getListStudentsByRequest(request);
		System.out.println(students.size());
		for (Object[] student : studentUtils.convertValue(students)) {
			tbmdResult.addRow(new Object[] { student[0], student[1], student[2], student[3], student[4], student[5] });
		}
	}

	private void setTableRowsByTopStudent(String requestTop) {
		page = 1;
		getAll("GetByTop", 10);
		RequestGetListStudentsByRequest request = new RequestGetListStudentsByRequest("TopStudent", requestTop, year,
				numberPages.length, page);
		students = client.getListStudentsByRequest(request);
		for (Object[] student : studentUtils.convertValue(students)) {
			tbmdResult.addRow(new Object[] { student[0], student[1], student[2], student[3], student[4], student[5] });
		}
	}

	private void search() {
		String input = tfSearch.getText();
		String pattern = "[!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~}]+";

		if (!input.trim().matches(pattern)) {
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
			tbmdResult.setRowCount(0);
			lbNot.setVisible(true);
		}
	}
}