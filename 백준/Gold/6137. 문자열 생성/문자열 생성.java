import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static int length = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringBuilder input = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int num = 0; num < N; num++) {
            input.append(br.readLine());
        }

        solve(input, sb);
        System.out.println(sb);
    }

    private static void solve(StringBuilder input, StringBuilder sb) {

        int left = 0;
        int right = input.length() - 1;

        StringBuilder bufferRight = new StringBuilder();
        StringBuilder bufferLeft = new StringBuilder();

        while (left < right) {
            if (input.charAt(left) == input.charAt(right)) {

                int tempLeft = left;
                int tempRight = right;
                int count = 0;
                boolean isAdd = true;
                char curString = input.charAt(left);

                do {
                    if (isAdd) {
                        if(curString != input.charAt(tempLeft)){
                            isAdd = false;
                            continue;
                        }
                        bufferLeft.append(input.charAt(tempLeft));
                        bufferRight.append(input.charAt(tempRight));
                        ++length;
                        if (length % 80 == 0) {
                            bufferLeft.append("\n");
                            bufferRight.append("\n");
                        }
                        ++count;
                    }
                } while (++tempLeft + 1 < --tempRight && input.charAt(tempLeft) == input.charAt(tempRight));

                if (input.charAt(tempLeft) < input.charAt(tempRight)) {
                    sb.append(bufferLeft);
                    left += count;
                } else {
                    sb.append(bufferRight);
                    right -= count;
                }
                bufferLeft.setLength(0);
                bufferRight.setLength(0);
                continue;
            } else if (input.charAt(left) < input.charAt(right)) {
                sb.append(input.charAt(left++));
            } else {
                sb.append(input.charAt(right--));
            }
            if (++length % 80 == 0) {
                sb.append("\n");
            }
        }
        sb.append(input.charAt(left));
    }
}
