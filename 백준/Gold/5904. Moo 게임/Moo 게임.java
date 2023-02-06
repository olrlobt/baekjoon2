
import java.util.Scanner;

public class Main {

    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        solve(0, 3);

    }

    private static int solve(int S, int size) {
        if (N <= size) {
            moo(S, size);
            return 0;
        }
        return solve(S + 1, size * 2 + S + 4);
    }


    private static void moo(int S, int size) {

        int preSize = (size - 3 - S) / 2;

        if (N < preSize) { //좌측
            moo(S - 1, preSize);

        } else if (N > preSize + S + 3) { // 우측

            N = N - preSize - S - 3;
            moo(S - 1, preSize);
        } else { // 중앙

            if (N - preSize == 1) {
                System.out.println("m");
            } else {
                System.out.println("o");
            }
        }
    }
}
