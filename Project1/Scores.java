
import java.util.Scanner;

public class Scores {

	public static void main(String[] args) {

		int i, j;
		double avg, sum;
		String[] name = new String[10];
		int[][] scores = new int[10][5];
		Scanner sc = new Scanner(System.in);
		for (i = 0; i < 10; i++) {
			System.out.println("Enter the name of student :");
			name[i] = sc.next();
			System.out.println("Enter Scores of 5 Quizes :");
			for (j = 0; j < 5; j++) {
				scores[i][j] = sc.nextInt();
			}
			sc.close();
		}
		// Calculate average score of each student//
		for (i = 0; i < 10; i++) {
			sum = 0;
			for (j = 0; j < 5; j++) {

				sum = sum + scores[i][j];
			}
			avg = sum / 5;

			System.out.println("Name of Student: " + name[i]);
			System.out.println("The average score: " + avg);
		}

	}

}
