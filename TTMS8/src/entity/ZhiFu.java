package entity;

import java.util.Date;

public class ZhiFu {
	private String play_name = null;
	private Date schedule_time;
	private String studio_name = null;
	private int seat_row = -1;
	private int seat_column = -1;
	
	public ZhiFu() {
		// TODO Auto-generated constructor stub
	}

	public ZhiFu(String play_name, Date schedule_time, String studio_name, int seat_row, int seat_column) {
		super();
		this.play_name = play_name;
		this.schedule_time = schedule_time;
		this.studio_name = studio_name;
		this.seat_row = seat_row;
		this.seat_column = seat_column;
	}

	public String getPlay_name() {
		return play_name;
	}

	public void setPlay_name(String play_name) {
		this.play_name = play_name;
	}

	public Date getSchedule_time() {
		return schedule_time;
	}

	public void setSchedule_time(Date schedule_time) {
		this.schedule_time = schedule_time;
	}

	public String getStudio_name() {
		return studio_name;
	}

	public void setStudio_name(String studio_name) {
		this.studio_name = studio_name;
	}

	public int getSeat_row() {
		return seat_row;
	}

	public void setSeat_row(int seat_row) {
		this.seat_row = seat_row;
	}

	public int getSeat_column() {
		return seat_column;
	}

	public void setSeat_column(int seat_column) {
		this.seat_column = seat_column;
	}

	@Override
	public String toString() {
		return "ZhiFu [play_name=" + play_name + ", schedule_time=" + schedule_time + ", studio_name=" + studio_name
				+ ", seat_row=" + seat_row + ", seat_column=" + seat_column + "]";
	}
	
	
	
}
