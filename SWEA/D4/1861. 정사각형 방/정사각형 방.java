import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int[][] map;
    static int[][] visited;

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testcase = 1; testcase <= T; testcase++) {

            int N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            visited = new int[N][N];
            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int column = 0; column < N; column++) {
                    map[row][column] = Integer.parseInt(st.nextToken());
                }
            }
            int max = 0 ;
            int num = Integer.MAX_VALUE;
            int temp = 0;
            for (int row = 0; row < N; row++) {

                for (int column = 0; column < N; column++) {

                    if (visited[row][column] != 0) {
                        continue;
                    }

                    if(max <= ( temp = solve(row, column))){

                        if(max < temp){
                            num = map[row][column];
                        }

                        if(max == temp && num > map[row][column]){
                            num = map[row][column];
                            continue;
                        }

                        max = temp;
                    }

                }
            }
            sb.append("#").append(testcase).append(" ").append(num).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int row, int column) {

        if(visited[row][column] != 0){
            return visited[row][column];
        }
        visited[row][column] +=1;

        int max = 0;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dy[i];
            int nextColumn = column + dx[i];

            if (nextRow < 0 || nextColumn < 0 || nextRow >= map.length || nextColumn >= map[0].length) {
                continue;
            }
            if (map[nextRow][nextColumn] != map[row][column] + 1) {
                continue;
            }

            max = Math.max(solve(nextRow,nextColumn), max) ;
        }

        visited[row][column]+= max;
        return visited[row][column] ;
    }


}
