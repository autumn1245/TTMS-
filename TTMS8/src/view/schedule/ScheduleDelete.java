package view.schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Ticket;
import service.ScheduleSrv;
import service.TicketSrv;

@WebServlet("/ScheduleDelete")
public class ScheduleDelete extends HttpServlet{
	private static final long serialVersionUID = 713022939165726501L;
	
	public ScheduleDelete() {
		// TODO 自动生成的构造函数存根
	}
	
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int schedule_ID = Integer.valueOf(req.getParameter("schedule_ID"));
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.write(""+new ScheduleSrv().delete(schedule_ID));
			System.out.println("你是否要删除这个计划的票，此行为很有可能造成重大影响");
			List<Ticket> list = new TicketSrv().Fetch("schedule_ID = '"+ schedule_ID +"'");
			for (Ticket ticket : list) {
				int ticket_id = ticket.getId();
				new TicketSrv().delete(ticket_id);
			}
			
			out.close();
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write("操作错误，请重试");
		}
		
	}
}
