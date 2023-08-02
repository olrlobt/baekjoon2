import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static List<Integer> correct = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        combination(N, M);
        System.out.println(sb);
    }

    private static void combination(int N, int M) {

        if (M == 0) {
            for (int i : correct) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {

            if (correct.contains(i)) {
                continue;
            }
            correct.add(i);
            combination(N, M - 1);
            correct.remove(new Integer(i));
        }
    }
}
