
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s, " ");
        int[] arr = new int[N];
        for(int i = 0; i < N; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int res = Integer.MAX_VALUE;
        int start = 0;
        int end = arr.length - 1;
        int answer1 = 0;
        int answer2 = 0;
        while(start < end) {
            int temp = Math.abs(arr[start] + arr[end]);
            if(res > temp) {
                answer1 = arr[start];
                answer2 = arr[end];
                res = temp;
                if(temp == 0) break;
            }
            if(arr[start] + arr[end] > 0)
                end--;
            else
                start++;
        }
        System.out.println(answer1 + " " + answer2);
    }
}
