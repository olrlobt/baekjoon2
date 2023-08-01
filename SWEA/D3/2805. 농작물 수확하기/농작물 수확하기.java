import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());


        for (int testCase = 1; testCase <=T; testCase++) {
            int N = Integer.parseInt(br.readLine());

            int[][] map = new int[N][N];

            for (int row = 0; row < N; row++) {
                String input = br.readLine();
                for (int column = 0; column < N; column++) {
                    map[row][column] = input.charAt(column)-'0';
                }
            }

            sb.append("#").append(testCase).append(" ").append(solve(N, map)).append("\n");

        }
        System.out.println(sb);
    }

    private static int solve(int n, int[][] map) {
        boolean[][] visited = new boolean[n][n];
        int result = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{map.length / 2, map.length / 2 , 0});
        while (!queue.isEmpty()) {

            int[] curNode = queue.poll();

            if (visited[curNode[0]][curNode[1]] ) {
                continue;
            }
            visited[curNode[0]][curNode[1]]= true;
            result += map[curNode[0]][curNode[1]];
            
            if(curNode[2] == map.length / 2){
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nextRow = curNode[0] + dy[i];
                int nextColumn = curNode[1] + dx[i];

                queue.offer(new int[]{nextRow, nextColumn, curNode[2] + 1});
            }
        }
        return result;
    }
}
