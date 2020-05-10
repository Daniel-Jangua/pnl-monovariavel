import java.util.Scanner;

public class uso_interpretador {
    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        String f = "";
        double x = 0;
        System.out.print("f(x) = ");
        f = in.nextLine();
        System.out.print("x = ");
        x = in.nextDouble();
        double fx = 0;
        try{
            fx = Interpretador.FxR1(f, x);
            System.out.println("f(x) = " + fx);
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }    
}