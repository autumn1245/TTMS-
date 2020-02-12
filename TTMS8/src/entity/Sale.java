package entity;

import  java.sql.Date;

public class Sale {
	/*==============================================================
	create table sale
	(
	   sale_ID              bigint not null auto_increment,
	   emp_id               int,
	   sale_time            datetime,
	   sale_payment         decimal(10,2),
	   sale_change          numeric(10,2),
	   sale_type            smallint comment '���ȡֵ���壺
	            1�����۵�
	            -1���˿',
	   sale_status          smallint comment '���۵�״̬���£�
	            0��������
	            1���Ѹ���',
	   primary key (sale_ID)
	); 
	==============================================================*/
	
	private int id;
	private int empId;
	private Date time ;  // ?? �鿴datetime��Ӧ��java����
	private float payment;
	private float change;  //  ������Ե���ҵ������ʲô�أ�
	private int type;
	private int status;
	public Sale() {
		// TODO Auto-generated constructor stub
	}
	
	public Sale(int id, int empId, Date time2, float payment, float change, int type, int status) {
		super();
		this.id = id;
		this.empId = empId;
		this.time = time2;
		this.payment = payment;
		this.change = change;
		this.type = type;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public float getPayment() {
		return payment;
	}
	public void setPayment(float payment) {
		this.payment = payment;
	}
	public float getChange() {
		return change;
	}
	public void setChange(float change) {
		this.change = change;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
