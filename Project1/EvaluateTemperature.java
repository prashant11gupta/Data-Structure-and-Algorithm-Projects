
import java.util.Scanner;

public class EvaluateTemperature {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Temperature");
		double temps = sc.nextDouble();
		System.out.println("Enter temperature scale (C or F)");
		String tempScale = sc.next();

		sc.close();
		if (tempScale.equals("C") || tempScale.equals("c")) {
			temps = (temps * 9 / 5.0) + 32;

		}

		if (temps < 0)
			System.out.println("Extremely cold");
		if (temps >= 0 && temps < 33)
			System.out.println("Very Cold");
		if (temps >= 33 && temps < 51)
			System.out.println("Cold");
		if (temps >= 51 && temps < 71)
			System.out.println("Mild");
		if (temps >= 71 && temps < 91)
			System.out.println("Warm");
		if (temps >= 91 && temps < 100)
			System.out.println("Hot");
		if (temps >= 100)
			System.out.println("Very Hot");

	}

}
