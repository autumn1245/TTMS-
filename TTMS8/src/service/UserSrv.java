package service;

import java.util.List;

import entity.User;
import idao.DAOFactory;
import idao.IUserDAO;

public class UserSrv {
	private IUserDAO userDAO = DAOFactory.creatUserDAO();
	
	public int add(User user) {
		return userDAO.insert(user);
	}
	
	public int delete(String name) {
		return userDAO.delete(name);
	}
	
	public int modify(User user) {
		return userDAO.update(user);
	}
	
	public List<User> Fetch(String name) {
		return userDAO.select(name);
	}
	
	public List<User> FetchAll() {
		return userDAO.select("");
	}
}
