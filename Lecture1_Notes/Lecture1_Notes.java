import java.util.Scanner;



public class Lecture1_Notes {
 
	public static void main(String[] args) {
			
		Scanner input = new Scanner(System.in);
		
		/*private static String promptString(String prompt) {
			System.out.print(prompt);
			return input.nextLine();
		}
		*/
		StringBuilder sb = new StringBuilder();
		sb.append('p');
		
				
		String str;
		
		System.out.print("Enter Your Name:");
		str = input.nextLine();
		System.out.println("Hello," + str);
		input.close();

	}

}
