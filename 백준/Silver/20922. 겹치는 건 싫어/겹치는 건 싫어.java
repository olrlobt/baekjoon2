import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[100_001];
        int[] map = new int[N];
        int left = 0;
        int right = 0;

        st = new StringTokenizer(br.readLine());
        map[0] = Integer.parseInt(st.nextToken());
        dp[map[0]]++;
        int max = 1;

        while (++right < N) {
            int cur = Integer.parseInt(st.nextToken());
            map[right] = cur;
            dp[cur]++;

            while (dp[cur] > K) {
                dp[map[left]]--;
                left++;
            }

            max = Math.max(max, right - left + 1);
        }
        System.out.println(max);
    }
}
