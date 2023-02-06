package application;

public class ServiceData {

	private int sID;
	private String sName;
	int sCost;
	

	public ServiceData(int sID, String sName, int sCost) {
		super();
		this.sID = sID;
		this.sName = sName;
		this.sCost = sCost;
	}

	public int getsID() {
		return sID;
	}

	public void setsID(int sID) {
		this.sID = sID;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public int getsCost() {
		return sCost;
	}

	public void setsCost(int sCost) {
		this.sCost = sCost;
	}

}
