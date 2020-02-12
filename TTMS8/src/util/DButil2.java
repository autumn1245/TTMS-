package util;

import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Ticket;

public class DButil2 {
	private final String URL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=TTMS3";
	private final String USERNAME = "sa";
	private final String PASSWORD = "sa";
	public Connection createConn() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //classLoader,加载对应驱动
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(true);		//点禁止自动提交，设置回退
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void GetProduseInsert(Connection conn,List<entity.Ticket> list ){
		CallableStatement ic =null ;
		if(conn==null){
			System.out.println("数据库连接失败");
		}else {
			try {
				 ic =conn.prepareCall("{call xgz(?,?)}");
				for (Ticket ticket : list) {
					
					
					int seat_id = ticket.getSeatId();
					int schedule_id = ticket.getScheduleId();
					
//					int studio_id = 2;
//					int seat_id = 1;
//					int schedule_id = 38;
					System.out.println("seat_id:"+ seat_id);
					System.out.println("schedule_id:" + schedule_id);
					ic.setInt(1, seat_id);
					ic.setInt(2, schedule_id);
					System.out.println("------------------");
					
					if(ic.execute()) {
						System.out.println("=================成功");				
					}else {
						System.out.println("===========不执行");
					}
					
					//ic.executeUpdate();
				}
			 }
			 catch (Exception ex) {
				 //TODO: handle exception
				 System.out.println("失败");
			 }
			
			finally {
				try {
					ic.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
//	public static void main(String[] args) throws Exception {
//		DButil2 dButil2 = new DButil2();
//		Ticket ticket1  = new Ticket(166, 1, 2, 12, 1, 38);
//		Ticket ticket2  = new Ticket(168, 3, 2, 12, 1, 38);
//		Ticket ticket3  = new Ticket(170, 5, 2, 12, 1, 38);
//		List<Ticket> list = new ArrayList<Ticket>();
//		list.add(ticket2);
//		list.add(ticket3);
//		list.add(ticket1);
//		Connection conn = dButil2.createConn();
//		GetProduseInsert(conn,list);
//		conn.close();
//	}
	
}
