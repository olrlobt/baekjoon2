import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] home = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            int[] company = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            int[][] map = new int[N][2];
            boolean[] visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                map[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            }
            sb.append("#").append(testCase).append(" ").append(getMinDis(calDistance(home, company, map), visited, N, 0, 0)).append("\n");
        }
        System.out.println(sb);
    }


    private static int getMinDis(int[][] distance, boolean[] visited, int cur, int dis, int count) {
        int min = Integer.MAX_VALUE;

        if(count == visited.length){
            return dis + distance[visited.length+1][cur];
        }

        boolean[] nextVisited = Arrays.copyOf(visited, visited.length);

        for (int next = 0; next < nextVisited.length; next++) {
            if (nextVisited[next]) {
                continue;
            }
            nextVisited[next] = true;
            min = Math.min(min, getMinDis(distance, nextVisited, next, dis + distance[cur][next] , count + 1));
            nextVisited[next] = false;
        }
        return min;
    }

    private static int[][] calDistance(int[] home, int[] company, int[][] map) {

        int[][] distance = new int[map.length + 2][map.length + 2];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (distance[i][j] != 0) {
                    continue;
                }
                distance[i][j] = Math.abs(map[i][0] - map[j][0]) + Math.abs(map[i][1] - map[j][1]);
                distance[j][i] = distance[i][j];
            }
            distance[i][map.length] = Math.abs(map[i][0] - home[0]) + Math.abs(map[i][1] - home[1]);
            distance[i][map.length + 1] = Math.abs(map[i][0] - company[0]) + Math.abs(map[i][1] - company[1]);
            distance[map.length][i] = distance[i][map.length];
            distance[map.length + 1][i] = distance[i][map.length + 1];
        }
        return distance;
    }


}
