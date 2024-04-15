package day1;

public class CopyConstructor {
	private String studName;
	private int studId;

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public int getStudId() {
		return studId;
	}

	public void setStudId(int studId) {
		this.studId = studId;
	}

	public CopyConstructor(CopyConstructor cc) {
		this.studName = cc.getStudName();
		this.studId = cc.getStudId();
	}

	public CopyConstructor() {

	}

	public static void main(String[] args) {
		CopyConstructor cc = new CopyConstructor();
		cc.setStudId(1001);
		cc.setStudName("Jihnoo");
		
		CopyConstructor cc2 = new CopyConstructor(cc);
	}

}
