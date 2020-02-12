package view.ticket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Ticket;
import service.TicketSrv;

public class TicketBack extends HttpServlet{
private static final long servialVersionUID=1L;
	
	public TicketBack() {
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int ticket_id=Integer.valueOf(req.getParameter("ticket_id"));
		List<Ticket> result=null;
		int n = 0;
		if(ticket_id!=0){
			result=new TicketSrv().Fetch("ticket_id = '"+ticket_id+"'");
		}
		for (Ticket ticket : result) {
			ticket.setStatus(0);
			n = new TicketSrv().modify(ticket);
			System.out.println(ticket);
		}
		PrintWriter out=resp.getWriter();

		if(n > 0) {
			out.write("1");
		} else {
			out.write("0");
		}
		

		out.close();
	}
}
