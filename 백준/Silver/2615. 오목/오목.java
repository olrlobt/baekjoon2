import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dx = {1, 1, 0, -1};
    private static final int[] dy = {0, 1, 1, 1};
    private static int[][] map = new int[20][20];
    static boolean[][][] visited = new boolean[19][19][4];

    public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("Test5.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int row = 0; row < 19; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < 19; column++) {
                map[row][column] = Integer.parseInt(st.nextToken());
            }
        }
        int[] result = {0,0,0};

        for (int row = 0; row < 19; row++) {
            for (int column = 0; column < 19; column++) {
                if (map[row][column] == 0) {
                    continue;
                }

                for (int dir = 0; dir < 4; dir++) {
                    if (!visited[row][column][dir]) {
                        if (solve(row, column, dir, 0, map[row][column])) {
                            result[0] = map[row][column];
                            if (dir == 3) {
                                result[1] = row+5;
                                result[2] = column-3;
                            } else {
                                result[1] = row+1;
                                result[2] = column+1;
                            }

                        }
                    }
                }
            }
        }
        System.out.println(result[0]);
        if(result[0] != 0){
            System.out.println(result[1] + " " + result[2]);
        }
    }

    private static boolean solve(int row, int column, int dir, int count, int kind) {
        // TODO Auto-generated method stub
        visited[row][column][dir] = true;

        if (row + dy[dir] < 0 || row + dy[dir] >= 19 || column + dx[dir] < 0 || column + dx[dir] >= 19) {
            if (count == 4) {
                return true;
            }
            return false;
        }

        if (count == 4 && map[row + dy[dir]][column + dx[dir]] != kind) {
            return true;
        }

        if (visited[row + dy[dir]][column + dx[dir]][dir] || count == 5
                || map[row + dy[dir]][column + dx[dir]] != kind) {
            return false;
        }

        return solve(row + dy[dir], column + dx[dir], dir, count + 1, kind);
    }
}
