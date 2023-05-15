
import java.lang.reflect.Array;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int N;
    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,-1,0,1};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String input = sc.next();

            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }

        }

        int count = 1;
        PriorityQueue<Integer> result = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] == 1 && !visited[i][j]) {
                    result.offer(solve(i, j));
                    count++;
                }

            }
        }

        System.out.println(count - 1); // 총 단지수
        while (!result.isEmpty()){
            System.out.println(result.poll());
        }
    }

    public static int solve(int row, int column) {

        Queue<int []> queue = new LinkedList<>();
        queue.offer(new int []{ row,column});
        int count = 0 ;

        while (!queue.isEmpty()){

            int [] cur = queue.poll();
            int curRow = cur[0];
            int curColumn = cur[1];

            if(visited[curRow][curColumn]){
                continue;
            }
            visited[curRow][curColumn] = true;
            count ++;

            for(int i = 0 ; i < 4; i ++){

                int nextRow = curRow + dy[i];
                int nextColumn = curColumn + dx[i];

                if(nextColumn < 0 || nextRow < 0 || nextRow >= N || nextColumn >= N){
                    continue;
                }
                if(map[nextRow][nextColumn] == 0){
                    continue;
                }

                queue.offer(new int[] { nextRow, nextColumn});
            }
        }

        return count;

    }
}
