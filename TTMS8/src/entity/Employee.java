package entity;

import java.io.Serializable;

public class Employee implements Serializable {
	
	
	/*
	 * create table employee
    (
        emp_id               int not null auto_increment,
        emp_no               char(20) not null,
        emp_name             varchar(100) not null,
        emp_tel_num          char(20),
        emp_addr             varchar(200),
        emp_email            varchar(100),
         primary key (emp_id)
      );
	 * 
	 * 
	 */
	
	private int id;
	private String name;
	private String telNum;
	private String address;
	private String email;
	private int power;
	
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public Employee(int id, String name, String telNum,
			String address, String email, int power) {
		super();
		this.id = id;
		this.name = name;
		this.telNum = telNum;
		this.address = address;
		this.email = email;
		this.power = power;
	}
	


	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getTelNum() {
		return telNum;
	}




	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}



	public void showValue() {
		System.out.println("编号：" + id + "\t姓名：" + name);
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", telNum=" + telNum + ", address=" + address + ", email="
				+ email + ", power=" + power + "]";
	}
	
	

}
