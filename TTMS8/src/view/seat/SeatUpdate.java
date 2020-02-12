package view.seat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Seat;
import entity.Studio;
import entity.Ticket;
import service.PlaySrv;
import service.SeatSrv;
import service.StudioSrv;
import service.TicketSrv;

@WebServlet("/SeatUpdate")
public class SeatUpdate extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	public SeatUpdate() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Seat seat = null;
////		String type = req.getParameter("type");
//		String studio_name = null;
//		int studio_id = -1;
//		int seat_row = -1;
//		int seat_column = -1;
//		int seat_status = -1;
		System.out.println("到这了");
		String studio_name = null;
		int studio_id = -1;
		int seat_row = -1;
		int seat_column = -1;
		int seat_status = -1;
		int num = -1;
		try{
			// 得到页面信息			
			System.out.println(req.getParameter("num"));
			num = Integer.valueOf(req.getParameter("num").trim());
			System.out.println("num =" + num);
			String seat = req.getParameter("seat");
			System.out.println("seat =" + seat);
			
			PrintWriter out = resp.getWriter();

			studio_name = req.getParameter("studio_name");
			studio_name = new String(studio_name.getBytes("ISO-8859-1"),"utf-8");
			
			studio_id = NameToId.changeStudio2(studio_name);
			
			//先全部修改成好的
			List<Seat> list5 = new SeatSrv().Fetch("studio_id = '"+ studio_id +"'");
			for (Seat seat2 : list5) {
				seat2.setStatus(0);
				new SeatSrv().modify(seat2);
			}
			
			// 把传过来的修改成坏的
			// 把seat解析出来
			seat = seat.substring(1, seat.length()-1);// 去掉两边的【】
			String[] seats = seat.split(",");
			int i;
			for( i = 0; i < 2 * num; i++) {
				System.out.println(seats[i]);
				seat_row = Integer.parseInt(seats[i].substring(7, seats[i].length()));
				System.out.println(seat_row);
				seat_column = Integer.parseInt(seats[++i].substring(9, seats[i].length()-1));
				System.out.println(seat_column);
				
				Seat seat2 = new Seat();
				seat_status = 1;
				seat2.setStudioId(studio_id);
				seat2.setRow(seat_row);
				seat2.setColumn(seat_column);
				seat2.setStatus(seat_status);
				int n = new SeatSrv().modify(seat2);
				
				
				if(n == 1){
					System.out.println("修改成功！");
					
					List<Seat> list2 = new SeatSrv().Fetch("studio_id = '"+ studio_id +"' and seat_row = '"+ seat_row +"' and seat_column = '"+ seat_column +"'");
					Seat seat3 = list2.get(0);
					System.out.println(seat3);
					List<Ticket> list3 = new TicketSrv().Fetch("seat_id = '"+ seat3.getId() +"'");
					for (Ticket ticket : list3) {
						
						ticket.setStatus(1);
						int n1 = new TicketSrv().modify(ticket);
						if(n1 > 0) {
							System.out.println("座位修改成功，票修改成功");
						}
					}
				}else{
					System.out.println("修改失败！");
					break;
				}
				out.write("修改成功！");
				out.close();
			}
		}catch(Exception e){
			e.printStackTrace();
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write("操作错误，请重试");
		}
	}
		
		
//		if (i == num) {
//			out.write("数据修改成功");
//		}else{
//			out.write("数据修改失败，请重试");
//		}
//	}	
}
