
import java.util.Scanner;

public class Main {

    static int R;
    static int C;
    static int[][] map;

    static boolean[] visited = new boolean[27];
    static int[] dx = {0, 1, 0, -1}; // 위, 오른쪽 , 아래 , 왼쪽
    static int[] dy = {-1, 0, 1, 0};
    static int max = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        map = new int[R + 2][C + 2];

        for (int row = 1; row <= R; row++) {
            String input = sc.next();

            for (int column = 1; column <= C; column++) {
                map[row][column] = input.charAt(column - 1) - 'A' + 1;
            }
        }

        solve();
    }

    public static void solve() {

        dfs(1, 1, 0);
        System.out.println(max);
    }

    public static void dfs(int row, int column, int count) {

        if (visited[map[row][column]]) { // 이미 방문한 적이 있다.
            max = Math.max(max, count);
            return;
        }

        visited[map[row][column]] = true; // 방문처리

        for (int i = 0; i < 4; i++) {

            if(map[row+dy[i]][column+dx[i]] == 0){ // 그래프 범위를 벗어났을때.
                continue;
            }
            dfs(row + dy[i], column + dx[i], count + 1); //dfs
        }
        visited[map[row][column]] = false; // 위의 dfs에서 모든 경우를 찾았으니 false
    }
}
