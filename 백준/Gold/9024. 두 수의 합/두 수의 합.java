import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] map = new int[N];
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}
			sb.append(solve(map, M)).append("\n");
		}
		System.out.println(sb);
	}

	private static int solve(int[] map, int m) {
		Arrays.sort(map);

		int left = 0;
		int right = map.length - 1;
		int count = 0;
		int maxDiff = Integer.MAX_VALUE ;

		while (left < right) {

			int diff = (map[left] + map[right]) - m;
			int absDiff = Math.abs(diff);

			if (absDiff == maxDiff) {
				count ++;
			}else if(absDiff < maxDiff){
				maxDiff = absDiff;
				count = 1;
			}

			if (diff > 0) {
				right --;
			}else {
				left ++;
			}
		}

		return count;
	}
}
