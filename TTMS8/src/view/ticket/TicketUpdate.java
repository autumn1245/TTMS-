package view.ticket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Generated;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import entity.Seat;
import entity.Studio;
import entity.Ticket;
import entity.ZhiFu;
import service.DateChange;
import service.SeatSrv;
import service.TicketSrv;
import service.ZhiFuSrv;
import util.DBUtil;
import util.DButil2;
@WebServlet("/TicketUpdate")
public class TicketUpdate extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	public TicketUpdate() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("sssdkherijeuhfj");
		
		Ticket ticket=null;
		int seat1_id = -1;
		int seat2_id = -1;
		int seat3_id = -1;
		int seat4_id = -1;
		int seat5_id = -1;
		int seat6_id = -1;
		int schedule_ID = -1;
		
		try{
			System.out.println("llllll" + req.getParameter("seat1_id"));
			seat1_id = Integer.valueOf(req.getParameter("seat1_id"));
			seat2_id = Integer.valueOf(req.getParameter("seat2_id"));
			seat3_id = Integer.valueOf(req.getParameter("seat3_id"));
			seat4_id = Integer.valueOf(req.getParameter("seat4_id"));
			seat5_id = Integer.valueOf(req.getParameter("seat5_id"));
			seat6_id = Integer.valueOf(req.getParameter("seat6_id"));
			schedule_ID = Integer.valueOf(req.getParameter("schedule_ID"));
			
			resp.setContentType("text/html;charset=utf-8");
			
			List<Ticket> list = new TicketSrv().Fetch("schedule_ID = '"+ schedule_ID +"' and seat_id in ('"+ seat1_id +"','"+ seat2_id +"','"+ seat3_id +"','"+ seat4_id +"','"+ seat5_id +"','"+ seat6_id +"')");
			
			DButil2 dButil2 = new DButil2();
			java.sql.Connection conn = dButil2.createConn();
			DButil2.GetProduseInsert(conn,list);
			
			
			List<ZhiFu> list2 = new ArrayList<ZhiFu>();
			
			if(seat1_id != 0) {
				list2.add(new ZhiFuSrv().Fetch(schedule_ID, seat1_id));
			}
			if(seat2_id != 0) {
				list2.add(new ZhiFuSrv().Fetch(schedule_ID, seat2_id));
			}
			if(seat3_id != 0) {
				list2.add(new ZhiFuSrv().Fetch(schedule_ID, seat3_id));
			}
			if(seat4_id != 0) {
				list2.add(new ZhiFuSrv().Fetch(schedule_ID, seat4_id));
			}
			if(seat5_id != 0) {
				list2.add(new ZhiFuSrv().Fetch(schedule_ID, seat5_id));
			}
			if(seat6_id != 0) {
				list2.add(new ZhiFuSrv().Fetch(schedule_ID, seat6_id));
			}
			
			String jsonStr = "[";		
			for (ZhiFu s : list2) {
				jsonStr += "{\"play_name\":\""+s.getPlay_name() + 
						"\",\"schedule_time\":\""+new DateChange().UToSt(s.getSchedule_time()) +
						"\",\"studio_name\":\""+s.getStudio_name()+
						"\",\"seat_row\":\""+s.getSeat_row()+
						"\",\"seat_column\":\""+s.getSeat_column()+ "\"}";
				jsonStr += ",";
			}
			if(list2.size()==0)
				jsonStr += "]";
			else
			    jsonStr = jsonStr.substring(0, jsonStr.length()-1)+"]";
			resp.setContentType("application/json;charset=utf-8");  // 指定返回的格式为JSON格式
			resp.setCharacterEncoding("UTF-8");  // setContentType与setCharacterEncoding的顺序不能调换，否则无法解决中文乱码的问题
			PrintWriter out = resp.getWriter();
			out.write(jsonStr);
			out.close();
			
			Date date = new Date();
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);// 把date转成GregorianCalendar;
			calendar.add(GregorianCalendar.MINUTE, 15); 
			date = calendar.getTime(); // 转换回来

			while(true) {
				Date date2 = new Date();
				if(date.before(date2)) {
					DButil2.GetProduseInsert(conn,list);
					break;
				}
			}			
		}catch(Exception e){
			e.printStackTrace();
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write("操作错误，请重试");
		}
	}
}
