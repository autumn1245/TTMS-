package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entity.ZhiFu;
import idao.IZhiFuDAO;
import service.DateChange;
import util.DBUtil;

public class ZhiFuDAO implements IZhiFuDAO{

	@Override
	public ZhiFu select(int schedule_ID, int seat_id) {
		ZhiFu zhiFu = new ZhiFu();
		
		String sql = "select play_name, schedule_time, studio_name, seat_row, seat_column from schedule, play, studio, seat " + 
				"where schedule_id ='"+schedule_ID+"' and schedule.play_id = play.play_id and "
						+ "schedule.studio_id = studio.studio_id and seat_id  = '"+seat_id+"'";
			System.out.println(sql);
		try {
			DBUtil dbUtil = new DBUtil();
			ResultSet rs = dbUtil.execQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString("play_name"));
				zhiFu.setPlay_name(rs.getString("play_name"));
				System.out.println(rs.getString("studio_name"));
				zhiFu.setSchedule_time(new DateChange().SToU(rs.getTimestamp("schedule_time")));
				zhiFu.setStudio_name(rs.getString("studio_name"));
				zhiFu.setSeat_row(rs.getInt("seat_row"));
				zhiFu.setSeat_column(rs.getInt("seat_column"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return zhiFu;
	}
	
}
