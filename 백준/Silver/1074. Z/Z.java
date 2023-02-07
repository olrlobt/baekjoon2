
import java.util.Scanner;

public class Main {

    static int result = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();  // 2^N x 2^N
        int R = sc.nextInt();
        int C = sc.nextInt();
        solve(N, R, C);
    }

    public static void solve(int N, int R, int C) { // N = 제곱 // 다음 재귀땐 N -1


        if (N == 0) {
            System.out.println(result );
            return;
        }

        if (R < Math.pow(2, N - 1)) { // 위 라인

            if (C < Math.pow(2, N - 1)) {//좌측

            } else {
                result += Math.pow(4, N-1);
                C -= Math.pow(2, N - 1);
            }

        } else {  // 아래 라인
            R -= Math.pow(2, N - 1);

            if (C < Math.pow(2, N - 1)) {//좌측
                result += Math.pow(4, N-1) * 2;

            } else {
                result += Math.pow(4, N-1) * 3;
                C -= Math.pow(2, N - 1);
            }
        }

        solve(N - 1, R, C);
    }
}
