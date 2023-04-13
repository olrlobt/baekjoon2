
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {


    static int[] map = new int[101];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();


        for (int line = 0; line < N + M; line++) {
            map[sc.nextInt()] = sc.nextInt();
        }
        System.out.println(solve(1));
    }

    public static int solve(int start) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});
        boolean[] visited = new boolean[101];


        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            if (cur[0] == 100) {
                return cur[1];
            }
            if(visited[cur[0]]){
                continue;
            }
            visited[cur[0]] = true;
            for (int i = 1; i <= 6; i++) {

                if(i+cur[0] > 100){
                    continue;
                }

                if (map[i+cur[0]] != 0) {
                    queue.offer(new int[]{map[cur[0] + i], cur[1] + 1});
                } else {
                    queue.offer(new int[]{cur[0] + i, cur[1] + 1});
                }
            }
        }

        return -1;
    }

    public static class Node implements Comparable<Node>{

        int index;
        int num;

        public Node(int index, int num) {
            this.index = index;
            this.num = num;
        }


        @Override
        public int compareTo(Node o) {
            return o.num - this.num;
        }
    }
}
