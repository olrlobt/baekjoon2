import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		StringBuilder answer = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			int[][] material = new int[N][2];
			for (int num = 0; num < N; num++) {
				st = new StringTokenizer(br.readLine());
				material[num][0] = Integer.parseInt(st.nextToken());
				material[num][1] = Integer.parseInt(st.nextToken());
			}
			answer.append("#").append(testCase).append(" ").append(solve(material, L)).append("\n");
		}
		System.out.println(answer);
	}

	private static int solve(int[][] material, int L) {
		int[] dp = new int[L + 1];
		Arrays.fill(dp, material[0][1], dp.length, material[0][0]);

		for (int i = 1; i < material.length; i++) {
			for (int j = dp.length - 1; j >= material[i][1]; j--) {
				dp[j] = Math.max(dp[j - material[i][1]] + material[i][0], dp[j]);
			}
		}
		return dp[L];
	}
}
