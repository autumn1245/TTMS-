package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DB2 {
	private final String URL = "dbc:sqlserver://127.0.0.1:1433;DatabaseName=TTMS";
	private final String USERNAME = "sa";
	private final String PASSWORD = "sa";

	private Connection createConn() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //classLoader,加载对应驱动
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 查询并得到结果集
	public ResultSet execQuery(String sql,List list) {
		ResultSet set = null;
		try {
			Connection conn = createConn();
//			Statement stmt = conn.createStatement();
			PreparedStatement ps=conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				ps.setObject(i+1, list.get(i));
			}
//			set = stmt.executeQuery(sql);
			set=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}

	// 插入一条新纪录，并获取标识列的值
	public ResultSet getInsertObjectIDs(String sql,List list) {
		ResultSet rst = null;
		try {
			Connection conn = createConn();
//			Statement stmt = conn.createStatement();
			PreparedStatement ps=conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				ps.setObject(i+1, list.get(i));
			}
//			stmt.executeUpdate(insertSql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
//			rst = stmt.getGeneratedKeys();
			rst=ps.getGeneratedKeys();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rst;
	}

	// 插入、更新、删除
	public int execCommand(String sql,List list) {
		int flag = 0;
		try {
			Connection conn = createConn();
//			Statement stmt = conn.createStatement();
			PreparedStatement ps=conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				ps.setObject(i+1, list.get(i));
			}
//			flag = stmt.executeUpdate(sql);
			flag=ps.executeUpdate();
//			stmt.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	// 释放资源
	public void closeDB(Object obj) {
		try {
			if (obj instanceof Connection) {
				Connection con = (Connection) obj;
				con.close();
			}
			if (obj instanceof Statement) {
				Statement sta = (Statement) obj;
				sta.close();
			}
			if (obj instanceof ResultSet) {
				ResultSet rst = (ResultSet) obj;
				rst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
