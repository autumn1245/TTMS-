package view.studio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Seat;
import entity.Studio;
import service.SeatSrv;
import service.StudioSrv;
import view.seat.SeatSearch;

@WebServlet("/StudioUpdate")
public class StudioUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudioUpdate() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Studio stu = null;
		String type = request.getParameter("type");
		int id = 0;
		try {
			if (type.equals("modify")) {
				id = Integer.valueOf(request.getParameter("studioid"));
			}
			String name = request.getParameter("studioname");
			name=new String(name.getBytes("ISO-8859-1"),"utf-8");
			int rowCount = Integer.valueOf(request.getParameter("rowcount"));
			int colCount = Integer.valueOf(request.getParameter("colcount"));
			String intro = request.getParameter("intro");
			intro=new String(intro.getBytes("ISO-8859-1"),"utf-8");
			stu = new Studio(id, name, rowCount, colCount, intro);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if (type.equals("add")) {
				if(new StudioSrv().add(stu)==1) {
					out.write("数据添加成功");
					List<Studio> list = null;
					list = new StudioSrv().Fetch("studio_name = '"+name+"'");
					int studioid = list.get(0).getID();
					for(int i = 1; i <= rowCount; i++) {
						for(int j = 1; j <= colCount; j++) {
							Seat seat = new Seat();
							seat.setStudioId(studioid);
							seat.setRow(i);
							seat.setColumn(j);
							seat.setStatus(0);
							
							int n1 = new SeatSrv().add(seat);
							if(n1 > 0) {
								System.out.println("座位生成成功");
							} else {
								System.out.println("座位生成失败");
							}
						}
					}
				}
				else
					out.write("数据添加失败，请重试");
			} else{
				if(new StudioSrv().modify(stu)==1)
					out.write("数据修改成功");
				else
					out.write("数据修改失败，请重试");
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("操作错误，请重试");
		}
	}
}
