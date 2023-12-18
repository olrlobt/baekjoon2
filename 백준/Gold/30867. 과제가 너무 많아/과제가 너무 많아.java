import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int L = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        char[] str = br.readLine().toCharArray();

        solve(str, N);

        for (char c : str) {
            sb.append(c);
        }
        System.out.println(sb);
    }

    private static void solve(char[] str, int n) {

        int count = 0;

        for (int idx = 0; idx < str.length; idx++) {
            if (str[idx] == 'w') {
                count++;
                if (count > n) {
                    count = n;
                }
            }else if (str[idx] == 'h'){
                swap(str, idx, count);
            }else{
                count = 0;
            }

        }
    }

    private static void swap(char[] str, int idx, int count) {
        char temp = str[idx];
        str[idx] = str[idx - count];
        str[idx - count] = temp;
    }


}
