package view.ticket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Schedule;
import entity.Seat;
import entity.Studio;
import entity.Ticket;
import service.ScheduleSrv;
import service.SeatSrv;
import service.StudioSrv;
import service.TicketSrv;
@WebServlet("/TicketAndSeatSearch")
public class TicketAndSeatSearch extends HttpServlet{

	private static final long serialVersionUID = 6428141453991577898L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("--------");
		int schedule_ID = -1;
		int seat_id = -1;
		int seat_row = -1;
		int seat_column = -1;
		int ticket_status = -1;
		int studio_id = -1;
		int rowcount = -1;
		int colcount = -1;
		
		schedule_ID = Integer.valueOf(req.getParameter("schedule_ID").trim());
		//System.out.println("schedule_ID:===" +schedule_ID);
		PrintWriter out = resp.getWriter();
		String jsonStr = "[";
		
		List<Ticket> list = new TicketSrv().Fetch("schedule_ID = '"+ schedule_ID +"'");
		for (Ticket ticket: list) {
			seat_id = ticket.getSeatId();
			System.out.println("seat_id===0--098"+ seat_id);
			// 通过seat_id得到seat_row,seat_column
			List<Seat> list1 = new SeatSrv().Fetch("seat_id = '"+ seat_id +"'");
			for (Seat seat : list1) {
				seat_row = seat.getRow();
				seat_column = seat.getColumn();
			}
			ticket_status = ticket.getStatus();
			jsonStr+="{\"seat_id\":\""+seat_id+
					"\",\"seat_row\":\""+seat_row+
					"\",\"seat_column\":\""+seat_column+
					"\",\"ticket_status\":\""+ticket_status+"\"}";
			jsonStr += ",";
		}
		
		List<Schedule> list2 = new ScheduleSrv().Fetch("schedule_ID = '"+ schedule_ID +"'");
		for (Schedule schedule : list2) {
			studio_id = schedule.getStudio_id();
		}
		List<Studio> list3 = new StudioSrv().Fetch("studio_id = '"+ studio_id +"'");
		for (Studio studio : list3) {
			rowcount = studio.getRowCount();
			colcount = studio.getColCount();
		}
		
		if(list.size() == 0) {
			jsonStr+="{\"rowcount\":\""+rowcount +
					"\",\"colcount\":\"" + colcount + "\"}";
			jsonStr+="]";
			System.out.println("====jsonStr:" + jsonStr);
		}else{
			jsonStr+="{\"rowcount\":\""+rowcount +
					"\",\"colcount\":\"" + colcount + "\"}";
			jsonStr=jsonStr.substring(0,jsonStr.length())+"]";
			System.out.println("jsonStr:==="+jsonStr);
			resp.setContentType("application/json;charset=utf-8");
			resp.setCharacterEncoding("utf-8");
			out.write(jsonStr);
			out.close();
		}	
	}
}

