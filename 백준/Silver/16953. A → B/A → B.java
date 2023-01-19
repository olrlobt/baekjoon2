import java.util.Scanner;

public class Main {

    static int A;
    static int B;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        A = sc.nextInt();
        B = sc.nextInt();
        solve();
    }

    public static void solve() {
        int count = 1;

        while (A != B) {
            count++;

            if (B % 2 == 0 && B != 0) {
                B /= 2;
                continue;
            }
            if (B % 10 == 1) {
                B /= 10;
                continue;
            }

            break;
        }

        if (A != B) {
            count = -1;
        }
        System.out.println(count);
    }
}
