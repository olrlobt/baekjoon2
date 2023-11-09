import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] trees = new int[N];
        long[] sum = new long[N + 1];
        for (int num = 0; num < N; num++) {
            trees[num] = Integer.parseInt(st.nextToken());

        }

        Arrays.sort(trees);
        for (int num = 0; num < N; num++) {
            sum[num + 1] += sum[num] + trees[num];
        }


        for (int photoZone = 0; photoZone < Q; photoZone++) {
            long P = Long.parseLong(br.readLine());
            int leftTreeNum = solve(trees, P);

            long l = (P * leftTreeNum) - sum[leftTreeNum];
            long l1 = (N - leftTreeNum) * P - (sum[N] - sum[leftTreeNum]);

            sb.append(l - l1).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int[] trees, long photoZone) {

        int left = -1;
        int right = trees.length;
        int mid;

        while (left + 1 < right) {

            mid = (left + right) / 2;


            if (trees[mid] <= photoZone) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return right;
    }

}