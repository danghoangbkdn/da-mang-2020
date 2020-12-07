package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionManager;
import connection.ConnectionManagerImpl;
import utils.SQLUtils;

public class InsertDataImpl implements InsertData {

	private final ConnectionManager connection;
	private Statement st;

	public InsertDataImpl() {
		connection = new ConnectionManagerImpl();
	}

	@Override
	public boolean setData(String table, String[] student) {
		Connection con = connection.getConnection();
		int affectedRows = 0;
		try {
			while (affectedRows < student.length) {
				System.out.println(student[affectedRows]);
//				String query = "INSET INTO " + table + " VALUES " + student[affectedRows];
				st = con.createStatement();
//				st.executeUpdate(query);
				affectedRows++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtils.closed(st, con);
		}

		return affectedRows != 0;
	}
}