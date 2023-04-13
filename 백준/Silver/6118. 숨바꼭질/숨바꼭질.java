
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); //헛간 갯수
        int M = sc.nextInt(); // 라인

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int line = 0; line < M; line++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            map.get(start).add(end);
            map.get(end).add(start);
        }

        solve(1);
    }

    public static void solve(int index) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{index, 0});
        int[] visited = new int[N + 1];
        visited[1] = -1;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            for (int node : map.get(cur[0])) {

                if (visited[node] != 0) {
                    continue;
                }
                visited[node] = cur[1] + 1;

                queue.offer(new int[]{node, cur[1] + 1});
            }
        }


        int max = 0;
        int minIndex = 0;
        int count = 0;
        for (int i = 1; i < visited.length; i++) {

            if (visited[i] > max) {
                max = visited[i];
                count = 1;
                minIndex = i;
            } else if (visited[i] == max) {
                count++;
                if (minIndex > i) {
                    minIndex = i;
                }
            }

        }
        System.out.println(minIndex + " " + max + " " + count);
    }
}
