package dao;

import entity.Employee;

public class EmployeeDAOText {

	public static void main(String[] args) {
		//增
		//power  为   0：最高   1：财务   2：售票员
		/*EmployeeDAO dao = new EmployeeDAO();
		
		Employee emp = new Employee();
		emp.setName("123");
		emp.setTelNum("123");
		emp.setAddress("123");
		emp.setEmail("123");
		emp.setPower(2);
		
		int n = dao.insert(emp);
		System.out.println(n);*/
		
		//改
		/*EmployeeDAO dao = new EmployeeDAO();
		
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("123");
		emp.setTelNum("13");
		emp.setAddress("123");
		emp.setEmail("3");
		emp.setPower(2);
		
		int n = dao.update(emp);
		System.out.println(n);*/
		
		//查
		/*EmployeeDAO dao = new EmployeeDAO();
		String sql = "emp_name like '%2%'";
		
		System.out.println(dao.select(sql).get(0).getPower());*/
		
		//删
		EmployeeDAO dao = new EmployeeDAO();
		System.out.println(dao.delete(1));
	}

}
