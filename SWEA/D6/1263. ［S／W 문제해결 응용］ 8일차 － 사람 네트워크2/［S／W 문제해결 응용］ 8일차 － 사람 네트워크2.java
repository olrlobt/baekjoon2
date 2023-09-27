import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());

            int[][] map = new int[num][num];

            for (int row = 0; row < num; row++) {
                for (int column = 0; column < num; column++) {
                    map[row][column] = Integer.parseInt(st.nextToken());
                    if (map[row][column] == 0) {
                        map[row][column] = 1001;
                    }
                }
            }
            sb.append("#").append(testCase).append(" ").append(solve(map, num)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int[][] map, int num) {

        for (int k = 0; k < num; k++) {
            for (int row = 0; row < num; row++) {
                if (k == row) {
                    continue;
                }
                for (int col = 0; col < num; col++) {
                    if (row == col || k == col) {
                        continue;
                    }
                    map[row][col] = Math.min(map[row][col], map[row][k] + map[k][col]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int row = 0; row < num; row++) {
            min = Math.min(min, Arrays.stream(map[row]).sum() - 1001);
        }
        return min;
    }
}
