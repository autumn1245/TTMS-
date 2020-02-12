package entity;

import java.util.Date;

public class Schedule {
	private int schedule_ID = -1;
	private int studio_id = -1;
	private int play_id = -1;
	private Date schedule_time = null;
	private float schedule_price = -1;
	private Date schedule_endtime = null;
	
	public Schedule() {
		// TODO 自动生成的构造函数存根
	}

	public Schedule(int schedule_ID, int studio_id, int play_id, Date schedule_time, float schedule_price,
			Date schedule_endtime) {
		super();
		this.schedule_ID = schedule_ID;
		this.studio_id = studio_id;
		this.play_id = play_id;
		this.schedule_time = schedule_time;
		this.schedule_price = schedule_price;
		this.schedule_endtime = schedule_endtime;
	}

	public int getSchedule_ID() {
		return schedule_ID;
	}

	public void setSchedule_ID(int schedule_ID) {
		this.schedule_ID = schedule_ID;
	}

	public int getStudio_id() {
		return studio_id;
	}

	public void setStudio_id(int studio_id) {
		this.studio_id = studio_id;
	}

	public int getPlay_id() {
		return play_id;
	}

	public void setPlay_id(int play_id) {
		this.play_id = play_id;
	}

	public Date getSchedule_time() {
		return schedule_time;
	}

	public void setSchedule_time(Date schedule_time) {
		this.schedule_time = schedule_time;
	}

	public float getSchedule_price() {
		return schedule_price;
	}

	public void setSchedule_price(float schedule_price) {
		this.schedule_price = schedule_price;
	}

	public Date getSchedule_endtime() {
		return schedule_endtime;
	}

	public void setSchedule_endtime(Date schedule_endtime) {
		this.schedule_endtime = schedule_endtime;
	}

	@Override
	public String toString() {
		return "Schedule [schedule_ID=" + schedule_ID + ", studio_id=" + studio_id + ", play_id=" + play_id
				+ ", schedule_time=" + schedule_time + ", schedule_price=" + schedule_price + ", schedule_endtime="
				+ schedule_endtime + "]";
	}
}
