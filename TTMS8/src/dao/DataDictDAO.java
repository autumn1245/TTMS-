package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.DataDict;
import idao.IDataDictDAO;
import util.DBUtil;

public class DataDictDAO implements IDataDictDAO{

	
	@Override
	public List<DataDict> select(int dict_id) {
		List<DataDict> list = new ArrayList<DataDict>();
		
		String sql = "select * from data_dict where dict_id = '" + dict_id + "'";
		
		DBUtil dbUtil = new DBUtil();
		
		ResultSet rs = dbUtil.execQuery(sql);
		
		try {
			while(rs.next()) {
				DataDict dataDict = new DataDict();
				dataDict.setId(rs.getInt("dict_id"));
				dataDict.setSuperId(rs.getInt("dict_parent_id"));
				dataDict.setIndex(rs.getInt("dict_index"));
				dataDict.setName(rs.getString("dict_name"));
				dataDict.setValue(rs.getString("dict_value"));
				
				list.add(dataDict);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int select(String dict_value) {
		int n = -1;
		
		String sql = "select * from data_dict where dict_value = '" + dict_value + "'";
		
		DBUtil dbUtil = new DBUtil();
		
		ResultSet rs = dbUtil.execQuery(sql);
		
		try {
			while(rs.next()) {
				n = rs.getInt("dict_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return n;
	}

}
