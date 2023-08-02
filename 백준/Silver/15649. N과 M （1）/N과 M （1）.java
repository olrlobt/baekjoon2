import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        combination(N, M , list);
        System.out.println(sb);
    }

    private static void combination(int N, int M , List<Integer> list) {

        if (M == 0) {
            for (int i : list) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {

            if (list.contains(i) ) {
                continue;
            }
            list.add(i);
            combination(N,M-1, new ArrayList<>(list));
            list.remove(new Integer(i));
        }
    }
}
