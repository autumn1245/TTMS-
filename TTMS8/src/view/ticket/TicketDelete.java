package view.ticket;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TicketSrv;

public class TicketDelete {
	private static final long serialVersionUID = 1L;
	
	public TicketDelete() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			int ticket_id=Integer.valueOf(req.getParameter("ticket_id"));
			resp.setContentType("text/html;charset=utf_8");
			PrintWriter out=resp.getWriter();
			out.write(""+new TicketSrv().delete(ticket_id));
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write("操作错误，请重试");
		}
	}
}
