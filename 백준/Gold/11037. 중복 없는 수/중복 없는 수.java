import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while ((input = br.readLine()) != null) {
            int inputInt = Integer.parseInt(input) + 1;
            input = String.valueOf(inputInt);
            int[] numbers = new int[input.length()];

            for (int idx = 0; idx < numbers.length; idx++) {
                numbers[idx] = input.charAt(idx) - '0';
            }


            numbers = solve(numbers, numbers.length);
            if (numbers.length > 9) {
                sb.append(0).append("\n");
                continue;
            }
            Arrays.stream(numbers).forEach(sb::append);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[] solve(int[] input, int len) {

        int flag = 0;
        for (int idx = 0; idx < len; idx++) {
            int cur = input[idx];

            if ((flag & (1 << cur)) == 0 && cur != 0) { // 방문 안 했으면 방문처리
                flag |= (1 << cur);
                continue;
            }
            return fillNext(input, idx, flag, len); // 중복된 2번째 숫자부터 입장
        }
        return input;
    }

    private static int[] fillNext(int[] input, int idx, int flag, int len) {

        while ((flag & (1 << ++input[idx])) != 0 || input[idx] == 10) { // 방문을 했다면 ++
            if (idx == 0) {
                return fillFromZero(new int[len + 1], 0, 0, len + 1);
            } else if (input[idx] == 10) {
                flag &= ~(1 << input[--idx]); // 이 전 자리로 이동
            }
        }
        return fillFromZero(input, flag | (1 << input[idx]), ++idx, len);
    }

    private static int[] fillFromZero(int[] input, int flag, int startIdx, int len) {
        // input[idx] 부터 1234 순으로 채우면 됨
        for (int idx = startIdx, num = 1; num < 10 && idx < len; num++) {
            if ((flag & (1 << num)) != 0) {
                continue;
            }
            flag |= (1 << num);
            input[idx++] = num;
        }
        return input;
    }
}