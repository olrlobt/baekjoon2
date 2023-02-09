
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        map = new int[N][N];
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                map[row][column] = sc.nextInt();
            }
        }

        solve(0, 0, N);
    }

    public static void solve(int x, int y, int size) {

        if (size == 1) {
            System.out.println(map[0][0]);
            return;
        }

        int standard = (size / 2);
        for (int row = 0; row < standard; row++) {
            for (int column = 0; column < standard; column++) {
                map[row][column] = secondMax(x + column * 2, y + row * 2);
            }
        }

        solve(0, 0, standard);
    }

    private static int secondMax(int x, int y) {

        int[] sort = new int[4];

        sort[0] = map[y][x];
        sort[1] = map[y][x + 1];
        sort[2] = map[y + 1][x];
        sort[3] = map[y + 1][x + 1];

        Arrays.sort(sort);
        return sort[2];
    }
}
