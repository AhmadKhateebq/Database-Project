package application;

public class Company2Service {

	private int sID;
	private String phoneNum; // from company

	public Company2Service(int sID, String phoneNum) {
		super();
		this.sID = sID;
		this.phoneNum = phoneNum;
	}

	public int getsID() {
		return sID;
	}

	public void setsID(int sID) {
		this.sID = sID;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

}
