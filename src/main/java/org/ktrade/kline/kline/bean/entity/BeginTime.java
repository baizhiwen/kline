package org.ktrade.kline.kline.bean.entity;

public class BeginTime {
	
	private Integer id;
	private String startTime;
	private String endTime;
	private Integer del;
	
	public Integer getDel() {
		return del;
	}
	public void setDel(Integer del) {
		this.del = del;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "BeginTime{" +
				"id=" + id +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", del=" + del +
				'}';
	}
}
