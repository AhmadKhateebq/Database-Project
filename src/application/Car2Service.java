package application;

import java.util.Date;

public class Car2Service {
	private String carNumber;
	private int sID;
	private Date serviceStartDate;
	private Date ServiceEndDate;
	private boolean payament_status;
	public Car2Service(String carNumber, int sID, Date serviceStartDate, Date serviceEndDate, boolean payament_status) {
		super();
		this.carNumber = carNumber;
		this.sID = sID;
		this.serviceStartDate = serviceStartDate;
		ServiceEndDate = serviceEndDate;
		this.payament_status = payament_status;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public int getsID() {
		return sID;
	}
	public void setsID(int sID) {
		this.sID = sID;
	}
	public Date getServiceStartDate() {
		return serviceStartDate;
	}
	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}
	public Date getServiceEndDate() {
		return ServiceEndDate;
	}
	public void setServiceEndDate(Date serviceEndDate) {
		ServiceEndDate = serviceEndDate;
	}
	public boolean isPayament_status() {
		return payament_status;
	}
	public void setPayament_status(boolean payament_status) {
		this.payament_status = payament_status;
	}

	
}
