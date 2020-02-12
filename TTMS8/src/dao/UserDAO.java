package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import idao.IUserDAO;
import util.DBUtil;

public class UserDAO implements IUserDAO{

	@Override
	public int insert(User user) {
		int n = -1;
	
		String name = user.getUserName();
		String password = user.getUserPassword();
		String sql = "insert into cs(User_name, User_password)"
				+ " values('" + name + "', '" + password + "')";
		System.out.println(sql);
		DBUtil db = new DBUtil();
//			ResultSet rs = db.getInsertObjectIDs(sql);
//			if(rs != null && rs.first()) {
//				user.setUserId(rs.getInt(1));
//				return 1;
//			}
		n = db.execCommand(sql);
		if(n > 0) {
			return 1;
		}
	
	
		return n;
	}

	@Override
	public int delete(String name) {
		String sql = "delete from cs where User_name = '" + name + "'";
		DBUtil db = new DBUtil();
		return db.execCommand(sql);
	}

	@Override
	public int update(User user) {
		String sql = "update cs set User_password = '"+ user.getUserPassword() +"' where User_name = '"+ user.getUserName() +"'"; 
		
		DBUtil dbUtil = new DBUtil();
		return dbUtil.execCommand(sql);
	}

	@Override
	public List<User> select(String name) {
		List<User> list = new ArrayList<User>();
		
		String sql = "select * from cs";
		name.trim();
		if(!name.isEmpty()) {
			sql += " where User_name = '" + name + "'";
		}
		DBUtil dbUtil = new DBUtil();
		
		ResultSet rs = dbUtil.execQuery(sql);
		try {
			while (rs.next()) {
				String tempName = rs.getString("User_name");
				String tempPassword = rs.getString("User_password");
				
				User tempUser = new User(tempName, tempPassword);
				list.add(tempUser);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}	
		return list;
	}


}
