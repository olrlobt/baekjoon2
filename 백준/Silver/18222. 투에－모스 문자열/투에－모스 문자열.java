
import java.util.Scanner;

public class Main {
    static long[] arr;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long K = sc.nextLong();
        solve(K);
    }

    public static void solve(long K) {

        arr = new long[64];
        arr[0] = 1;
        for (int i = 1; i < 64; i++) {
            arr[i] = arr[i - 1] * 2;
        }

        cal(K);
        System.out.println(count%2);
    }

    private static void cal(long K) {

        if (K == 1) {
            return ;
        }
        count++;
        for (int i = 0; i < 64; i++) { // arr1 = 2 // arr2 = 4
            if (arr[i] >= K) {
                cal(K - arr[i-1]);
                break;
            }
        }
    }
}
