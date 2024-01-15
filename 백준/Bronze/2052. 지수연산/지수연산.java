import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        BigDecimal result = BigDecimal.ONE.divide(new BigDecimal(2).pow(N));

        System.out.println(result.toPlainString());
    }
}
