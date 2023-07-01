
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[] A = new int[N];
            int[] B = new int[M];

            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }
            for (int i = 0; i < M; i++) {
                B[i] = sc.nextInt();
            }

            if (A.length > B.length) {
                System.out.println("#" + testCase + " " + solve(A , B));
            } else {
                System.out.println("#" + testCase + " " + solve(B , A));
            }
        }
    }

    private static int solve(int[] longer, int[] shorter) {
        int max = 0;

        for (int i = 0; i + shorter.length <= longer.length; i++) {
            int sum = 0;
            for(int j = 0 ; j < shorter.length ; j ++){
                sum += shorter[j] * longer[i + j];
            }

            max = Math.max(sum, max);
        }

        return max;
    }
}
