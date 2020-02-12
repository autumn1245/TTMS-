package service;

import java.util.List;

import entity.Employee;
import idao.DAOFactory;
import idao.IEmployeeDAO;

public class EmployeeSrv {
	private IEmployeeDAO employeedao=DAOFactory.createEmployeeDAO();
	public int add(Employee employee){
		return employeedao.insert(employee);
	}
	public int modify(Employee employee){
		return employeedao.update(employee);
	}
	public int delete(int id){
		return employeedao.delete(id);
	}
	public List<Employee> Fetch(String condt){
		return employeedao.select(condt);
	}
	public List<Employee> FetchAll(){
		return employeedao.select("");
	}
}
