import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int min = Integer.MAX_VALUE;
        int len = str.length();

        boolean[] dp = new boolean[len];
        int aCnt = 0;
        for (int idx = 0; idx < len; idx++) {
            if (str.charAt(idx) == 'a') {
                aCnt++;
                dp[idx] = true;
            }
        }

        int start = 0;
        for (int i = 0; i < aCnt; i++) {
            if (str.charAt(i) == 'b') {
                start ++;
            }
        }

        int maxLen = len + aCnt;
        int idx;
        for (int i = aCnt; i < maxLen; i++) {
            idx = i % len;
            if(!dp[idx]){
                start ++;
            }
            if(!dp[i - aCnt]){
                start --;
            }
            min = Math.min(min, start);
        }

        System.out.println(min);
    }
}