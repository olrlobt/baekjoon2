import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] map = new long[N + 1];
        long[] sum = new long[N + 1];
        long max = 0;
        int count = 0;
        Map<Long, Integer> location = new HashMap<>();

        for (int idx = 1; idx <= N; idx++) {
            map[idx] = Integer.parseInt(st.nextToken());
            sum[idx] = map[idx] + sum[idx - 1];
            if(!location.containsKey(map[idx])){
                location.put(map[idx], idx);
            }

            int startIdx = location.get(map[idx]);
            long curSum = sum[idx] - sum[startIdx] + map[idx];

            if (curSum == max) {
                count++;
            } else if (curSum > max) {
                max = curSum;
                count = 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append(max).append(' ').append(count));
    }
}
