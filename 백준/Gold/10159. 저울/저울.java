import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        map = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];
        for (int relation = 0; relation < M; relation++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

        for (int num = 1; num <= N; num++) {
            Arrays.fill(visited,false);
            sb.append(N - (topDown(num) + bottomUp(num)) + 1).append("\n");
        }
        System.out.println(sb);
    }

    private static int bottomUp(int num) {
        int sum = 1;

        for (int row = 1; row < map.length ; row++) {
            if(map[row][num] && !visited[row]){
                visited[row] = true;
                sum += bottomUp(row);
            }
        }

        return sum;

    }

    private static int topDown(int num) {
        int sum = 1;

        for (int column = 1; column < map.length ; column++) {
            if(map[num][column] && !visited[column]){
                visited[column] = true;
                sum += topDown(column);
            }
        }

        return sum;
    }


}
