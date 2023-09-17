import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        
        int temp = 0;
        int[] map = new int[3];
        int[] maxDp = new int[3];
        int[] minDp = new int[3];

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int column = 0; column < 3; column++) {
                map[column] = Integer.parseInt(st.nextToken());
            }

            int max = Math.max(maxDp[1], maxDp[2]);
            temp = map[0] + Math.max(maxDp[0], maxDp[1]);
            maxDp[1] = map[1] + Math.max(maxDp[0], max);
            maxDp[2] = map[2] + max;

            maxDp[0] = temp;

            int min = Math.min(minDp[1], minDp[2]);
            temp = map[0] + Math.min(minDp[0], minDp[1]);
            minDp[1] = map[1] + Math.min(minDp[0], min);
            minDp[2] = map[2] + min;

            minDp[0] = temp;
        }

        sb.append(Math.max(maxDp[0], Math.max(maxDp[2], maxDp[1]))).append(" ").append(Math.min(minDp[0],
                Math.min(minDp[2], minDp[1])));
        System.out.println(sb);
    }
}
