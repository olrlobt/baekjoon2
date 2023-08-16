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
        
        if (N == 0) {
            return result;
        }

        if (R < Math.pow(2, N - 1)) { // 위 라인
            if (C >= Math.pow(2, N - 1)) {//좌측
                result += Math.pow(4, N-1);
                C -= Math.pow(2, N - 1);
            }
        } else {  // 아래 라인
            R -= Math.pow(2, N - 1);
            if (C < Math.pow(2, N - 1)) {//좌측
                result += Math.pow(4, N-1) * 2;
            } else {
                result += Math.pow(4, N-1) * 3;
                C -= Math.pow(2, N - 1);
            }
        }
        return solve(N - 1, R, C, result);
    }
}
