import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    private static final long MESSI_NUM = 5;
    private static final long SPACE_NUM = 1;
    private static final long GIMOSSI_NUM = 7;
    private static final String MG = " Messi Gimossi";
    private static ArrayList<Long> dp = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int idx = getLeng(N);
        char solve = solve(N, idx);
        System.out.println(solve == ' ' ? "Messi Messi Gimossi" : solve);
    }

    private static int getLeng(int n) {
        dp.add(0L);
        dp.add(MESSI_NUM);
        dp.add(MESSI_NUM + SPACE_NUM + GIMOSSI_NUM);

        int idx = 2;
        while (n > dp.get(++idx - 1)) {
            dp.add(dp.get(idx - 2) + SPACE_NUM + dp.get(idx - 1));
        }
        return idx-1;
    }

    private static char solve(int N, int idx) {

        if (N <= 13) {
            return MG.charAt(N);
        }

        if (N < dp.get(idx - 1)) { // 왼쪽
            return solve(N, idx - 1);
        } else if (N > dp.get(idx - 1) + SPACE_NUM) { // 오른쪽
            return solve((int) (N - dp.get(idx-1) - SPACE_NUM), idx - 2);
        } else{ // 공백
            return ' ';
        }
    }
}
