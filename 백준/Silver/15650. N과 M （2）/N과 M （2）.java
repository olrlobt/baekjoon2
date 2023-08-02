import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
        visited = new boolean[N + 1];

        combination(N, 0, 1);

        System.out.println(sb);
    }

    private static void combination(int N, int count, int cur) {

        if (count == M) {
            for (int i : answer) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = cur; i <= N ; i++) {

            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            answer[count] = i;
            combination(N, count + 1, i);
            visited[i] = false;
        }
    }
}
