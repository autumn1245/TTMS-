package view.schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Play;
import entity.Schedule;
import entity.Seat;
import entity.Studio;
import entity.Ticket;
import service.DateChange;
import service.PlaySrv;
import service.ScheduleSrv;
import service.SeatSrv;
import service.StudioSrv;
import service.TicketSrv;

@WebServlet("/ScheduleUpdate")
public class ScheduleUpdate extends HttpServlet{
	private static final long serialVersionUID = -3166790721662987855L;
	
	public ScheduleUpdate() {
		// TODO 自动生成的构造函数存根
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Schedule sch = null;
		String type = req.getParameter("type");
		int schedule_ID = 0;
		try {
			if (type.equals("modify")) {
				schedule_ID = Integer.valueOf(req.getParameter("schedule_ID"));
			}
			String studio_name = req.getParameter("studio_name");
			studio_name = new String(studio_name.getBytes("ISO-8859-1"), "utf-8");
			int studio_id = NameToId.changeStudio2(studio_name);// 由于增加和修改给的studio_name对应的id只有一个
			String play_name = req.getParameter("play_name");
			play_name = new String(play_name.getBytes("ISO-8859-1"), "utf-8");
			int play_id = NameToId.changePlay2(play_name);
			String schedule_time0 = req.getParameter("schedule_time");
			schedule_time0 = new String(schedule_time0.getBytes("ISO-8859-1"), "utf-8");
			Date schedule_time = DateChange.StToU(schedule_time0);
			float schedule_price = Float.valueOf(req.getParameter("schedule_price"));
			// 计算结束时间
			List<Play> list = new PlaySrv().Fetch("play_id = '"+ play_id +"'"); // 找到play对应的时长
			int time = -1;
			for (Play play : list) {
				time = play.getLength();
			}
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(schedule_time);// 把date转成GregorianCalendar;
			calendar.add(GregorianCalendar.MINUTE, time); 
			Date schedule_endtime = calendar.getTime(); // 转换回来
			sch = new Schedule(schedule_ID, studio_id, play_id, schedule_time, schedule_price, schedule_endtime);
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			List<Schedule> list4 = new ScheduleSrv().Fetch("studio_id = '"+ studio_id +"'");
			int flag = 0;
			for (Schedule schedule : list4) {
				Date schedule_time2 = schedule.getSchedule_time();
				Date schedule_endtime2 = schedule.getSchedule_endtime();
				Date currentTime = new Date();
				if (schedule_time.before(currentTime) || (schedule_time.after(schedule_time2) && schedule_time.before(schedule_endtime2))) {
					out.write("时间冲突，请重新设置时间！");
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				if (type.equals("add")) {
					if (new ScheduleSrv().add(sch) == 1) {
//						out.write("数据添加成功");
						
//						System.out.println(sch.getSchedule_ID());
						// 修改play的状态(0：待安排演出 1：已安排演出 -1：下线)
						List<Play> list1 = null;
						list1 = new PlaySrv().Fetch("play_id = '"+ play_id +"'");
						Play play = list1.get(0);
						play.setStatus(1);
						int n = new PlaySrv().modify(play);
						System.out.println("play的状态修改成功！");
						
						String schedule_info = req.getParameter("schedule_info");
						schedule_info = new String(schedule_info.getBytes("ISO-8859-1"), "utf-8");
						System.out.println(schedule_info);
						if ("是".equals(schedule_info)) {
							// 开始生成票
							
							/*获取选定的这个演出厅的信息*/
							List<Studio> list2 = null;
							list2 = new StudioSrv().Fetch("studio_id = '"+ studio_id +"'");

							/*获取选定的演出厅的所有座位的信息*/
							List<Seat> list3 = null;
							list3 = new SeatSrv().Fetch("studio_id = '"+ studio_id +"'");
//							for (Seat seat : list3) {
//								System.out.println(seat.getId());
//							}
							for (Seat seat : list3) {
								Ticket ticket = new Ticket();
								ticket.setSeatId(seat.getId());
								ticket.setScheduleId(sch.getSchedule_ID());
								ticket.setPrice(schedule_price);
								System.out.println(ticket);
								if (seat.getStatus() == 0) {
									ticket.setStatus(0);// 票的状态 ：未售出：0 已售出：1 锁住：2 
								}else {
									ticket.setStatus(1);
								}
								int n2 = new TicketSrv().add(ticket);
								
								if(n2 == 1) {
									System.out.println("票生成成功");
								}else {
									System.out.println("票生成失败");
								}
							}
						}
						out.write("数据添加成功！");
					}else {
						out.write("数据添加失败");
					}
				} else {
					if (new ScheduleSrv().modify(sch)==1) {
						out.write("数据修改成功");
					}else {
						out.write("数据修改失败，请重试");
					}
				}
			}
			out.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write("操作错误，请重试");
		}
	}
}