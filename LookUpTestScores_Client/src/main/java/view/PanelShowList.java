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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import entity.RequestSearch;
import entity.RequestShowAll;
import entity.Student;
import tcp.ClientHandling;

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

	private final ClientHandling client;

	public PanelShowList(ClientHandling client) {
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
		pnTop.setPreferredSize(new Dimension(0, 100));
		pnTop.setBackground(Color.PINK);

		tfSearch.setFont(font);
		tfSearch.setBounds(100, 20, 280, 40);
		pnTop.add(tfSearch);

		lbNot.setFont(font);
		lbNot.setForeground(Color.RED);
		lbNot.setText("Không tìm thấy !");
		lbNot.setBounds(100, 70, getPreWidth(lbNot), getPreHeight(lbNot));
		lbNot.setVisible(false);
		pnTop.add(lbNot);

		btSearch.setFont(font);
		btSearch.setText("Search");
		btSearch.setBounds(400, 20, getPreWidth(btSearch) + 5, 40);
		btSearch.setFocusable(false);
		btSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnTop.add(btSearch);

		btAll.setFont(font);
		btAll.setText("ShowAll");
		btAll.setBounds(425 + getPreWidth(btSearch), 20, getPreWidth(btAll), 40);
		btAll.setFocusable(false);
		btAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnTop.add(btAll);

		lbYear.setFont(font);
		lbYear.setText("Chọn năm: ");
		lbYear.setBounds(555 + getPreWidth(btSearch), 30, getPreWidth(lbYear), getPreHeight(lbYear));
		pnTop.add(lbYear);

		cbYears.setFont(font);
		cbYears.setModel(new DefaultComboBoxModel<>(new String[] { "2019", "2020" }));
		cbYears.setSelectedItem("2020");
		cbYears.setBounds(635 + getPreWidth(lbYear), 20, getPreWidth(cbYears) + 20, 40);
		pnTop.add(cbYears);

		add(pnTop, NORTH);

		pnBot.setLayout(null);
		pnBot.setBackground(Color.PINK);

		tbResult.setModel(tbmdResult);
		tbResult.setFont(font);
		spResult.setBounds(90, 0, 700, 340);
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
		tbResult.getColumnModel().getColumn(1).setPreferredWidth(170);
		tbResult.getColumnModel().getColumn(2).setPreferredWidth(100);
		tbResult.getColumnModel().getColumn(3).setPreferredWidth(170);
		tbResult.getColumnModel().getColumn(4).setPreferredWidth(80);
		tbResult.getColumnModel().getColumn(5).setPreferredWidth(80);
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