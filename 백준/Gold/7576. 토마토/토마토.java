import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static int[][] tomato;
    static int[] dx = {0, 1, 0, -1}; // 위, 오른쪽 , 아래 , 왼쪽
    static int[] dy = {-1, 0, 1, 0};

    static Queue<int[]> visited = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        tomato = new int[M + 2][N + 2];

        for (int i = 0; i < M + 2; i++) {
            Arrays.fill(tomato[i], -1);
        }

        for (int row = 1; row <= M; row++) {
            for (int column = 1; column <= N; column++) {
                tomato[row][column] = sc.nextInt();

                if (tomato[row][column] == 1) {
                    visited.offer(new int[]{row, column});
                }

            }
        }

        bfs();
    }

    public static void bfs() {
        int max=1;

        while (!visited.isEmpty()) {

            int[] ripeTomato = visited.poll();
            int row = ripeTomato[0];
            int column = ripeTomato[1];

            for (int i = 0; i < 4; i++) {

                if (tomato[row + dy[i]][column + dx[i]] == 0) {
                    visited.offer(new int[]{row + dy[i], column + dx[i]});
                    tomato[row + dy[i]][column + dx[i]] = tomato[row][column] + 1;
                    max = Math.max(max,tomato[row][column] + 1);
                }
            }
        }

        if(Arrays.deepToString(tomato).contains(" 0")){
            System.out.println(-1);
        }else{
            System.out.println(max-1);
        }
    }
}
