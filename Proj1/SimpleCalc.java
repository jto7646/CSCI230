import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Character;
import java.lang.Double;


public class SimpleCalc {

    public static void main(String[] args) {

        /* Instatiations */
        Scanner input = new Scanner(System.in);     // Scanner that handles user input
        String inputString = " ";                   // String for temporary input storage
        ArrayList<String> list = new ArrayList<>(); // Used to store user input
        Boolean endProg = false;                    // Ends the program is user chooses; true = exit
        Boolean inputing = true;                    // Ends the user input loop; false = exit
        Boolean compute = false;                
        Boolean testing = true;
        String testTemp = " ";                  // Used for error testing
        int parenthNum = 0;                     // Used to test for parenthesis pairs
        int test = 0;//TESTING

       // while(!endProg){ // Loop holding the program
            
            // vv Instruction output vv
            System.out.println("Simple clculator program: Press enter after each opperator or opperand.\nCan use parenthesis, dont include with special opperator.\nType \"=\" to compute");
            System.out.println("Special opperators:\nSquare root: sqrt   Exponent: pow   Modulus: mod");
            System.out.println("*****************************************************");
            

            // vv User input loop vv
            while(inputing){ 
                inputString = input.next();
                
                switch (inputString) {
                    case "=":   // Exits input loop when "=" entered
                        inputing = false;
                        break;
                    // vv Opperator checks vv
                    // ** functions used built under main **
                    case "+":
                        if(opCheck(list) == true){list.add("+");}
                        else{System.out.println("Syntax error, not added.");}
                        break;
                    case "-":
                        if(opCheck(list) == true){list.add("-");}
                        else{System.out.println("Syntax error, not added.");}
                        break;
                    case "*":
                        if(opCheck(list) == true){list.add("*");}
                        else{System.out.println("Syntax error, not added.");}
                        break;
                    case "/":
                        if(opCheck(list) == true){list.add("/");}
                        else{System.out.println("Syntax error, not added.");}
                        break;
                    case "(":
                        if(leftCheck(list) == true){list.add("(");}
                        else{System.out.println("Syntax error, not added.");}
                        break;
                    case ")":
                        if(rightCheck(list) == true){list.add(")");}
                        else{System.out.println("Syntax error, not added.");}
                        break;
                    // vv Special opps checks vv
                    case "sqrt":
                        if(lastIsNumb(list) == false){
                            list.add("s");
                            list.add("(");
                            System.out.println("(");
                        }
                        else{System.out.println("Syntax error, not added.");}
                        break;
                    case "pow":
                        if(lastIsNumb(list) == false){
                            list.add("p");
                            list.add("(");
                            System.out.println("("); 
                        }
                        else{System.out.println("Syntax error, not added.");}
                        break;
                    case "mod":

                        if(lastIsNumb(list) == false){
                            list.add("m");
                        }
                        else{System.out.println("Syntax error, not added.");}
                        break;
                    default:
                        

                        if(list.size() == 0){
                            if(isNumb(inputString) == true){list.add(inputString);}
                            else{System.out.println("Syntax error, not added.");}
                        }
                        else{
                            if(lastIsNumb(list) == false && isNumb(inputString) && list.get(list.size() - 1) !=")"){list.add(inputString);}
                            else{System.out.println("Syntax error, not added.");}
                        }
                        break;
                }
            }
            inputing = true;

            // vv Checks the last entered item on the list for correctness vv
            if(lastIsNumb(list) == false || list.get(list.size() - 1) != ")"){
                System.out.println("Syntax error: end of input string");
                // SKIP CALCULATION STEP
            }
           

            // vv Used to check for correct parenthesis pairs vv
            // Adds one to counter if Left, subtracts one if right. If counter is 0, the parenthesis are paired
            for(int i = 0; i < list.size() - 1; i++){
                testTemp = list.get(i); // Stores next item in array list for checking
                switch (testTemp) {
                    case "(":
                        parenthNum++;
                        break;
                    case ")":
                        parenthNum--;
                    default:
                        break;
                }
            }
            if(parenthNum != 0){
                System.out.println("Syntax error: missmatched parenthesis.");
                    // SKIP COMPUTATION STEP!!
            }


                

               
        System.out.println(list);
        System.out.println("Size " + list.size());//TESTING
        input.close();
    } 












    // vv Function testing for proper opperand placement vv
    public static Boolean opCheck(ArrayList<String> arr){
        String temp = " ";
        // vv Check fails if array is empty 
        if(arr.size() == 0){return false;} 
        temp = arr.get(arr.size() - 1);
        // vv check fails if last item is not a number or a right parenthesis
        if(isNumb(temp) == true){return true;} 
        else if(temp == ")"){return true;}
        else{return false;}
    }

    // vv Function that checks if the last item on the list is a number vv
    public static Boolean lastIsNumb(ArrayList<String> arr){
        if(arr.size() == 0){return false;}
        if(isNumb(arr.get(arr.size() - 1)) == true){return true;}
        else{return false;}
    }

    // vv function testing for proper number input vv 
    public static Boolean isNumb(String in){
        Double tempDouble = 0.00;
        
        try {
            tempDouble = Double.parseDouble(in);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // vv function testing for proper parenthesis input
    public static Boolean rightCheck(ArrayList<String> arr){
        String temp = " ";
        // vv Check fails if array is empty 
        if(arr.size() == 0){return false;} 
        temp = arr.get(arr.size() - 1);
        // vv check fails if last item is not a number
        if(isNumb(temp) == true){return true;} 
        else{return false;}
    }

     // vv function testing for proper parenthesis input
    public static Boolean leftCheck(ArrayList<String> arr){
        String temp = " ";
        // vv Check fails if array is empty 
        if(arr.size() == 0){return true;} 
        temp = arr.get(arr.size() - 1);
        // vv check fails if last item isn't an opperator or right parenthesis
        if(isNumb(temp) == false && temp != ")"){return true;} 
        else{return false;}
    }
    
}











// Create Scanner for input !DONE!
// Create Array list to store problem   !DONE!
// Create Program loop with exit code   !DONE!
// Print out instructions   !DONE!

    //Loop for input, While loop with exit code?   !DONE! 
        // - Add opperator/opperand to list after every enter

// Error Checking Sequence !DONE!
    // Run throught and check for proper parenthisis - loop !DONE!
        // - They have pairs
        // - If error, throw perenthisis error
    // Run through and check for propper opperators - loop !DONE!
        // - have numbers between them
        // - Throw error if wrong
    // Run through and check for proper opperands - loop !DONE!
        // - have oppereators between, no double decimals, ect...
        // - Throw error if wrong
    // Run through and check for complex opperands (sqrt, pow, ect) - loop !DONE!
        // - Correct spelling, have stuff in them
        // - throw error if wrong.

// Do Problem - follow PMDAS
    // Parenthisis first - loop
        // find parenthisis pair, then do the opperation in the middle
        // Delete the opperands/opperators, replace with answer
    // Do mult/div - loop
        // including sqrt, pow
    // Do addition and subtraction - loop

// Output the answer! -Array list will be size 1 by the end
    // prompt for another problem, or go straight into it