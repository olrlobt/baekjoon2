import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static ArrayList<ArrayList<Integer>> map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken()); //start

        map = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            map.get(start).add(end);
            map.get(end).add(start);
        }

        visited = new boolean[map.size()];
        DFS(V);
        visited = new boolean[map.size()];
        BFS(V);

        System.out.println(sb);
    }

    private static void BFS(int V) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(V);
        sb.append("\n");
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (visited[cur]) {
                continue;
            }

            visited[cur] = true;
            sb.append(cur).append(" ");
            Collections.sort(map.get(cur));
            for(int next : map.get(cur)){
                queue.offer(next);
            }
        }

    }

    private static void DFS(int V) {

        if(visited[V]){
            return;
        }
        visited[V] = true;
        sb.append(V).append(" ");
        Collections.sort(map.get(V));
        for(int next : map.get(V)){
            DFS(next);
        }
    }
}
