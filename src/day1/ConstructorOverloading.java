package day1;

public class ConstructorOverloading {
	private String empName;
	private int empId;
	private double empSal;

	public ConstructorOverloading(String empName, int empId, double empSal) {
		super();
		this.empName = empName;
		this.empId = empId;
		this.empSal = empSal;
	}

	public ConstructorOverloading(String empName, int empId) {
		this(empName, empId, 0.0);
	}

	public ConstructorOverloading(String empName) {
		this(empName, 0);
	}

	@Override
	public String toString() {
		return "ConstructorOverloading [empName=" + empName + ", empId=" + empId + ", empSal=" + empSal + "]";
	}

	public static void main(String[] args) {
		ConstructorOverloading co1 = new ConstructorOverloading("Inno");
		ConstructorOverloading co2 = new ConstructorOverloading("Shi", 1001);
		ConstructorOverloading co3 = new ConstructorOverloading("Cho", 1002, 1000.0);

		System.out.println(co1);
		System.out.println(co2);
		System.out.println(co3);

	}
}
