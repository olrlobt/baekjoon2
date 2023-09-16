import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int[] result = new int[3];
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int column = 0; column < N; column++) {
                map[row][column] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, N);
        Arrays.stream(result).forEach(value -> sb.append(value).append("\n"));
        System.out.println(sb);
    }

    public static void solve(int x, int y, int size) {

        if (paper(x, y, size)) {
            result[map[y][x] + 1] += 1;
            return;
        }

        size /= 3;

        for(int X_idx = 0; X_idx < 3 ; X_idx ++){
            for (int Y_idx = 0; Y_idx < 3; Y_idx++) {
                solve(x + size * X_idx, y + size *Y_idx, size);
            }
        }
    }

    private static boolean paper(int x, int y, int size) {
        int standard = map[y][x];

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (standard != map[y + row][x + column]) {
                    return false;
                }
            }
        }
        return true;
    }
}
