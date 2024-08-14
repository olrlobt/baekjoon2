import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long bit = 0;
        String input = br.readLine();

        if(N % 2 != 0){
            bit |= 1L << input.charAt(N / 2) - 'a';
        }

        for (int idx = 0; idx < N; idx++) {
            bit ^= (1L << input.charAt(idx) - 'a');
        }

        System.out.println(bit == 0 ? "Yes" : "No");
    }
}
