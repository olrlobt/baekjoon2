import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[26];
        String input = br.readLine();

        int range = N / 2;
        for (int idx = 0; idx < range; idx++) {
            arr[input.charAt(idx) - 'a']++;
        }
        range += N % 2;
        for (int idx = range; idx < N; idx++) {
            arr[input.charAt(idx) - 'a']++;
        }

        long count = Arrays.stream(arr).filter(value -> value % 2 != 0).count();
        System.out.println(count == 0 ? "Yes" : "No");
    }

}
