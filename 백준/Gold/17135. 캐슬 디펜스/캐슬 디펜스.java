import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] dx = {1, 0, -1};
    static final int[] dy = {0, -1, 0};
    static int[][] map;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < M; column++) {
                map[row][column] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M; j++) {
                for (int k = j + 1; k < M; k++) {
                    result = Math.max(result, solve(D, i, j, k));
                }
            }
        }
//        System.out.println(solve(D, 0, 2, 5));
        System.out.println(result);
    }

    private static int solve(int dis, int one, int two, int three) {

        PriorityQueue<Arrow> queue = new PriorityQueue<>();

        boolean[][] dead = new boolean[map.length + 1][map[0].length];

        int[] killed1;
        int[] killed2;
        int[] killed3;

        int round = 0;
        while (round++ < map.length) {
            queue.clear();
            queue.offer(new Arrow(map.length - round, one, dis - 1));
            killed1 = kill(queue, dead);
            queue.clear();
            queue.offer(new Arrow(map.length - round, two, dis - 1));
            killed2 = kill(queue, dead);
            queue.clear();
            queue.offer(new Arrow(map.length - round, three, dis - 1));
            killed3 = kill(queue, dead);

            dead[killed1[0]][killed1[1]] = true;
            dead[killed2[0]][killed2[1]] = true;
            dead[killed3[0]][killed3[1]] = true;
//            for (int row = 0; row < map.length; row++) {
//                System.out.println(Arrays.toString(dead[row]));
//            }
//            System.out.println(Arrays.toString(killed1));
//            System.out.println(Arrays.toString(killed2));
//            System.out.println(Arrays.toString(killed3));
//            System.out.println(round);
        }

        int count= 0;
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (dead[row][column] ) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int[] kill(PriorityQueue<Arrow> queue, boolean[][] dead) {

        while (!queue.isEmpty()) {
            Arrow curArrow = queue.poll();

//            System.out.println(curArrow.row + " /" + curArrow.column + " / " + curArrow.dis);
            if (map[curArrow.row][curArrow.column] == 1 && !dead[curArrow.row][curArrow.column]) {
//                System.out.println("kill");
                return new int[]{curArrow.row, curArrow.column};
            }
            if (curArrow.dis == 0) {

                continue;
            }

            for (int i = 0; i < 3; i++) {
                int nextRow = curArrow.row + dy[i];
                int nextColumn = curArrow.column + dx[i];

                if (nextRow < 0 || nextColumn < 0 || nextRow >= map.length || nextColumn >= map[0].length) {
                    continue;
                }
                queue.offer(new Arrow(nextRow, nextColumn, curArrow.dis-1));
            }
        }
        return new int[]{dead.length-1, 0};
    }


    private static class Arrow implements Comparable<Arrow> {
        int row;
        int column;
        int dis;

        public Arrow(int row, int column, int dis) {
            this.row = row;
            this.column = column;
            this.dis = dis;
        }

        @Override
        public int compareTo(Arrow o) {
            if (dis == o.dis) {
                return Integer.compare(column, o.column);
            }
            return Integer.compare(o.dis, dis);
        }
    }
}
