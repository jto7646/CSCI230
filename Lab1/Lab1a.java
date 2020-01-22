import java.util.Scanner;

public class Lab1a {

public static void main(String[] args){

    Scanner input = new Scanner(System.in);

    System.out.println("Please input a number: ");

    String n = input.nextLine();

    int temp1, sum = 0;
    int temp2 = 0;
    int prod = 1;

    int[] array = new int[n.length()];

    for(int i = 0; i <= (n.length() - 1); i++){
        array[i] = Character.getNumericValue(n.charAt(i));
    }

    for(int i = 0; i <= (n.length()-1); i++){
        temp1 = array[i];
        temp2 = temp2 + temp1;
    }

    sum = temp2;
    
    for(int i = 0; i <= (n.length()-1); i++){
        temp1 = array[i];
        prod *= temp1;
    }

    System.out.println("The digits added together equal " + sum);

    System.out.println("The digits multiplied together are " + prod);

    System.out.println("The lab objective is " + (sum + prod));
    
    
    input.close();
}

}