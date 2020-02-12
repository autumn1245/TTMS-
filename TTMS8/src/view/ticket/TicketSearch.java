package view.ticket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Ticket;
import service.SeatSrv;
import service.TicketSrv;

public class TicketSearch extends HttpServlet{
	private static final long servialVersionUID=1L;
	
	public TicketSearch() {
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int ticket_id=Integer.valueOf(req.getParameter("ticket_id"));
		List<Ticket> result=null;
		if(ticket_id!=0){
			result=new TicketSrv().Fetch("ticket_id = '"+ticket_id+"'");
		}else{
			result=new TicketSrv().FetchAll();
		}
		String jsonStr="[";
		for(Ticket t:result){
			jsonStr+="{\"ticket_id\":\""+t.getId() + 
					"\",\"seat_id\":\""+t.getSeatId() +
					"\",\"schedule_id\":\""+t.getScheduleId()+
					"\",\"ticket_price\":\""+t.getPrice()+
					"\",\"tickrt_status\":\""+t.getStatus()+ "\"}";
			jsonStr += ",";
		}
		if(result.size()==0){
			jsonStr+="]";
		}else{
			jsonStr=jsonStr.substring(0,jsonStr.length()-1)+"]";
			resp.setContentType("application/json;charset=utf-8");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out=resp.getWriter();
			out.write(jsonStr);
			out.close();
		}
	}
}
