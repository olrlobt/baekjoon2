import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Node> fast = new ArrayList<>();

		for (int idx = 0; idx < N; idx++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			if (end > M || end - start < dis) {
				continue;
			}
			fast.add(new Node(start, end, dis));
		}

		System.out.println(solve(fast, M));
	}

	private static int solve(List<Node> fast, int m) {

		fast.sort((o1, o2) -> o1.start - o2.start);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.start == m) {
				return cur.distance;
			}

			boolean check = false;
			for (Node fastLoad : fast) {
				if (fastLoad.start > cur.start) {
					pq.offer(new Node(fastLoad.start, 0, cur.distance + fastLoad.start - cur.start));// 다음 지름길까지 더함
					check = true;
				} else if (fastLoad.start == cur.start) { // 지름길
					pq.offer(new Node(fastLoad.end, 0, cur.distance + fastLoad.distance));
					check = true;
				}
			}

			if (!check) {
				pq.offer(new Node(m, 0, cur.distance + m - cur.start));
			}
		}
		return m;
	}

	private static class Node implements Comparable<Node> {
		int start;
		int end;
		int distance;

		public Node(int start, int end, int distance) {
			this.start = start;
			this.end = end;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return distance - o.distance;
		}
	}
}
