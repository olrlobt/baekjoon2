import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] map = new int[N];

            for (int i = 0; i < N; i++) {
                map[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(map);

            int left= 0;
            int right = map.length-1;
            int max = -1;
            while(left < right){

                int sum = map[left] + map[right];

                if (sum == M) {
                    max = sum;
                    break;
                } else if (sum > M) {
                    right -= 1;
                } else {
                    if(sum > max){
                        max = sum;
                    }
                    left += 1;
                }
            }

            sb.append("#").append(testCase).append(" ").append(max).append("\n");

        }
        System.out.println(sb);
    }
}
