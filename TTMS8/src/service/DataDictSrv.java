package service;

import java.util.List;

import entity.DataDict;
import idao.DAOFactory;
import idao.IDataDictDAO;

public class DataDictSrv {
	private IDataDictDAO datadictdao=DAOFactory.createDataDictDAO();
	
	public List<DataDict> Fetch(int dict_id){
		return datadictdao.select(dict_id);
	}
	
	public int Fetch1(String dict_value){
		return datadictdao.select(dict_value);
	}
}
