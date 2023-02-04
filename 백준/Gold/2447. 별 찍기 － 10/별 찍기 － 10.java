import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;
    static char[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], ' ');
        }

        solve(N, 0, 0);

        for (int i = 0; i < N; i++) {
            System.out.println(map[i]);
        }
    }

    private static void solve(int N, int x, int y) {

        if (N == 1) {
            map[y][x] = '*';
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (i == 1 && j == 1) {
                    continue;
                }
                solve(N / 3, x + i * (N / 3), y + j * (N / 3));


            }
        }
    }
}
