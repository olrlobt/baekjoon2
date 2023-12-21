import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] number = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int num = 0; num < N; num++) {
            number[num] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(number));
    }

    private static int solve(int[] number) {

        int[] memo = new int[number.length];
        memo[0] = number[0];
        int maxIdx = 1;

        for (int i = 1; i < number.length; i++) {
            if (number[i] > memo[maxIdx - 1]) {
                memo[maxIdx++] = number[i];
            }else{
                binarySearch(memo, number[i] ,maxIdx);
            }
        }
        return maxIdx;
    }

    private static void binarySearch(int[] memo, int num, int maxIdx) {
        int left = -1;
        int right = maxIdx;
        int mid;

        while (left + 1 < right) {
            mid = (left + right) / 2;

            if(memo[mid] >= num){
                right = mid;
            }else{
                left = mid;
            }
        }
        memo[right] = num;
    }

}
