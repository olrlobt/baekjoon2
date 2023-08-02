import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int[] answer;
    static boolean[] visited;

    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
        answer = new int[M];
        visited = new boolean[N+1];

        combination(N, 0);
        System.out.println(sb);
    }

    private static void combination(int N, int m) {

        if (m == M) {
            for (int i : answer) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {

            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            answer[m] = i;
            combination(N, m + 1);
            visited[i] = false;
        }
    }
}
