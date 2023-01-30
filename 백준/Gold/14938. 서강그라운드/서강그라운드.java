
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int R;
    static int[] items;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        R = sc.nextInt();

        items = new int[N + 1];
        map = new int[N + 1][N + 1];

        Arrays.fill(map[0], Integer.MAX_VALUE);
        for (int area = 1; area <= N; area++) {
            items[area] = sc.nextInt();
            Arrays.fill(map[area], Integer.MAX_VALUE);
        }

        for (int line = 0; line < R; line++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int distance = sc.nextInt();

            map[start][end] = distance;
            map[end][start] = distance;
        }

        solve();

        int[] result = Arrays.copyOf(items,N+1);
        int max = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == Integer.MAX_VALUE || map[i][j] > M) {
                    continue;
                }

                result[i] += items[j];
            }

            if(result[i] > max){
                max = result[i];
            }
        }

        System.out.println(max);

    }

    public static void solve() {

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {

                    if (k == i || k == j || i == j) {
                        continue;
                    }
                    if (map[i][k] == Integer.MAX_VALUE || map[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                        map[j][i] = map[i][k] + map[k][j];
                    }
                }
            }
        }


    }

}
