package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Schedule;
import idao.IScheduleDAO;
import service.DateChange;
import util.DBUtil;

public class ScheduleDAO implements IScheduleDAO{

	@Override
	public int insert(Schedule sch) {
		int studio_id = sch.getStudio_id();
		int play_id = sch.getPlay_id();
		Date schedule_time0 = sch.getSchedule_time();
		Timestamp schedule_time = DateChange.UToS(schedule_time0);//把util的date 转换成sql的date 
		float schedule_price = sch.getSchedule_price();
		Date schedule_endtime0 = sch.getSchedule_endtime();
		Timestamp schedule_endtime = DateChange.UToS(schedule_endtime0); //把util的date 转换成sql的date 
		
		String sql = "insert into schedule(studio_id,play_id,schedule_time,schedule_price,schedule_endtime) values ('"+ studio_id +"','"+ play_id +"','"+ schedule_time +"','"+ schedule_price +"','"+ schedule_endtime +"')";
		
		DBUtil dbUtil = new DBUtil();
		int n = dbUtil.execCommand(sql);
		try {
			if(n > 0) {
				String sql2 = "select * from schedule where studio_id = '"+ studio_id +"'";
				ResultSet rs = dbUtil.execQuery(sql2);
				int schedule_id = -1;
				while(rs.next()) {
					schedule_id = rs.getInt("schedule_ID");
				}
				sch.setSchedule_ID(schedule_id);
				return 1;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Schedule sch) {
		int schedule_ID = sch.getSchedule_ID();
		int studio_id = sch.getStudio_id();
		int play_id = sch.getPlay_id();
		Date schedule_time0 = sch.getSchedule_time();
		Timestamp schedule_time = DateChange.UToS(schedule_time0); //把util的date 转换成sql的date 
		float schedule_price = sch.getSchedule_price();
		Date schedule_endtime0 = sch.getSchedule_endtime();
		Timestamp schedule_endtime = DateChange.UToS(schedule_endtime0); //把util的date 转换成sql的date 
		
		String sql = "update schedule set studio_id = '"+ studio_id +"',"
				+ "play_id = '"+ play_id +"',"
				+ "schedule_time = '"+ schedule_time +"',"
				+ "schedule_price = '"+ schedule_price +"',"
				+ "schedule_endtime = '"+ schedule_endtime +"' where schedule_ID = '"+ schedule_ID +"'";
		
		DBUtil dbUtil = new DBUtil();
		
		return dbUtil.execCommand(sql);
	}

	@Override
	public int delete(int ID) {
		String sql = "delete from schedule where schedule_ID = '"+ ID +"'";
		DBUtil dbUtil = new DBUtil();
		return dbUtil.execCommand(sql);
	}

	@Override
	public List<Schedule> select(String condt) {
		List<Schedule> list = new ArrayList<Schedule>();
		condt.trim();
		String sql = "select * from schedule";
		if (!condt.isEmpty()) {
			sql+=" where " + condt;
		}
		DBUtil dbUtil = new DBUtil();
		ResultSet rs = dbUtil.execQuery(sql);
		try {
			while (rs.next()) {
				Schedule schedule = new Schedule();
				int schedule_ID = rs.getInt("schedule_ID");
				int studio_id = rs.getInt("studio_id");
				int play_id = rs.getInt("play_id");
				Timestamp schedule_time0 = rs.getTimestamp("schedule_time");
				Date schedule_time = DateChange.SToU(schedule_time0);// 把sql的date转换成util的date
				float schedule_price = rs.getFloat("schedule_price");
				Timestamp schedule_endtime0 = rs.getTimestamp("schedule_endtime");
				Date schedule_endtime = DateChange.SToU(schedule_endtime0);// 把sql的date转换成util的date
				
				schedule.setSchedule_ID(schedule_ID);
				schedule.setStudio_id(studio_id);
				schedule.setPlay_id(play_id);
				schedule.setSchedule_time(schedule_time);
				schedule.setSchedule_price(schedule_price);
				schedule.setSchedule_endtime(schedule_endtime);
				
				list.add(schedule);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}
