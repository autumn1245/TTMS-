package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import entity.Schedule;
import entity.Ticket;
import idao.ITicketDAO;
import service.DateChange;
import util.DB2;
import util.DBUtil;

public class TicketDAO implements ITicketDAO{

	@Override
	public int insert(Ticket ticket) {
		int n = -1;

		int seat_id = ticket.getSeatId();
		int schedule_ID = ticket.getScheduleId();
		float ticket_price = ticket.getPrice();
		int ticket_status = ticket.getStatus();
		
		String sql = "insert into ticket(seat_id, schedule_ID, ticket_price, ticket_status) values ('"+ seat_id +"','"+ schedule_ID +"','"+ ticket_price +"','"+ ticket_status +"')";
		DBUtil dbUtil = new DBUtil();
		n = dbUtil.execCommand(sql);
		if (n > 0) {
			String sql2 = "select * from ticket where seat_id = '"+ seat_id +"' and schedule_ID = '"+ schedule_ID +"'";
			ResultSet rs = dbUtil.execQuery(sql2);
			int x = 0;
			try {
				while(rs.next()) {
					x = rs.getInt("ticket_id");
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			ticket.setId(x);
			System.out.println(ticket.getId());
			return 1;
		}
		return 0;
	}

	@Override
	public int update(Ticket ticket) {
		int n = -1;
		
		int ticket_id = ticket.getId();
		int seat_id = ticket.getSeatId();
		int schedule_ID = ticket.getScheduleId();
		float ticket_price = ticket.getPrice();
		int ticket_status = ticket.getStatus();
		
		String sql = "update ticket set seat_id = '"+ seat_id +"',"
				+ "schedule_ID = '"+ schedule_ID +"',"
					+ "ticket_price = '"+ ticket_price +"',"
						+ "ticket_status = '"+ ticket_status +"'"
							+ " where ticket_id = '"+ ticket_id +"'";
		
		n = new DBUtil().execCommand(sql);
		
		return n;
	}

	@Override
	public int delete(int id) {
		String sql = "delete from ticket where ticket_id = '"+ id +"'";
		DBUtil db = new DBUtil();
		return db.execCommand(sql);
	}

	@Override
	public List<Ticket> select(String condt) {
		List<Ticket> list = new ArrayList<Ticket>();
		condt.trim();
		String sql = "select * from ticket";
		if (!condt.isEmpty()) {
			sql+=" where " + condt;
		}
		DBUtil dbUtil = new DBUtil();
		ResultSet rs = dbUtil.execQuery(sql);
		try {
			while (rs.next()) {
				Ticket ticket = new Ticket();
				
				int ticket_id = rs.getInt("ticket_id");
				int seat_id = rs.getInt("seat_id");
				int schedule_ID = rs.getInt("schedule_ID");
				float ticket_price = rs.getFloat("ticket_price");
				int ticket_status = rs.getInt("ticket_status");
				
				ticket.setId(ticket_id);
				ticket.setSeatId(seat_id);
				ticket.setScheduleId(schedule_ID);
				ticket.setPrice(ticket_price);
				ticket.setStatus(ticket_status);
				
				list.add(ticket);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	
	}

}
