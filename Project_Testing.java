import java.util.Scanner;

public class Project_Testing{

    public static void main(String[] args){

        // Scanner for input
        Scanner input = new Scanner(System.in);
        Boolean par = true;

        // Prompt for user input
        System.out.println("Ready for your input:");
        for(int i = 0; i < 10; i++){System.out.print('*');}
        System.out.println();

        // User input
        String calc = input.nextLine();

        // Checks fro parenthisis pairs 
        for(int i = 0; i < (calc.length()); i++){
            if(calc.charAt(i) == ('(')){par = false;}

            if(par == false && calc.charAt(i) == ')'){par = true;} 
        }



        System.out.println("Your input was: " + calc +" Boolean is " + par);
        input.close();

    }
}