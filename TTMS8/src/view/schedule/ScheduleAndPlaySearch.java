package view.schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.DataDict;
import entity.Play;
import entity.Schedule;
import service.DataDictSrv;
import service.DateChange;
import service.PlaySrv;
import service.ScheduleSrv;
@WebServlet("/ScheduleAndPlaySearch")

public class ScheduleAndPlaySearch extends HttpServlet{
	public ScheduleAndPlaySearch() {
		// TODO 自动生成的构造函数存根
	}
	
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException ,java.io.IOException {};
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int schedule_ID=Integer.valueOf(req.getParameter("schedule_ID"));
		List<Play> list0 = null;
		int studio_id = -1;
		int play_id = -1;
		Date schedule_time = null;
		float schedule_price = -1;
		int play_type_id = -1;
		int play_lang_id = -1;
		String play_name = null;
		int play_length = -1;
		List<Schedule> list = new ScheduleSrv().Fetch("schedule_ID = '"+ schedule_ID +"'");
		for (Schedule schedule : list) {
			studio_id = schedule.getStudio_id();
			play_id = schedule.getPlay_id();
			schedule_time = schedule.getSchedule_time();
			schedule_price = schedule.getSchedule_price();
		}
		list0 = new PlaySrv().Fetch("play_id = '"+ play_id +"'");
		for (Play play : list0) {
			play_type_id = play.getTypeId();
			play_lang_id = play.getLangId();
			play_name = play.getName();
			play_length = play.getLength();
		}
		List<DataDict> data1 = new DataDictSrv().Fetch(play_lang_id);
		String lang = data1.get(0).getValue();
		List<DataDict> data = new DataDictSrv().Fetch(play_type_id);
		String type = data.get(0).getValue();
		// 写jsonStr
		String jsonStr = "[";
		jsonStr += "{\"play_name\":\""+play_name+ 
					"\",\"play_type_id\":\""+type+
					"\",\"play_lang_id\":\""+lang+
					"\",\"play_length\":\""+play_length+
					"\",\"studio_name\":\""+new IDtoName().changeStudio(studio_id)+
					"\",\"schedule_time\":\""+new DateChange().UToSt(schedule_time)+
					"\",\"schedule_price\":\""+schedule_price+"\"}";
		jsonStr += "]";
		resp.setContentType("application/json;charset=utf-8");  // 指定返回的格式为JSON格式
		resp.setCharacterEncoding("UTF-8");  // setContentType与setCharacterEncoding的顺序不能调换，否则无法解决中文乱码的问题
		PrintWriter out = resp.getWriter();
		out.write(jsonStr);
		out.close();

		
	} 
}
