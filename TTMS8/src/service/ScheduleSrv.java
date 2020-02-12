package service;

import java.util.List;

import entity.Schedule;
import idao.DAOFactory;
import idao.IScheduleDAO;
import idao.IStudioDAO;

public class ScheduleSrv {
	private IScheduleDAO schDAO = DAOFactory.createScheduleDAO();
	
	public int add(Schedule sch) {
		return schDAO.insert(sch);
	}
	
	public int modify(Schedule sch) {
		return schDAO.update(sch);
	}
	
	public int delete (int ID) {
		return schDAO.delete(ID);
	}
	
	public List<Schedule> Fetch(String condt) {
		return schDAO.select(condt);
	}
	
	public List<Schedule> FetchAll() {
		return schDAO.select("");
	}
}
