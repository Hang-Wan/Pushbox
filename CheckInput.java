import java.util.Scanner;
/**
 * check the input from
 * uer and return correct value
 * including verify int and boolean value.
 * @author Hang Wan
 */
public class CheckInput {
    private static Scanner in = new Scanner(System.in);

    /* *
    * check yes or no that user entried and transfrom it to boole
    * value true or false.
    */
    public static boolean readYesNo(String message){
        boolean choice = false;//the choice of user
        boolean verify;//judge if user entry true answer
        String answer;// the answer of user
        //check input and transform
        do{
            verify = true;
            System.out.print("please entry your anwser: ");
            answer = in.next();            
            switch(answer.toLowerCase()){
                case "yes" :
                    choice = true;
                    break;
                case "y" :
                    choice = true;
                    break;
                case "true" :
                    choice = true;
                    break;
                case "no" :
                    choice = false;
                    break;
                case "n" :
                    choice = false;   
                    break;
                case "false" :
                    choice = false;  
                    break;
                default: 
                    verify = false; 
                    System.out.println(message + "(yes or no)");                         
            }
        }while(verify == false);

        return choice;
    }

    /* *
    * check a int number that user entried to make sure it is 
    * in a suit range by given max and min value
    */
    public static int readInt(String message, int min, int max){
        int value;// the value of user entry
        System.out.print(message + "(" + min + "-"+ max + "): ");
        value = in.nextInt();
        while(value < min || value > max){
            System.out.println("Value out of rannge");
            System.out.print(message + "(" + min + "-"+ max + "): ");
            value = in.nextInt();
        }
        return value;
    }
}