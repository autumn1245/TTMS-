package view.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Studio;
import entity.User;
import service.StudioSrv;
import service.UserSrv;

public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserUpdate() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		String type = request.getParameter("type");
		String name = null;
		try {
			if (type.equals("modify")) {
				 name = new String(request.getParameter("username"));
			}
			name=new String(name.getBytes("ISO-8859-1"),"utf-8");
			String password = new String(request.getParameter("password"));
			user = new User(name, password);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if (type.equals("add")) {
				if(new UserSrv().add(user)==1)
					out.write("数据添加成功");
				else
					out.write("数据添加失败，请重试");
			} else{
				if(new UserSrv().modify(user)==1)
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
