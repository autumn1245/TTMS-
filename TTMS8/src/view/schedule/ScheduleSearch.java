package view.schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Play;
import entity.Schedule;
import service.DateChange;
import service.ScheduleSrv;

@WebServlet("/ScheduleSearch")
public class ScheduleSearch extends HttpServlet{
	private static final long serialVersionUID = -7148355331844148238L;

	public ScheduleSearch() {
		// TODO 自动生成的构造函数存根
	}
	
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException ,java.io.IOException {};
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("play_name");
		name = new String(name.getBytes("ISO-8859-1"), "utf-8");
		List<Play> list0 = null;
		List<Schedule> list = null;
		int play_id = -1;
		String jsonStr = "[";
		// 如果是给了剧目名称，现在去转换为剧目id再去查
		if (name != null && name.length()>0) {
			// 先得到play_id
			list0 = NameToId.changePlay(name);
			for (Play play : list0) {
				play_id = play.getId();
				// 再去调用dao查
				list = new ScheduleSrv().Fetch("play_id = '"+ play_id +"'");
				for (Schedule schedule : list) {
					jsonStr += "{\"schedule_ID\":\""+schedule.getSchedule_ID()+ 
							"\",\"studio_name\":\""+new IDtoName().changeStudio(schedule.getStudio_id()) +
							"\",\"play_name\":\""+new IDtoName().changePlay(schedule.getPlay_id()) +
							"\",\"schedule_time\":\""+new DateChange().UToSt(schedule.getSchedule_time()) +
							"\",\"schedule_price\":\""+schedule.getSchedule_price()+
							"\",\"schedule_endtime\":\""+new DateChange().UToSt(schedule.getSchedule_endtime())+ "\"}";
					jsonStr += ",";
				}
			}
		}else {
			list = new ScheduleSrv().FetchAll();
			for (Schedule schedule : list) {
				jsonStr += "{\"schedule_ID\":\""+schedule.getSchedule_ID()+ 
						"\",\"studio_name\":\""+new IDtoName().changeStudio(schedule.getStudio_id()) +
						"\",\"play_name\":\""+new IDtoName().changePlay(schedule.getPlay_id()) +
						"\",\"schedule_time\":\""+new DateChange().UToSt(schedule.getSchedule_time()) +
						"\",\"schedule_price\":\""+schedule.getSchedule_price()+
						"\",\"schedule_endtime\":\""+new DateChange().UToSt(schedule.getSchedule_endtime())+ "\"}";
				jsonStr += ",";
			}
		}	
		
		if(list.size()==0)
			jsonStr += "]";
		else
		    jsonStr = jsonStr.substring(0, jsonStr.length()-1)+"]";
		resp.setContentType("application/json;charset=utf-8");  // 指定返回的格式为JSON格式
		resp.setCharacterEncoding("UTF-8");  // setContentType与setCharacterEncoding的顺序不能调换，否则无法解决中文乱码的问题
		PrintWriter out = resp.getWriter();
		out.write(jsonStr);
		out.close();
	} 
}
