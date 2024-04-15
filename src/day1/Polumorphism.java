package day1;

interface vehical{
	public void startEngine();
}

public class Polumorphism implements vehical{

	@Override
	public void startEngine() {
		System.out.println("Engine is Started");
	}

	public static void main(String[] args) {
		vehical car = new Polumorphism();
		car.startEngine();
	}
}
