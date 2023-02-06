package application;

import java.util.Date;

public class AccidentsRecords {
	private int accidentNo;
	private double cost;
	private String accidentDate;
	private String accidentLocation;
	private String carNumber;

	public AccidentsRecords(double cost, int accidentNo,  String accidentDate, String accidentLocation, String carNumber) {
		super();
		this.accidentNo = accidentNo;
		this.cost = cost;
		this.accidentDate = accidentDate;
		this.accidentLocation = accidentLocation;
		this.carNumber = carNumber;
	}

	public int getAccidentNo() {
		return accidentNo;
	}

	public void setAccidentNo(int accidentNo) {
		this.accidentNo = accidentNo;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(String accidentDate) {
		this.accidentDate = accidentDate;
	}

	public String getAccidentLocation() {
		return accidentLocation;
	}

	public void setAccidentLocation(String accidentLocation) {
		this.accidentLocation = accidentLocation;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

}