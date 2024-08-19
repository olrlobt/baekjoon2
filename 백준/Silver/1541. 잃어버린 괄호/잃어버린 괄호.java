import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = Integer.MAX_VALUE;
        int temp = 0;
        StringTokenizer subtraction = new StringTokenizer(br.readLine(), "-");
        StringTokenizer addition;
        while (subtraction.hasMoreTokens()) {
            temp = 0;

            addition = new StringTokenizer(subtraction.nextToken(), "+");
            while (addition.hasMoreTokens()) {
                temp += Integer.parseInt(addition.nextToken());
            }
            if (sum == Integer.MAX_VALUE) {
                sum = temp;
            } else {
                sum -= temp;
            }
        }
        System.out.println(sum);
    }
}