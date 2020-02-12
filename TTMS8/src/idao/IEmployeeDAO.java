package idao;

import java.util.List;

import entity.Employee;


public interface IEmployeeDAO {
	public int insert(Employee employee);
	public int update(Employee employee);
	public int delete(int id);
	public List<Employee> select(String condt);
}
