import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 버퍼리더
        StringBuilder sb = new StringBuilder();  // 출력을 위한 스트링빌더

        int T = Integer.parseInt(br.readLine()); // T를 입력 받는다.

        int[] result = solve(T); // 결과를 함수로 반환받아 저장한다.
        for(int re : result){
            sb.append(re).append(" "); // 결과를 스트링 빌더에 저장한다.
        }
        System.out.println(sb); // 결과를 출력한다.
    }

    private static int[] solve(int T) {
        if(T%10 != 0){
            return new int[]{-1}; // 입력 받은 T초가 최소 단위인 10으로 나누어 떨어지지 않으면 -1을 반환한다.
        }

        int[] times = {300, 60, 10}; // A, B , C  버튼
        int[] result = {0,0,0}; // 결과가 저장될 배열

        for (int time = 0; time < times.length; time++) {
            result[time] = T / times[time];  // 현재 시간으로 나누어 최소 버튼 클릭 횟수를 구한다.
            T %=times[time]; // 다음 시간으로 나누기 위해 시간을 나누어 준다.
        }
        return result; //결과 반환
    }
}