import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] map = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        solve(map);
    }

    private static void solve(int[] map) {
        StringBuilder sb = new StringBuilder();
        int[] result = new int[map.length];

        for (int i = 1; i < map.length; i++) {
            int previous = i-1;

            while(map[i] > map[previous]){
                previous = result[previous];
                if(previous == 0){
                    break;
                }
            }

            result[i] = previous;
            sb.append(previous).append(" ");
        }
        System.out.println(sb);
    }
}
