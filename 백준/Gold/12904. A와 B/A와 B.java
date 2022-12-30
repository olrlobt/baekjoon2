import java.util.Scanner;

public class Main {

    static String S;
    static StringBuffer T;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        S = sc.nextLine();
        String Ts = sc.nextLine();
        T = new StringBuffer(Ts);

        if(solve()){
            System.out.println("1");
        }else {
            System.out.println("0");
        }
    }

    public static boolean solve() {

        do{

            while(removeA());
        }while (T.length() > S.length() && removeB());

        return verify();
    }

    private static boolean verify() {
        if(T.toString().equals(S) ){
            return true;
        }
        return false;
    }

    private static boolean removeB() {
        if(T.charAt(T.length()-1) == 'B'){
            T.deleteCharAt(T.length()-1);
            T.reverse();
            return true;
        }
        return false;
    }

    private static boolean removeA() {

        if(T.charAt(T.length()-1 )== 'A'){
            if(T.length() <= S.length()){
                return false;
            }
            T.deleteCharAt(T.length()-1);
            return true;
        }
        return false;
    }
}
