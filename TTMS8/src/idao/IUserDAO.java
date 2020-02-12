package idao;

import java.util.List;

import entity.User;

public interface IUserDAO {
	public int insert(User user);
	public int delete(String name);
	public int update(User user);
	public List<User> select(String name);
}
