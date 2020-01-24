import java.util.Scanner;


public class PolyTest{

public static void main(String[] args){

    int base, hight, hyp;
    Scanner input = new Scanner(System.in);
    Triangle poly = new Triangle();

    System.out.println("Please insert the Triangles base: ");

    base = input.nextInt();

    System.out.println("Please enter the Triangles height: ");

    hight = input.nextInt();

    System.out.println("Please enter the Triangles Hypoginoose: ");

    hyp = input.nextInt();

    poly.Perimeter(base, hight, hyp);

    System.out.println("The perimeter of the triangle is " + poly.getPerim() + ".");

    System.out.println("The area of the circle is " + poly.Area() + ".");

    input.close();

}

}