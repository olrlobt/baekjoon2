import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static String[] strings;
    static int flag;
    static int L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("NONE");

        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        strings = new String[N];
        for (int num = 0; num < N; num++) {
            strings[num] = br.readLine();
        }
        Arrays.sort(strings);
        String[] mabangjin = new String[L];

        for (int idx = 0; idx < strings.length; idx++) {
            mabangjin[0] = strings[idx];
            flag |= (1 << idx);
            if(solve(mabangjin, 1)){
                sb = new StringBuilder();
                for (String s : mabangjin) {
                    sb.append(s).append("\n");
                }
                break;
            }
            flag &= ~(1 << idx);
        }

        System.out.println(sb);
    }

    private static boolean solve(String[] mabangjin, int curIdx) {
        if (curIdx >= L ) {
            return true;
        }

        for (int idx = 0; idx < strings.length; idx++) {

            if ((flag & ( 1 << idx)) != 0) {
                continue;
            }
            mabangjin[curIdx] = strings[idx];
            if(!isOK(mabangjin, curIdx)){
                continue;
            }

            flag |= (1 << idx);
            if (solve(mabangjin, curIdx + 1)) {
                return true;
            }
            flag &= ~(1 << idx);
        }
        return false;
    }

    private static boolean isOK(String[] mabangjin, int nextIdx) {

        for (int row = 0; row < nextIdx ; row++) {
            if (mabangjin[nextIdx].charAt(row) != mabangjin[row].charAt(nextIdx)) {
                return false;
            }
        }
        return true;
    }
}