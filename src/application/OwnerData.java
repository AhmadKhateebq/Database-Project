package application;

public class OwnerData {
	private int ownerID;
	private String phoneNO;
	private String ssn; //personalID
	private String ownerName;
	public OwnerData(int ownerID, String phoneNO, String ssn, String ownerName) {
		super();
		this.ownerID = ownerID;
		this.phoneNO = phoneNO;
		this.ssn = ssn;
		this.ownerName = ownerName;
	}
	public int getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	public String getPhoneNO() {
		return phoneNO;
	}
	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}

	