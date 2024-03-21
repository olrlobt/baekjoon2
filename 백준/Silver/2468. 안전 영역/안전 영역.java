import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static final int[] dx = {1, 0, -1, 0};
	static final int[] dy = {0, -1, 0, 1};

	static boolean[][] visited;

	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		Set<Integer> task = new HashSet<>();
		Map<Integer, List<int[]>> state = new HashMap<>();
		visited = new boolean[N][N];
		map = new int[N][N];

		for (int row = 0; row < N; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int column = 0; column < N; column++) {
				map[row][column] = Integer.parseInt(st.nextToken());
				task.add(map[row][column]);
				List<int[]> orDefault = state.getOrDefault(map[row][column], new ArrayList());
				orDefault.add(new int[] {row, column});
				state.put(map[row][column], orDefault);
			}
		}
		solve(map, state);
		System.out.println(sb);
	}

	private static void solve(int[][] map, Map<Integer, List<int[]>> state) {
		Set<Integer> integers = state.keySet();

		Iterator<Integer> iterator = integers.iterator();
		int max = 1;
		while (iterator.hasNext()) {
			int cur = iterator.next();

			List<int[]> ints = state.get(cur);
			for (int[] anInt : ints) {
				visited[anInt[0]][anInt[1]] = true;
			}

			boolean[][] visi = new boolean[map.length][map.length];
			for (int i = 0; i < map.length; i++) {
				visi[i] = Arrays.copyOf(visited[i], map.length);
			}

			int count = 0;
			for (int row = 0; row < map.length; row++) {
				for (int column = 0; column < map.length; column++) {
					if (map[row][column] <= cur || visi[row][column]) {
						continue;
					}
					bfs(row, column, visi);
					count++;
				}
			}
			max = Math.max(max, count);
		}
		System.out.println(max);
	}

	private static void bfs(int row, int column, boolean[][] visi) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {row, column});

		while (!queue.isEmpty()) {
			int[] poll = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nextRow = poll[0] + dy[i];
				int nextColumn = poll[1] + dx[i];

				if (nextRow >= map.length || nextColumn >= map.length || nextRow < 0 || nextColumn < 0
					|| visi[nextRow][nextColumn]) {
					continue;
				}
				visi[nextRow][nextColumn] = true;
				queue.offer(new int[] {nextRow, nextColumn});
			}
		}
	}

}
