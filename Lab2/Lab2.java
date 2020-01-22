import java.util.Scanner;

public class Lab2{

    public static void main(String[] args){
        //boolean ProblemInput(String original,String encrypted){return true or false}
        String test1 = "bannana";
        String test2 = "hellowe";
        //String1
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] compare = new char[26];

        int index = 0;
        while(test1.charAt(index)!= '\0'){
            for(int i = 0; i <= alphabet.length; i++){
                if(alphabet[i] == test1.charAt(i)){
                    if(compare[i] == '\0'){compare[i] = test2.charAt(i);}
                    else if()
                    
                    
                }
            }
        }
        // character array with alphabet
        // compare to alphabet array for compare index to store a character there
        // if taken, compare
    

    }

}