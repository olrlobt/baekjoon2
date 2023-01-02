
import java.util.Scanner;

public class Main {

    static Long S;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

         S= sc.nextLong();

        solve();
    }

    public static void solve(){
        Long sum = 0L;
        int i=0;
        while(sum <= S){
            i++;
            sum += i;
         
        }

        System.out.println(i-1);
    }
}
