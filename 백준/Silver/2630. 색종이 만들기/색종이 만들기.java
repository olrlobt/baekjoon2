import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int white = 0;
    static int blue = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int row = 0; row < N; row++) {
            String st = br.readLine();
            for (int j = 0, inx = 0; j < N; j++, inx+=2) {
                map[row][j] = st.charAt(inx)-'0';
            }
        }

        solve(0, 0, N);
        sb.append(white).append("\n").append(blue);
        System.out.println(sb);
    }

    public static void solve(int x, int y, int size) {

        if (checkColor(x, y, size)) {
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

    private static boolean checkColor(int x, int y, int size) {
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
