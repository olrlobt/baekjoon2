
import java.util.Scanner;

public class Main {

    static int[][] map;
    static int white = 0;
    static int blue = 0;

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
        System.out.println(white);
        System.out.println(blue);
    }

    public static void solve(int x, int y, int size) {

        if (colorCheck(x, y, size)) {
            if (map[y][x] == 1) {
                blue++;
            }else {
                white++;
            }
            return;
        }

        size /= 2;

        solve(x, y, size);
        solve(x, y + size, size);
        solve(x + size, y, size);
        solve(x + size, y + size, size);

    }

    private static boolean colorCheck(int x, int y, int size) {
        int standard = map[y][x];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (standard != map[y + i][x + j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
