
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuffer sb = new StringBuffer();

        int T = sc.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = sc.nextInt();
            int[][] map = new int[N][N];

            sb.append("#").append(testCase).append("\n");
            for (int row = 0; row < N; row++) {
                for (int column = 0; column < N; column++) {
                    map[row][column] = sc.nextInt();
                }
            }

            for (int line = 0; line < N; line++) {
                for (int i = 0; i < N; i++) {
                    sb.append(map[N - i - 1][line]);
                }
                sb.append(" ");
                for (int i = 0; i < N; i++) {
                    sb.append(map[N - line - 1][N - i - 1]);
                }
                sb.append(" ");
                for (int i = 0; i < N; i++) {
                    sb.append(map[i][N - line - 1]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
