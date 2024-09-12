import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static char[][] map;
    static int[] able;
    static int N, M;
    static int max;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            able = new int[N];
            int ab = 0;
            map = new char[N][M];
            max = 0;
            for (int row = 0; row < N; row++) {
                String input = br.readLine();
                for (int col = 0; col < M; col++) {
                    map[row][col] = input.charAt(col);
                    if (map[row][col] == '.') {
                        ab ++;
                    }else{
                        able[row] += ab / 2;
                        able[row] += ab % 2;
                        ab = 0;
                    }
                }
                able[row] += ab / 2;
                able[row] += ab % 2;
                ab = 0;
            }

            for (int row = 1; row < N; row++) {
                able[row] += able[row - 1];
            }

            dfs(0, 0, 0, 0, 0);
            sb.append(max).append('\n');
        }
        System.out.println(sb);
    }

    private static void dfs(int row, int col, int count, int preBit, int curBit) {

        if (col >= M) {
            col = 0;
            row++;
            if (row == N) {
                max = Math.max(count, max);
                return;
            }

            int ab = able[N - 1] - able[row - 1];

            if(max > ab + count){
                return;
            }
            preBit = curBit;
            curBit = 0;
        }

        if (map[row][col] == '.') {
            if (!(col - 1 >= 0 && (preBit & (1 << (col - 1))) != 0) && !(col + 1 < M && (preBit & (1 << (col + 1))) != 0)) {
                dfs(row, col + 2, count + 1, preBit, curBit | (1 << col));
            }
        }

        dfs(row, col + 1, count, preBit, curBit);
    }
}
