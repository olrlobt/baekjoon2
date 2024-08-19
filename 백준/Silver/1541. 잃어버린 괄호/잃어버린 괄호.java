import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int sum = 0;
        String[] subtraction = input.split("-");
        sum += sumParts(subtraction[0]);

        for (int i = 1; i < subtraction.length; i++) {
            sum -= sumParts(subtraction[i]);
        }

        System.out.println(sum);
    }

    private static int sumParts(String part) {
        int sum = 0;
        String[] addition = part.split("\\+");
        for (String num : addition) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }
}