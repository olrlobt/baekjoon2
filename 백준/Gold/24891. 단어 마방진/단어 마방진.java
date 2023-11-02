import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static String[] strings;
    static boolean[] visited;
    static int L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("NONE");

        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        strings = new String[N];
        visited = new boolean[N];
        for (int num = 0; num < N; num++) {
            strings[num] = br.readLine();
        }
        Arrays.sort(strings);
        String[] mabangjin = new String[L];

        for (int idx = 0; idx < strings.length; idx++) {
            mabangjin[0] = strings[idx];
            visited[idx] = true;
            if(solve(mabangjin, 0)){
                sb = new StringBuilder();
                for (String s : mabangjin) {
                    sb.append(s).append("\n");
                }
                break;
            }
            visited[idx] = false;
        }

        System.out.println(sb);
    }

    private static boolean solve(String[] mabangjin, int curIdx) {

        if (curIdx >= L - 1) {
            return true;
        }

        int nextIdx = curIdx + 1;

        for (int idx = 0; idx < strings.length; idx++) {

            if (visited[idx] || !isOK(mabangjin, nextIdx, strings[idx])) {
                continue;
            }
            mabangjin[nextIdx] = strings[idx];
            visited[idx] = true;
            if (solve(mabangjin, nextIdx)) {
                return true;
            }
            visited[idx] = false;
        }
        return false;
    }

    private static boolean isOK(String[] mabangjin, int nextIdx, String target) {

        for (int row = 0; row < nextIdx; row++) {
            if (target.charAt(row) != mabangjin[row].charAt(nextIdx)) {
                return false;
            }
        }
        return true;
    }
}