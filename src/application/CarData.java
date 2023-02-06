package application;

public class CarData {

	private String carModel;
	private String carNumber;
	private String carTybe;
	private String personalID;
	public CarData(String carModel, String carNumber, String carTybe, String personalID) {
		super();
		this.carModel = carModel;
		this.carNumber = carNumber;
		this.carTybe = carTybe;
		this.personalID = personalID;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCarTybe() {
		return carTybe;
	}
	public void setCarTybe(String carTybe) {
		this.carTybe = carTybe;
	}
	public String getPersonalID() {
		return personalID;
	}
	public void setPersonalID(String personalID) {
		this.personalID = personalID;
	}
}