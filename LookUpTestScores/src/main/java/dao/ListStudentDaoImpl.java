package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionManager;
import connection.ConnectionManagerImpl;
import entity.Student;
import utils.SQLUtils;

public class ListStudentDaoImpl implements ListStudentDao {

	private final ConnectionManager connection;
	private Statement st;
	private ResultSet rs;

	public ListStudentDaoImpl() {
		connection = new ConnectionManagerImpl();
	}

	@Override
	public List<Student> getListStudentsByYear(int year) {
		List<Student> list = new ArrayList<>();
		Connection con = connection.getConnection();
		String query = "WITH ttts AS (\r\n"
				+ "	SELECT ts.SBD, ts.TenTS, ts.GioiTinh, ts.NgaySinh, ts.QueQuan, ts.TruongTHPT, ts.NamThi, ct.TenCT, kv.TenKV\r\n"
				+ "    FROM thisinh ts\r\n" + "    JOIN cumthi ct ON ts.MaCT = ct.MaCT\r\n"
				+ "	JOIN khuvuc kv ON ct.MaKV = kv.MaKV  WHERE ts.NamThi = " + year + "\n)\r\n"
				+ "SELECT tt.SBD, tt.TenTS, tt.GioiTinh, tt.NgaySinh, tt.QueQuan, tt.TruongTHPT, tt.NamThi, tt.TenCT, tt.TenKV, \r\n"
				+ "	   dt.Toan, dt.Van, dt.TiengAnh, dtth.Mon1, dtth.Mon2, dtth.Mon3, mtth.Monfirst, mtth.Monsecond, mtth.Monthird,\r\n"
				+ "       mth.TenTH\r\n" + "FROM ttts tt\r\n" + "JOIN diemthi dt ON tt.SBD = dt.SBD\r\n"
				+ "JOIN diemthitohop dtth ON tt.SBD = dtth.SBD\r\n" + "JOIN monthitohop mtth ON dt.MaTH = mtth.MaTH\r\n"
				+ "JOIN montohop mth ON dt.MaTH = mth.MaTH";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				Student student = new Student(rs.getString("SBD"), rs.getString("TenTS"), rs.getBoolean("GioiTinh"),
						rs.getDate("NgaySinh"), rs.getString("QueQuan"), rs.getString("TruongTHPT"),
						rs.getString("TenCT"), rs.getString("TenKV"), rs.getInt("NamThi"), rs.getFloat("Toan"),
						rs.getFloat("Van"), rs.getFloat("TiengAnh"), rs.getString("Monfirst"), rs.getFloat("Mon1"),
						rs.getString("Monsecond"), rs.getFloat("Mon2"), rs.getString("Monthird"), rs.getFloat("Mon3"),
						rs.getString("TenTH"));
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtils.closed(rs, st, con);
		}

		return list;
	}
}