import java.util.Scanner;
public class FractionCalculator{
    /*
    WORKS :))))))))
    next step: use i think a lot of try catch blocks to make it so users can input the stuff all
    on one line. do it in a different class, FractionCalculatorAdvanced. input in form of 
    [FRAC] [OPERATION] [FRAC]
    reprompt if input is invalid, using stuff from FractionCalculator.
    */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        intro(); //intro method, just text
        String operation = "";
        while(!operation.equals("q") && !operation.equals("Q")){
            operation = getOperation(input);
            Fraction frac1; //both 0 fraction so it doesnt print everytime
            Fraction frac2;
            switch(operation){ //switch for each operation, dont need default bc will reprompt if invalid input
                
                case "q":
                    break;
                
                case "Q":
                    break;
                
                case "=":
                    frac1 = getFraction(input);
                    frac2 = getFraction(input);
                    boolean equals = frac1.equals(frac2);
                    System.out.println(frac1.toString() + " = " + frac2.toString() + " is " + equals);
                    break;

                case "+":
                    frac1 = getFraction(input);
                    frac2 = getFraction(input);
                    Fraction sum = frac1.add(frac2);
                    sum.toLowestTerms();
                    System.out.println(frac1.toString() + " + " + frac2.toString() + " = " + sum.toString());
                    break;
                
                case "-":
                    frac1 = getFraction(input);
                    frac2 = getFraction(input);
                    Fraction diff = frac1.subtract(frac2);
                    diff.toLowestTerms();
                    System.out.println(frac1.toString() + " - " + frac2.toString() + " = " + diff.toString());
                    break;
                
                case "*":
                    frac1 = getFraction(input);
                    frac2 = getFraction(input);
                    Fraction prod = frac1.multiply(frac2);
                    prod.toLowestTerms();
                    System.out.println(frac1.toString() + " * " + frac2.toString() + " = " + prod.toString());
                    break;

                case "/":
                    frac1 = getFraction(input);
                    frac2 = getFraction(input);
                    Fraction quot = frac1.add(frac2);
                    quot.toLowestTerms();
                    System.out.println(frac1.toString() + " / " + frac2.toString() + " = " + quot.toString());
                    break;
            }
            System.out.println("----------------------------------------------------------------------------");
        }
    }


    public static void intro(){
        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply, and divide fractions until you press Q to quit");
        System.out.println("Please enter your fractions in the form (a/b) where a and b are integers.");
        System.out.println("--------------------------------------------------------------------------------");
    }

    public static String getOperation(Scanner input){//loops until they give a valid input
        boolean valid = false;
        System.out.print("Please enter an operation (+, -, /, *, =, or Q to quit): ");
        String op = input.nextLine();
        if(op.equals("+") || op.equals("-") || op.equals("/") || op.equals("*") || op.equals("=") || op.equals("Q") || op.equals("q")){
            valid = true;
        }
        while(valid == false){
            System.out.print("Invalid input (+, -, /, *, =, or Q to quit): ");
            op = input.nextLine();
            if(op.equals("+") || op.equals("-") || op.equals("/") || op.equals("*") || op.equals("=") || op.equals("Q") || op.equals("q")){
                valid = true;
            }
        }
        return op;

    }

    public static boolean validFraction(String frac){//gets rid of whitespace then sees if its a valid fraction
        String fracnew = frac.replaceAll("\\s", "");
        String[] s = fracnew.split("/");//splits into an array
        
        if(s.length==1){
            try{
                int a = Integer.parseInt(s[0]);
            }catch(Exception e){// try catch block so it doesnt throw an exception forany input
                return false; //2 cases for integer (a) vs (a/b)
            }
            return true;
        
        }
        if(s[1].equals("0")){
            return false;
        }try{
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
        }catch(Exception e){//similar try catch block but for (a/b)
            return false;
        }
        return true;
    }

    public static Fraction getFraction(Scanner input){//***scanner input to method very useful***
        boolean valid = false;
        System.out.print("Please enter a fraction (a/b) or an integer (a): ");
        String frac = input.nextLine();
        String fracnew = frac.replaceAll("\\s", "");
        if(validFraction(fracnew)){
            //uses validFraction() and no whitespace to return a fraction from user input
            valid = true;    
        }
        while(!valid){
            System.out.print("Invalid fraction. Please enter (a/b) or an integer (a): ");
            frac = input.nextLine();//loops until valid input is given
            fracnew = frac.replaceAll("\\s", "");
            if(validFraction(fracnew)){
                valid = true;    
            }
        }
        String[] s = fracnew.split("/");//if everything is good, turns it into a Fraction and returns it
            
            if(s.length==1){
                int a = Integer.parseInt(s[0]); //parseint was super helpful
                Fraction fraction = new Fraction(a);
                return fraction;
                
            }else{
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                Fraction fraction = new Fraction(a,b);
                return fraction;
            }
    }
}