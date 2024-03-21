import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] map = new int[N];
		int[] sumMap = new int[N + 1];

		map[0] = Integer.parseInt(st.nextToken());
		for (int idx = 1; idx < N; idx++) {
			map[idx] = Integer.parseInt(st.nextToken());
			sumMap[idx] += map[idx - 1];
			sumMap[idx] += sumMap[idx - 1];
		}
		sumMap[N] += map[N - 1];
		sumMap[N] += sumMap[N - 1];

		int M = Integer.parseInt(br.readLine());
		for (int idx = 0; idx < M; idx++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken());
			sb.append(sumMap[end] - sumMap[start]).append("\n");
		}
		System.out.println(sb);
	}
}
