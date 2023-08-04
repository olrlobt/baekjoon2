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
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] result = new int[N][N];

            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int column = 0; column < N; column++) {
                    int cur = Integer.parseInt(st.nextToken());

                    for (int mrow = 0; mrow < M && row - mrow >= 0; mrow++) {
                        for (int mcolumn = 0; mcolumn < M && column - mcolumn >= 0; mcolumn++) {
                            result[row - mrow][column - mcolumn] += cur;
                        }
                    }
                }
            }

            int flatStream = Arrays.stream(result).flatMapToInt(Arrays::stream).boxed().max(Integer::compare).orElse(1);
            sb.append("#").append(testCase).append(" ").append(flatStream).append("\n");
        }
        System.out.println(sb);
    }


}
