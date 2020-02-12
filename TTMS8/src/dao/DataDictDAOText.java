package dao;

import java.util.List;

import entity.DataDict;

public class DataDictDAOText {

	public static void main(String[] args) {
		DataDictDAO dao = new DataDictDAO();
		
		List<DataDict> list = dao.select(5);
		DataDict dataDict = list.get(0);
		System.out.println(dataDict.getValue());
	}
}
