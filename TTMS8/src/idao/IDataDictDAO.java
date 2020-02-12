package idao;

import java.util.List;

import entity.DataDict;


public interface IDataDictDAO {
	public List<DataDict> select(int dict_id);
	public int select(String dict_value);
}
