
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static int K;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // city
        int M = sc.nextInt(); // load
        K = sc.nextInt(); // distance
        int X = sc.nextInt(); // start

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int num = 0; num < M; num++) {
            map.get(sc.nextInt()).add(sc.nextInt());
        }
        solve(X);
    }

    public static void solve(int start) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});
        boolean[] visited = new boolean[map.size()];
        int [] dp = new int[map.size()];

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();
            if (visited[cur[0]]) {
                continue;
            }
            visited[cur[0]] = true;

            dp[cur[0]] = cur[1];

            for (int next : map.get(cur[0])) {
                queue.offer(new int[]{next, cur[1] + 1});
            }
        }



        int count = 0;
        for(int i = 0 ; i < map.size() ; i ++){
            if(dp[i] == K){
                count ++;
                System.out.println(i);
            }
        }

        if(count == 0){
            System.out.println(-1);
        }
    }
}
