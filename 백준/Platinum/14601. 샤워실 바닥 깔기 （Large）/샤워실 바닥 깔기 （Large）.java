import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int R;
    static int C;
    static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int length = (int) Math.pow(2, K);

        C = Integer.parseInt(st.nextToken()) - 1;
        R = length - Integer.parseInt(st.nextToken());


        map = new int[length][length];
        solve(length*2, 0, 0);

        if(count > 19001){
            System.out.println(-1);
            return;
        }

        for (int row = 0; row < length; row++) {
            for (int column = 0; column < length; column++){
                sb.append(map[row][column]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    private static void solve(int length, int r, int c) {
        // r , c  == 시작지점
        if (count > 19001) {
            return;
        }

        if (length == 1 ) {
            map[r][c] = -1;
            return;
        }

        int halfLength = length/2;

        if (R <  r + halfLength && C <  c + halfLength) { // 1사분면

        } else if (R < r + halfLength && C >= c + halfLength) { // 2사분면
            c += halfLength;

        } else if (R >= r + halfLength && C <  c + halfLength) { // 3 사분면
            r += halfLength;

        } else if (R >= r + halfLength && C >=  c + halfLength) { // 4사분면
            r += halfLength;
            c += halfLength;
        }

        solve(halfLength, r, c);
        fillTile(halfLength, r, c);
    }

    private static void fillTile(int length, int r, int c) {

        if (length == 1) {
            return;
        }
        if (length == 2) {
            boolean check = false;
            for (int row = r; row < r + length; row++) {
                for (int column = c; column < c + length; column++) {
                    if (map[row][column] != 0) {
                        continue;
                    }
                    check = true;
                    map[row][column] = count;
                }
            }
            if(check) {
                count++;
            }
            return;
        }

        int halfLength = length / 2;

        fillTile(halfLength, r + halfLength/2, c + halfLength/2);
        fillTile(halfLength, r, c);
        fillTile(halfLength, r+halfLength, c);
        fillTile(halfLength, r, c+ halfLength);
        fillTile(halfLength, r+halfLength, c+ halfLength);

    }
}
