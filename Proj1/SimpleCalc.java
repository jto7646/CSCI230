/* Simple calculator program
    Created by: John "this chair hurts my ass" O'Leary
    Date: Feb/12/2020
    Version: 1.0
*/
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
        Boolean compute = true;                     // If false, will skip the calculation. false if error encountered              
        String testTemp = " ";                  // Used for error testing
        int parenthNum = 0;                     // Used to test for parenthesis pairs

       while(!endProg){ // Loop holding the program
            
            // vv Instruction output vv
            System.out.println("Simple clculator program: Press enter after each opperator or opperand.\nCan use parenthesis, dont include with special opperator.\nType \"=\" to compute. Type \"q\" to quit.");
            System.out.println("Special opperators: First parenthesis added automatically, don't forget to close\nSquare root: sqrt #    Exponent: # pow #    Modulus: # mod #");
            System.out.println("*****************************************************");
            

            // VV USER INPUT VV

            // vv User input loop vv
            while(inputing){ 
                inputString = input.next();
                
                switch (inputString) {
                    case "q":
                        System.out.print("Exiting program: ");
                        endProg = true;
                        inputing = false;
                        compute = false;
                        list.add("q");
                        break;
                    case "=":   // Exits input loop when "=" entered
                        inputing = false;
                        break;
                    // vv Opperator checks vv
                    // ** functions used built under main **
                    case "+":
                        if(opCheck(list) == true){list.add("+");}
                        else{System.out.println("Syntax error, try again.");}
                        break;
                    case "-":
                        if(opCheck(list) == true){list.add("-");}
                        else{System.out.println("Syntax error, try again.");}
                        break;
                    case "*":
                        if(opCheck(list) == true){list.add("*");}
                        else{System.out.println("Syntax error, try again.");}
                        break;
                    case "/":
                        if(opCheck(list) == true){list.add("/");}
                        else{System.out.println("Syntax error, try again.");}
                        break;
                    case "(":
                        if(leftCheck(list) == true){list.add("(");}
                        else{System.out.println("Syntax error, try again.");}
                        break;
                    case ")":
                        if(rightCheck(list) == true){list.add(")");}
                        else{System.out.println("Syntax error, try again.");}
                        break;
                    // vv Special opps checks vv
                    case "sqrt":
                        if(list.size() == 0){ // for empty list, ok
                            list.add("s");
                            list.add("(");
                            System.out.println("(");
                        }
                        else{ // vv checks if last is number of left parenthesis, blocks if number true or parenthesis false
                            if(lastIsNumb(list) == false || list.get(list.size() - 1) == "("){ 
                                list.add("s");
                                list.add("(");
                                System.out.println("("); 
                            }
                            else{System.out.println("Syntax error, try again.");}
                            }
                        break;
                    case "pow":
                        if(list.size() == 0){ // for empty list, bad
                            System.out.println("Syntax error, try again.");
                        }
                        else{ // vv checks if last is number of right parenthesis, blocks if both false vv
                            if(lastIsNumb(list) == true || list.get(list.size() -1 ) == ")"){ 
                                list.add("p");
                                list.add("(");
                                System.out.println("("); 
                            }
                            else{System.out.println("Syntax error, try again.");}
                            }
                        break;
                    case "mod":
                        if(list.size() == 0){ // for empty list, bad
                            System.out.println("Syntax error, try again.");
                        }
                        else{ // vv checks if last is number of right parenthesis, blocks if both false vv
                            if(lastIsNumb(list) == true || list.get(list.size() -1 ) == ")"){ 
                                list.add("m");
                                list.add("(");
                                System.out.println("("); 
                            }
                            else{System.out.println("Syntax error, try again.");}
                            }
                        break;
                    default:
                        if(list.size() == 0){
                            if(isNumb(inputString) == true){list.add(inputString);}
                            else{System.out.println("Syntax error, try again.");}
                        }
                        else{
                            if(lastIsNumb(list) == false && isNumb(inputString) && list.get(list.size() - 1) !=")"){list.add(inputString);}
                            else{System.out.println("Syntax error, try again.");}
                        }
                        break;
                }
            }//bottom input loop
            inputing = true;

            // vv Prints out user input vv
            System.out.print("Your input: ");
            for(int i = 0; i <= list.size() - 1; i++){
                System.out.print(list.get(i) + " ");
            }
            System.out.println(" ");


            // VV ERROR CHECKING VV

            // vv Checks the last entered item on the list for correctness vv
            if(!endProg && lastIsNumb(list) == false && list.get(list.size() - 1) != ")"){
                System.out.println("Syntax error: end of input string");
                compute = false; // Error, skips computation
            }
           

            // vv Used to check for correct parenthesis pairs vv
            // Adds one to counter if Left, subtracts one if right. If counter is 0, the parenthesis are paired
            for(int i = 0; i <= list.size() - 1; i++){
                testTemp = list.get(i); // Stores next item in array list for checking
                switch (testTemp) {
                    case "(":
                        parenthNum++;
                        break;
                    case ")":
                        parenthNum -= 1;
                    default:
                        break;
                }
            }
            if(parenthNum != 0){
                System.out.println("Syntax error: missmatched parenthesis.");
                    compute = false; // Error, skips computation
            }


            // VV COMPUTATION VV

            // vv Calls function to do the calculations, functions below main
            if(compute){
            calc(list);

            if(list.contains("Infinity")){ // list will contain "Infinity" if there was something divided by zero
                System.out.println("Math Error: Divide by Zero");
            }
            else{
                System.out.println("Answer: " + list + "\n\n\n");  // prints answer if there wasnt an issue
            }
            }
            compute = true;
            list.clear();

        };//bottom progLoop
        System.out.println("Goodbye");
        input.close();
    }//bottom main()








    // VV FUNCTION LIST VV



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
        if(isNumb(temp) == true || temp == ")"){return true;} 
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

    // vv Function for doing opperations within parenthesis
    public static void parOps(ArrayList<String> arr, int index){
        arr.remove(index); // removes the "("

        // vv loop for reading through arraylist vv
        for(int i = index; i <= arr.size() - 1; i++){
            switch (arr.get(i)) { // item in arraylist at i
                case ")":      // if right parenth, exit function
                    arr.remove(i);// removes ")"
                    return;
                case "(":      // if left parenth, use recursion to calculate inner parenthesis
                    parOps(arr, i);
                    break;
                default:        // if a number, do inner opperations
                    if(isNumb(arr.get(i))){
                        Double tempD1 = Double.parseDouble(arr.get(i));
                        Double tempD2 = 0.00;

                        // vv checks the opperator next to the number vv 
                        switch (arr.get(i + 1)) {
                            case ")":
                                arr.remove(i + 1); // removes ")"
                                return;
                            // vv normal ops vv
                            case "*":
                                if(arr.get(i+2) == "("){parOps(arr, i + 2);}                                                    
                                tempD2 = Double.parseDouble(arr.get(i+2)); // grabs number to right of opperator
                                tempD1 = tempD1 * tempD2;
                                arr.remove(i);      // removes first number
                                arr.remove(i);      // removes opperator
                                arr.remove(i);      // removes number to right of opperator
                                arr.add(i, Double.toString(tempD1));    // puts answer in list
                                break;
                            case "/":
                                if(arr.get(i+2) == "("){parOps(arr, i + 2);} 
                                tempD2 = Double.parseDouble(arr.get(i+2)); // div by zero error check? try catch
                                try {
                                    tempD1 = tempD1 / tempD2;
                                } catch (Exception e) {
                                    System.out.println("Error: Divide by zero");
                                    return;
                                }
                                arr.remove(i);
                                arr.remove(i);
                                arr.remove(i);
                                arr.add(i, Double.toString(tempD1));
                                break;
                            case "+":
                                if(arr.get(i+2) == "("){parOps(arr, i + 2);}                                                
                                tempD2 = Double.parseDouble(arr.get(i+2));
                                tempD1 = tempD1 + tempD2;
                                arr.remove(i);
                                arr.remove(i);
                                arr.remove(i);
                                arr.add(i, Double.toString(tempD1));
                                break;
                            case "-":
                                if(arr.get(i+2) == "("){parOps(arr, i + 2);}                                                    
                                tempD2 = Double.parseDouble(arr.get(i+2));
                                tempD1 = tempD1 - tempD2;
                                arr.remove(i);
                                arr.remove(i);
                                arr.remove(i);
                                arr.add(i, Double.toString(tempD1));
                                break;
                            // vv special ops vv
                            case "m":
                                if(arr.get(i+2) == "("){parOps(arr, i + 2);}   
                                tempD2 = Double.parseDouble(arr.get(i+2));
                                tempD1 = tempD1 % tempD2;
                                arr.remove(i);
                                arr.remove(i);
                                arr.remove(i);
                                arr.add(i, Double.toString(tempD1));
                                break;
                            case "p":                                                                                                   
                                parOps(arr, i + 2); // Does opperations inside power parenthesis
                                tempD2 = Double.parseDouble(arr.get(i + 2));
                                tempD1 = Math.pow(tempD1, tempD2);
                                arr.remove(i); // delete first # 
                                arr.remove(i); // delete p
                                arr.remove(i); // delete second #
                                arr.add(i, Double.toString(tempD1)); // replace with answer
                                break;
                            default:
                                System.out.println("Error: parOps_NumSwitch");
                                return;
                        }
                    }
                    else{ i -= 2; } // For indexing issue with nested parenthesis
                    break;
            }
        }; 
    }//bottom parOps()


    // vv function does opperations outside of parenthesis
    public static void calc(ArrayList<String> arr){
        Double tempD1 = 0.00;
        Double tempD2 = 0.00;

        // vv Loop calculates things inside parenthesis, Function above
        for(int i = 0; i <= arr.size() - 1; i++){
            if(arr.get(i) == "("){
                parOps(arr, i); 
            }
        }

        // vv Loop calculates sqrt and pow
        for(int i = 0; i <= arr.size() - 1; i++){
            switch (arr.get(i)) {
                case "s":
                    tempD1 = Double.parseDouble(arr.get(i + 1));
                    tempD1 = Math.sqrt(tempD1);
                    arr.remove(i);
                    arr.remove(i);
                    arr.add(i, Double.toString(tempD1));
                    break;
                case "p": 
                    //parOps(arr, i + 2); // Does opperations inside power parenthesis
                    tempD1 = Double.parseDouble(arr.get(i - 1));
                    tempD2 = Double.parseDouble(arr.get(i + 1));
                    tempD1 = Math.pow(tempD1, tempD2);
                    arr.remove(i); // delete first # 
                    arr.remove(i); // delete p
                    arr.remove(i); // delete second #
                    arr.add(i, Double.toString(tempD1)); // replace with answer
                    break;
                default:
                    break;
            }
        }
        
        // vv loop calculates Multiply/Divide/Modulus
        for(int i = 0; i <= arr.size() - 1; i++){
            switch (arr.get(i)) {
                case "*":
                    tempD1 = Double.parseDouble(arr.get(i - 1)); // grabs number from left
                    tempD2 = Double.parseDouble(arr.get(i + 1)); // grabs number from right
                    tempD1 = tempD1 * tempD2; // does opperation
                    i -= 1;                    // moves index to number on left
                    arr.remove(i);  // removes left number
                    arr.remove(i);  // removes opperator
                    arr.remove(i);  // removes right number 
                    arr.add(i, Double.toString(tempD1)); // replaces with answer
                    break;
                case "/":
                    tempD1 = Double.parseDouble(arr.get(i - 1));
                    tempD2 = Double.parseDouble(arr.get(i + 1));
                    try {
                        tempD1 = tempD1 / tempD2;
                    } catch (Exception e) {
                        System.out.println("Error: Divide by Zero");
                    }
                    i -= 1;
                    arr.remove(i);
                    arr.remove(i);
                    arr.remove(i);
                    arr.add(i, Double.toString(tempD1));
                    break;
                case "m":
                    tempD1 = Double.parseDouble(arr.get(i - 1));
                    tempD2 = Double.parseDouble(arr.get(i + 1));
                    tempD1 = tempD1 % tempD2;
                    i -= 1;
                    arr.remove(i);
                    arr.remove(i);
                    arr.remove(i);
                    arr.add(i, Double.toString(tempD1));
                    break;
                default:
                    break;
            }
        }

        // Loop Calculates Add/Subtract
        for(int i = 0; i <= arr.size() - 1; i++){
            switch (arr.get(i)) {
                case "+":
                tempD1 = Double.parseDouble(arr.get(i - 1));
                tempD2 = Double.parseDouble(arr.get(i + 1));
                tempD1 = tempD1 + tempD2;
                i -= 1;
                arr.remove(i);
                arr.remove(i);
                arr.remove(i);
                arr.add(i, Double.toString(tempD1));
                break;
            case "-":
                tempD1 = Double.parseDouble(arr.get(i - 1));
                tempD2 = Double.parseDouble(arr.get(i + 1));
                tempD1 = tempD1 - tempD2;
                i -= 1;
                arr.remove(i);
                arr.remove(i);
                arr.remove(i);
                arr.add(i, Double.toString(tempD1));
                break;
            default:
                break;
            }
        }
       return; 
    }//bottom calc()
    
}//bottom class











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