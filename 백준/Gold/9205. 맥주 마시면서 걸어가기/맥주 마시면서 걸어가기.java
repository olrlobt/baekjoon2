
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static int con;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int testCase = 0; testCase < T; testCase++) {

            con = sc.nextInt();
            int[] start = new int[]{sc.nextInt(), sc.nextInt()};

            map = new int[con + 1][2];
            for (int i = 0; i < con+1; i++) {
                map[i] = new int[]{sc.nextInt(), sc.nextInt()};
            }
            solve(start);
        }
    }

    public static void solve(int[] start) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean end = false;
        boolean [] visited = new boolean[map.length];

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();
            if(cur[0] == map[map.length-1][0] && cur[1] == map[map.length-1][1]){
                end = true;
            }

            for(int i = 0; i < map.length ; i ++){
                int[] con = map[i];

                if(Math.abs(con[0] - cur[0]) + Math.abs(con[1] - cur[1]) > 1000){
                    continue;
                }
                if(visited[i]){
                    continue;
                }
                visited[i] = true;

                queue.offer(con);
            }
        }

        if(end){
            System.out.println("happy");
        }else{
            System.out.println("sad");
        }

    }
}
