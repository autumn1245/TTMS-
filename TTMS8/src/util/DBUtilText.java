package util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtilText {

	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		String sql = "select * from cs where User_name = '12'";
		ResultSet rs = dbUtil.execQuery(sql);
		try {
			while(rs.next()) {
				String tempName = rs.getString(2);
				String tempPassword = rs.getString(3);
				
				System.out.println(tempName + "  " + tempPassword);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
