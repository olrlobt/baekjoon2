import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 2^N x 2^N
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(solve(N, R, C, 0));
    }

    public static int solve(int N, int R, int C, int result) { // N = 제곱 // 다음 재귀땐 N -1

        if (N-- == 0) {
            return result;
        }

        int len = (int) Math.pow(2, N);
        if (R < len) { // 위 라인
            if (C >= len) {// 2사분면
                result += len * len;
                C -= len;
            }
        } else {  // 아래 라인
            R -= len;
            if (C < len) {// 3사분면
                result += len * len * 2;
            } else {    // 4사분면
                result += len * len * 3;
                C -= len;
            }
        }
        return solve(N, R, C, result);
    }
}
