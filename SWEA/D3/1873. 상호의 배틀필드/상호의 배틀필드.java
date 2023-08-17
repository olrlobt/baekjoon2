import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static char[][] map;
    enum TANK {
        L('<', -1, 0),
        R('>', 1, 0),
        U('^', 0, -1),
        D('v', 0, 1),
        S(' ', 0, 0),
        T('T', 0, 0);

        private Character shape;
        private int dx;
        private int dy;

        TANK(Character shape, int dx, int dy) {
            this.shape = shape;
            this.dx = dx;
            this.dy = dy;
        }

        public static TANK getDirection(char shape) {
            return Arrays.stream(TANK.values()).filter(tank -> tank.shape == shape).findAny().get();
        }

        public static boolean checkTank(char shape) {
            return Arrays.stream(TANK.values()).anyMatch(tank -> tank.shape == shape);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            map = new char[R][C];
            boolean findTank = false;

            TANK curTank = TANK.T;

            for (int row = 0; row < R; row++) {
                String input = br.readLine();
                for (int column = 0; column < C; column++) {
                    map[row][column] = input.charAt(column);

                    if (!findTank && TANK.checkTank(map[row][column])) {
                        curTank.shape = map[row][column];
                        curTank.dx = column;
                        curTank.dy = row;

                        findTank = true;
                    }
                }
            }
            int N = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split("");

            solve(curTank, input);

            sb.append("#").append(testCase).append(" ");

            for (int row = 0; row < R; row++) {
                for (int column = 0; column < C; column++) {
                    sb.append(map[row][column]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void solve(TANK curTank, String[] input) {

        for (String task : input) {
            TANK taskOfTank = TANK.valueOf(task);

            int nextRow = taskOfTank.dy + curTank.dy;
            int nextColumn = taskOfTank.dx + curTank.dx;

            if (taskOfTank.name().equals("S")) { // 슛
                int dx = TANK.getDirection(curTank.shape).dx;
                int dy = TANK.getDirection(curTank.shape).dy;

                while (!(isOverTheMap(nextRow, nextColumn)) && map[nextRow][nextColumn] != '#') {

                    if (map[nextRow][nextColumn] == '*') {
                        map[nextRow][nextColumn] = '.';
                        break;
                    }
                    nextRow += dy;
                    nextColumn += dx;
                }

            } else {
                if (isOverTheMap(nextRow, nextColumn) || map[nextRow][nextColumn] != '.') { // 이동 x 방향만 바뀜
                    map[curTank.dy][curTank.dx] = curTank.shape = taskOfTank.shape;
                    continue;
                }

                map[nextRow][nextColumn] = curTank.shape = taskOfTank.shape;
                map[curTank.dy][curTank.dx] = '.';
                curTank.dx = nextColumn;
                curTank.dy = nextRow;
            }
        }
    }

    private static boolean isOverTheMap(int nextRow, int nextColumn){
        if(nextRow < 0 || nextColumn < 0 || nextRow >= map.length || nextColumn >= map[0].length){
            return true;
        }
        return false;
    }
}
