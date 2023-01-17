import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int H;

    static int[][][] box;

    static int[] dx = {0, 1, 0, -1, 0, 0}; // 앞, 오른쪽 , 뒤 , 왼쪽 , 위 , 아래
    static int[] dy = {-1, 0, 1, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    static Queue<int[]> ripeTomato = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();
        H = sc.nextInt();

        box = new int[N][M][H];

        for (int height = 0; height < H; height++) {
            for (int row = 0; row < N; row++) {
                for (int column = 0; column < M; column++) {
                    box[row][column][height] = sc.nextInt();
                    if (box[row][column][height] == 1) {

                        ripeTomato.offer(new int[]{row, column, height});
                    }

                }
            }
        }

        System.out.println(solve());
    }

    public static int solve() {

        Queue<int[]> newRipeTomato;

        int day = -1;

        while (!ripeTomato.isEmpty()) {
            day++;
            newRipeTomato = new LinkedList<>(ripeTomato);
            ripeTomato.clear();

            while (!newRipeTomato.isEmpty()) {

                int[] tomato = newRipeTomato.poll();
                int row = tomato[0];
                int column = tomato[1];
                int height = tomato[2];

                for (int i = 0; i < 6; i++) {

                    int nextRow = row + dy[i];
                    int nextColumn = column + dx[i];
                    int nextHeight = height + dz[i];

                    if (nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= M || nextHeight < 0
                            || nextHeight >= H) {
                        continue;
                    }

                    if (box[nextRow][nextColumn][nextHeight] == 0) {
                        box[nextRow][nextColumn][nextHeight] = 1;
                        ripeTomato.offer(new int[]{nextRow, nextColumn, nextHeight});
                    }
                }
            }
        }

        for (int height = 0; height < H; height++) {
            for (int row = 0; row < N; row++) {
                for (int column = 0; column < M; column++) {
                    if (box[row][column][height] == 0) {
                        day = -1;
                    }
                }
            }
        }

        return day;

    }
}
