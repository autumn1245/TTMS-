package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entity.Employee;
import idao.IEmployeeDAO;
import util.DB2;
import util.DBUtil;

public class EmployeeDAO implements IEmployeeDAO{

	@Override
	public int insert(Employee emp) {
		int n = -1;
		
		String sql = "insert into employee(emp_name, emp_tel_num, emp_addr, emp_email, emp_power) "
				+ "values('"+emp.getName()+"', '"+emp.getTelNum()+"', '"+emp.getAddress()+"', "
						+ "'"+emp.getEmail()+"', '"+emp.getPower()+"')";
		
		System.out.println(sql);
		
		DBUtil db = new DBUtil();	
		n = db.execCommand(sql);
		if(n > 0) {
			System.out.println("添加成功");
		}
		
		return n;
	}

	@Override
	public int update(Employee emp) {
		DBUtil db = new DBUtil();
		
		String sql = "update employee set emp_name = '"+emp.getName()+"', "
				+ "emp_tel_num = '"+emp.getTelNum()+"', "
						+ "emp_addr = '"+emp.getAddress()+"', "
								+ "emp_email = '"+emp.getEmail()+"', "
										+ "emp_power = '"+emp.getPower()+"' ";
		sql += "where emp_id = '"+emp.getId()+"'";
		return db.execCommand(sql);
	}

	@Override
	public int delete(int id) {
		String sql=" delete from employee ";
		sql += " where emp_id = " + id;
		DBUtil db = new DBUtil();
		return db.execCommand(sql);
	}

	@Override
	public List<Employee> select(String condt) {
		List<Employee> employeeList=null;
		employeeList=new ArrayList<Employee>();
		try {
			String sql="select * from employee ";
			condt.trim();
			if(!condt.isEmpty()){
				sql+=" where "+ condt;
			}
			DBUtil db=new DBUtil();
			ResultSet rst=db.execQuery(sql);
			if(rst!=null){
				while(rst.next()){
					Employee emp=new Employee();
					emp.setId(rst.getInt("emp_id"));
					emp.setName(rst.getString("emp_name"));
					emp.setTelNum(rst.getString("emp_tel_num"));
					emp.setAddress(rst.getString("emp_addr"));
					emp.setEmail(rst.getString("emp_email"));
					emp.setPower(rst.getInt("emp_power"));
					employeeList.add(emp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeList;
	
	}

}
