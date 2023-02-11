
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int A;
    static int B;
    static int C;
    static boolean[][][] visited;

    static ArrayList<Integer> answer = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();

        solve();

        Collections.sort(answer);
        for(Integer a : answer){
            System.out.print(a + " ");
        }


    }

    public static void solve() {

        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[A + 1][B + 1][C + 1];
        queue.offer(new int[]{0, 0, C});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int a = cur[0];
            int b = cur[1];
            int c = cur[2];

            if (visited[a][b][c]) {
                continue;
            }
            visited[a][b][c] = true;
            if(a == 0){
                answer.add(c);
            }

            // C >> A
            if (c + a > A) {
                queue.offer(new int[]{A, b, (c + a) - A});
            } else {
                queue.offer(new int[]{a + c, b, 0});
            }

            // B >> A
            if (b + a > A) {
                queue.offer(new int[]{A, b + a - A, c});
            } else {
                queue.offer(new int[]{a + b, 0, c});
            }

            // C >> B
            if (c + b > B) {
                queue.offer(new int[]{a, B, c + b - B});
            } else {
                queue.offer(new int[]{a, b + c, 0});
            }

            // A >> B
            if (a + b > B) {
                queue.offer(new int[]{a + b - B, B, c});
            } else {
                queue.offer(new int[]{0, b + a, c});
            }

            // B >> C
            if (b + c > C) {
                queue.offer(new int[]{a, b + c - C, C});
            } else {
                queue.offer(new int[]{a, 0, c + b});
            }

            // A >> C
            if (a + c > C) {
                queue.offer(new int[]{a + c - C, b, C});
            } else {
                queue.offer(new int[]{0, b, c + a});
            }

        }

    }
}
