package view.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.UserSrv;

public class UserAdd extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public UserAdd() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		System.out.println(name);
		String pass = request.getParameter("password");
		System.out.println(pass);
		PrintWriter out = response.getWriter();
		List<User> list = null;
		if(name != null && pass != null) {
			list = new UserSrv().Fetch(name);
		}
		if(list.size() == 0) {
			User user = new User(name, pass);
			int n = new UserSrv().add(user);
			if(n > 0) {
				out.write("1");
			} else {
				out.write("0");
			}
		} else {
			out.write("0");
		}
		out.close();
	}
}
