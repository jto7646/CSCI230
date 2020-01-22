import java.util.Scanner;

public class Lab1b {

    public static void main(String[] args){
        
        // Initializations
        int split = 0;
        Scanner input = new Scanner(System.in);
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        System.out.print("Please input a string: ");

        String s = input.nextLine();

        // Finds the half way point of the string
        split = s.length() / 2;

        // Builds a string with the first half of the characters
        for(int i = 0; i <=(split -1); i++){
            string1.append(s.charAt(i));
        }

        // Builds a string with the second half of the characters
        for(int i = split; i <=(s.length()-1); i++){
            string2.append(s.charAt(i));
        }

        System.out.println("String one is: " + string1 +"\nString two is: " + string2);
    }
}