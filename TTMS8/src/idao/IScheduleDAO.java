package idao;

import java.util.List;

import entity.Schedule;

public interface IScheduleDAO {
	public int insert(Schedule sch);
	public int update(Schedule sch);
	public int delete(int ID);
	public List<Schedule> select(String condt);
}
