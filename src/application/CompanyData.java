package application;

public class CompanyData {

	private String PhoneNum;
	private String cname;
	private String email;

	public CompanyData(String phoneNum, String cname, String email) {
		super();
		PhoneNum = phoneNum;
		this.cname = cname;
		this.email = email;
	}

	public String getPhoneNum() {
		return PhoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}