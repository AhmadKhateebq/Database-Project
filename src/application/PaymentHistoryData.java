package application;

import java.util.Date;

public class PaymentHistoryData {
	private String serviceStartDate;
	private String ServiceEndDate;
	private String payament_status;
	private double money_amount;
	private String personal_ID;
	private String carnumber;
	private String serviceID;

	public PaymentHistoryData(double money_amount, String serviceStartDate, String serviceEndDate, String payament_status,
			 String personal_ID, String carnumber, String serviceID) {
		super();
		this.serviceStartDate = serviceStartDate;
		ServiceEndDate = serviceEndDate;
		this.payament_status = payament_status;
		this.money_amount = money_amount;
		this.personal_ID = personal_ID;
		this.carnumber = carnumber;
		this.serviceID = serviceID;
	}

	public PaymentHistoryData(String serviceStartDate, String serviceEndDate, String payament_status,
			double money_amount) {
		super();
		this.serviceStartDate = serviceStartDate;
		ServiceEndDate = serviceEndDate;
		this.payament_status = payament_status;
		this.money_amount = money_amount;
	}

	public String getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public String getServiceEndDate() {
		return ServiceEndDate;
	}

	public void setServiceEndDate(String serviceEndDate) {
		ServiceEndDate = serviceEndDate;
	}

	public String getPayament_status() {
		return payament_status;
	}

	public void setPayament_status(String payament_status) {
		this.payament_status = payament_status;
	}

	public double getMoney_amount() {
		return money_amount;
	}

	public void setMoney_amount(double money_amount) {
		this.money_amount = money_amount;
	}

	public String getPersonal_ID() {
		return personal_ID;
	}

	public void setPersonal_ID(String personal_ID) {
		this.personal_ID = personal_ID;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

}