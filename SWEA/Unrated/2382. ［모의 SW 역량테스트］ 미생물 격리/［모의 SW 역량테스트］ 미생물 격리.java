import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static NotLive[][] map ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 셀 수
            int M = Integer.parseInt(st.nextToken()); // 격리 시간
            int K = Integer.parseInt(st.nextToken()); // 군집 개수

            map = new NotLive[N][N];

            for (int group = 0; group < K; group++) {
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int column = Integer.parseInt(st.nextToken());
                int number = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());

                map[row][column] = new NotLive(row, column, number, direction % 2 == 0 ? 1 : -1, direction < 3);
            }

            sb.append("#").append(testCase).append(" ").append(solve(M)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int m) {
        Queue<NotLive> queue = new ArrayDeque<>();

        while (m-- > 0) {
            for (int row = 0; row < map.length; row++) {
                for (int column = 0; column < map.length; column++) {
                    if (map[row][column] == null) {
                        continue;
                    }
                    map[row][column].save = 0;
                    queue.offer(map[row][column]);
                    map[row][column] = null;
                }
            }

            while (!queue.isEmpty()) {
                NotLive cur = queue.poll();
                move(cur);
                map[cur.row][cur.column] = cur;
            }
        }

        int sum = 0;
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map.length; column++) {
                if (map[row][column] == null) {
                    continue;
                }
                sum += map[row][column].number;
            }
        }
        return sum;
    }

    private static void move(NotLive cur) {

        if (cur.isVertical) {
            cur.row += cur.moving;
        } else {
            cur.column += cur.moving;
        }
        NotLive next = map[cur.row][cur.column];

        if (next != null) {
            cur.save = cur.number;
            if ((next.save != 0 ? next.save : next.number) > cur.number) {
                cur.isVertical = next.isVertical;
                cur.moving = next.moving;
                cur.save = next.number;
            }
            cur.number += next.number;
        }

        if (cur.row == 0 || cur.row == map.length - 1 || cur.column == 0 || cur.column == map.length - 1) {
            cur.number /= 2;
            cur.moving *= -1;
        }
    }


    private static class NotLive {

        int row;
        int column;
        int number;
        int moving;
        boolean isVertical;
        int save;

        public NotLive(int row, int column, int number, int moving, boolean isVertical) {
            this.row = row;
            this.column = column;
            this.number = number;
            this.moving = moving;
            this.isVertical = isVertical;
        }
    }
}
