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

import entity.RequestSearch;
import entity.RequestShowAll;
import entity.Student;
import tcp.ClientHandler;

@SuppressWarnings("serial")
public class PanelShowList extends JPanel {

	private JPanel pnTop;
	private JTextField tfSearch;
	private JLabel lbNot;
	private JButton btSearch;
	private JButton btAll;
	private JLabel lbYear;
	private JComboBox<String> cbYears;
	private JPanel pnBot;
	private JScrollPane spResult;
	private JTable tbResult;
	private DefaultTableModel tbmdResult;
	private String[] columnNames;

	private final BorderLayout borderLayout = new BorderLayout();
	private final Font font = new FontUIResource("Tamaho", Font.PLAIN, 13);
	private final Border outsideBorderTop = BorderFactory.createLineBorder(new Color(0, 51, 51), 2);
	private final Border insideBorderTop = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
			"TÌM KIẾM", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 13), new Color(0, 0, 204));
	private final Border borderTop = BorderFactory.createCompoundBorder(outsideBorderTop, insideBorderTop);
	private final Border outsideBorderBot = BorderFactory.createLineBorder(new Color(204, 0, 102), 2);
	private final Border insideBorderBot = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
			"DANH SÁCH", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 13),
			new Color(0, 0, 204));
	private final Border borderBot = BorderFactory.createCompoundBorder(outsideBorderBot, insideBorderBot);

	private final ClientHandler client;

	public PanelShowList(ClientHandler client) {
		this.client = client;

		initComponents();
		initEvents();
	}

	private void initComponents() {
		setSize(880, 500);
		setLayout(borderLayout);

		pnTop = new JPanel();
		tfSearch = new JTextField();
		lbNot = new JLabel();
		btSearch = new JButton();
		btAll = new JButton();
		lbYear = new JLabel();
		cbYears = new JComboBox<>();
		pnBot = new JPanel();
		spResult = new JScrollPane();
		tbResult = new JTable();
		tbmdResult = new DefaultTableModel();
		columnNames = new String[] { "SBD", "Họ Tên", "Ngày Sinh", "Trường THPT", "Cụm Thi", "Năm Thi" };

		pnTop.setLayout(null);
		pnTop.setPreferredSize(new Dimension(0, 70));
		pnTop.setBackground(new Color(0, 255, 255));
		pnTop.setBorder(borderTop);

		tfSearch.setFont(font);
		tfSearch.setBounds(20, 13, 235, 35);
		pnTop.add(tfSearch);

		lbNot.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbNot.setForeground(Color.RED);
		lbNot.setText("Không tìm thấy !");
		lbNot.setBounds(20, 50, getPreWidth(lbNot), getPreHeight(lbNot));
		lbNot.setVisible(false);
		pnTop.add(lbNot);

		btSearch.setFont(font);
		btSearch.setText("Search");
		btSearch.setBounds(270, 13, getPreWidth(btSearch), 35);
		btSearch.setFocusable(false);
		btSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnTop.add(btSearch);

		btAll.setFont(font);
		btAll.setText("Show");
		btAll.setBounds(670 + getPreWidth(btSearch), 15, getPreWidth(btAll) + 8, 35);
		btAll.setFocusable(false);
		btAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnTop.add(btAll);

		lbYear.setFont(font);
		lbYear.setText("Chọn năm: ");
		lbYear.setBounds(490 + getPreWidth(btSearch), 25, getPreWidth(lbYear), getPreHeight(lbYear));
		pnTop.add(lbYear);

		cbYears.setFont(font);
		cbYears.setModel(new DefaultComboBoxModel<>(new String[] { "2019", "2020" }));
		cbYears.setSelectedItem("2020");
		cbYears.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cbYears.setBounds(570 + getPreWidth(lbYear), 15, getPreWidth(cbYears) + 23, 35);
		pnTop.add(cbYears);

		add(pnTop, NORTH);

		pnBot.setLayout(null);
		pnBot.setBackground(Color.WHITE);
		pnBot.setBorder(borderBot);

		tbResult.setModel(tbmdResult);
		tbResult.setFont(font);
		tbResult.setBackground(new Color(255, 204, 204));
		spResult.setBounds(10, 18, 832, 361);
		setTableColumns();
		setTableRows(Integer.parseInt((String) cbYears.getSelectedItem()));
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

		btAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				tbmdResult.setRowCount(0);
				setTableRows(Integer.parseInt((String) cbYears.getSelectedItem()));
			}
		});

		tbResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String id = tbResult.getValueAt(tbResult.getSelectedRow(), 0).toString();
				int year = Integer.parseInt(tbResult.getValueAt(tbResult.getSelectedRow(), 5).toString());
				Student student = client.getStudent(new RequestSearch("s1", btSearch.getText(), id, year));
				new UIInformation(student).setVisible(true);
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

	private void setTableRows(int year) {
		tbResult.setRowHeight(20);
		Object[][] std = client.getListStudentsByYear(new RequestShowAll("ShowAll", year));

		for (Object[] student : std) {
			tbmdResult.addRow(new Object[] { student[0], student[1], student[2], student[3], student[4], student[5] });
		}
	}

	private void search() {
		String input = tfSearch.getText();
		int year = Integer.parseInt((String) cbYears.getSelectedItem());
		String pattern = "[!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~}]+";

		if (input.isEmpty()) {
			return;
		} else if (!input.trim().matches(pattern)) {
			tbmdResult.setRowCount(0);
			List<Student> std = client.getListStudents(new RequestSearch("s2", btSearch.getText(), input, year));
			std.sort((s1, s2) -> s1.getId().compareTo(s2.getId()));
			if (std.size() > 0) {
				lbNot.setVisible(false);
				for (Object[] student : convertValue(std)) {
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

	private Object[][] convertValue(List<Student> students) {
		Object[][] std = new Object[students.size()][6];
		int count = 0;
		for (Student s : students) {
			std[count][0] = s.getId();
			std[count][1] = s.getFullname();
			std[count][2] = s.getDayOfBirth();
			std[count][3] = s.getHightSchool();
			std[count][4] = s.getExamcluster();
			std[count++][5] = s.getYear();
		}
		return std;
	}
}