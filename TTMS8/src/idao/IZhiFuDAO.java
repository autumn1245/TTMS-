package idao;

import java.util.List;

import entity.ZhiFu;

public interface IZhiFuDAO {
	public ZhiFu select(int schedule_ID, int seat_id);
}
