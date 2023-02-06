package application;

public class EmployeeData {

	private int employeeNO;
	private double salary;
	private String ename;
	private String phoneNO;
	private String personalID;

	public EmployeeData(int employeeNO, double salary, String ename, String phoneNO, String personalID) {
		super();
		this.employeeNO = employeeNO;
		this.salary = salary;
		this.ename = ename;
		this.phoneNO = phoneNO;
		this.personalID = personalID;
	}

	public int getEmployeeNO() {
		return employeeNO;
	}

	public void setEmployeeNO(int employeeNO) {
		this.employeeNO = employeeNO;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getPhoneNO() {
		return phoneNO;
	}

	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}

	public String getPersonalID() {
		return personalID;
	}

	public void setPersonalID(String personalID) {
		this.personalID = personalID;
	}

}