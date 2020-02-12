package service;

import java.util.List;

import entity.Play;
import idao.DAOFactory;
import idao.IPlayDAO;

public class PlaySrv {
	private IPlayDAO playDAO = DAOFactory.createPlayDAO();
	
	public int add(Play play){
		return playDAO.insert(play);
	}
	
	public int modify(Play play){
		return playDAO.update(play);
	}
	
	public int delete(int id){
		return playDAO.delete(id);
	}
	
	public List<Play> Fetch(String condt){
		return playDAO.select(condt);
	}
	
	public List<Play> FetchAll(){
		return playDAO.select("");
	}
}
