package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entity.Seat;
import idao.ISeatDAO;
import util.DB2;
import util.DBUtil;

public class SeatDAO implements ISeatDAO{

	@Override
	public int insert(Seat seat) {
		int studio_id = seat.getStudioId();
		int seat_row = seat.getRow();
		int seat_column = seat.getColumn();
		int seat_status = seat.getStatus();
		try{
			String sql="insert into seat(studio_id,seat_row,seat_column,seat_status) values ('"+ studio_id +"','"+ seat_row +"','"+ seat_column +"','"+ seat_status +"')";
	
			DBUtil dbUtil = new DBUtil();
			int n = dbUtil.execCommand(sql);
			if (n > 0) {
				String sql2 = "select * from seat where studio_id = '"+ studio_id +"'";
				ResultSet rs = dbUtil.execQuery(sql2);
				int seat_id = -1;
				while (rs.next()) {
					seat_id = rs.getInt("seat_id");
				}
				seat.setId(seat_id);
				return 1;
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Seat seat) {
		int studio_id = seat.getStudioId();
		int seat_row = seat.getRow();
		int seat_column = seat.getColumn();
		int seat_status = seat.getStatus();
		
		String sql="update seat set seat_status = '"+ seat_status +"' where studio_id = '"+ studio_id +"' and seat_row = '"+ seat_row +"' and seat_column = '"+ seat_column +"'";
		DBUtil dbUtil = new DBUtil();
		return dbUtil.execCommand(sql);
	}

	@Override
	public int delete(int id) {
		String sql=" delete from seat where seat_id = '"+ id +"'";
		DBUtil db = new DBUtil();
		return db.execCommand(sql);
	}

	@Override
	public List<Seat> select(String condt) {
		List<Seat> seatList = new ArrayList<Seat>();
		try {
			String sql = "select * from seat";
			condt.trim();
			if(!condt.isEmpty()){
				sql += " where "+ condt;
			}
			DBUtil db = new DBUtil();
			ResultSet rs = db.execQuery(sql);
			if(rs != null){
				while(rs.next()){
					Seat seat = new Seat();
					seat.setId(rs.getInt("seat_id"));
					seat.setStudioId(rs.getInt("studio_id"));
					seat.setRow(rs.getInt("seat_row"));
					seat.setColumn(rs.getInt("seat_column"));
					seat.setStatus(rs.getInt("seat_status"));
					seatList.add(seat);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seatList;
	}
}
