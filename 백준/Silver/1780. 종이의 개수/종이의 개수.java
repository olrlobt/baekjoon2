
import java.util.Scanner;

public class Main {

    static int[] result = new int[3];
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

        for(int i = 0 ; i < 3 ; i ++){

            System.out.println(result[i]);
        }
    }

    public static void solve(int x, int y, int size) {

        if (paper(x, y, size)) {
            result[map[y][x] + 1] += 1;
            return;
        }

        size /= 3;

        solve(x, y, size);
        solve(x, y + size, size);
        solve(x, y + size * 2, size);

        solve(x + size, y, size);
        solve(x + size, y + size, size);
        solve(x + size, y + size * 2, size);

        solve(x + size * 2, y, size);
        solve(x + size * 2, y + size, size);
        solve(x + size * 2, y + size * 2, size);

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
