import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int min = Integer.MAX_VALUE;
        int len = str.length();
        int aCnt = (int) str.chars().filter(c -> c == 'a').count();


        for (int i = 0; i < len; i++) {
            int bCnt = 0;
            for (int j = i; j < i + aCnt; j++) {
                int idx = j % len;
                if (str.charAt(idx) == 'b') {
                    bCnt++;
                }
            }
            min = Math.min(min, bCnt);
        }

        System.out.println(min);
    }
}