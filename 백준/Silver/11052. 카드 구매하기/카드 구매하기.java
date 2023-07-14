import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] cards = new int[N + 1];
        for (int number = 1; number <= N; number++) {
            cards[number] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(cards));
    }

    public static int solve(int[] cards) {
        int[][] dp = new int[cards.length][cards.length];

        for (int card = 1; card < cards.length; card++) {
            for (int number = 1; number < cards.length; number++) {
                if (card == 1) {
                    dp[card][number] = cards[card] * (number);
                    continue;
                }
                if (number >= card) {
                    dp[card][number] = Math.max(dp[card][number - card] + cards[card], dp[card - 1][number]);
                } else {
                    dp[card][number] = dp[card - 1][number];
                }
            }
        }

        return dp[cards.length - 1][cards.length - 1];
    }
}
