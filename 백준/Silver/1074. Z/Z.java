import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        System.out.println(solve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0));
    }

    public static int solve(int N, int R, int C, int result) { // N = 제곱 // 다음 재귀땐 N -1

        if (N-- == 0) {
            return result;
        }

        if (R < Math.pow(2, N)) { // 위 라인
            if (C >= Math.pow(2, N)) {//좌측
                result += Math.pow(4, N);
                C -= Math.pow(2, N);
            }
        } else {  // 아래 라인
            R -= Math.pow(2, N);
            if (C < Math.pow(2, N)) {//좌측
                result += Math.pow(4, N) * 2;
            } else {
                result += Math.pow(4, N) * 3;
                C -= Math.pow(2, N);
            }
        }
        return solve(N, R, C, result);
    }
}
