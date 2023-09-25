import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            int[] map = new int[N];
            st = new StringTokenizer(br.readLine());
            int max = 0;

            for (int num = 0; num < N; num++) {
                map[num] = Integer.parseInt(st.nextToken());

                if (map[num] > max) {
                    max = map[num];
                }
            }

            sb.append(solve(map, S, max)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int[] map, int s, int max) { // s-1 만큼

        Arrays.sort(map);
        int[] diff = new int[map.length - 1];
        IntStream.range(1, map.length).forEach(idx -> diff[idx - 1] = map[idx] - map[idx - 1]);

        int high = max;
        int low = 0;
        int mid = 0;

        while (low + 1 < high) {

            mid = (high + low) / 2;

            if (count(mid, diff) >= s - 1) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private static int count(int mid, int[] diff) {

        int sum = 0;
        int count = 0;

        for (int dif : diff) {
            if ((dif+sum) / mid > 0){
                count ++;
                sum = 0;
                continue;
            }
            sum += dif;
        }

        return count;
    }
}
