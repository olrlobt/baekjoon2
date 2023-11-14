import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer>[] graph;
    static int n;
    static int m;
    private static int s;
    private static boolean[] fans;
    static StringBuilder sb = new StringBuilder();
    static boolean meetFan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());//정점의 개수 N
        m = Integer.parseInt(st.nextToken());//간선의 개수 M

        graph = new List[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }//초기화

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
        }
        s = Integer.parseInt(br.readLine());
        fans = new boolean[n+1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < s; i++) {
            fans[Integer.parseInt(st.nextToken())] = true;
        } //입력 완료

        meetFan = fans[1]; // 1번이 팬이라면 시작할 필요 없음
        if(!meetFan) {      // 팬을 안 만났다면 시작
            meetFan = true;
            dfs(1);
        }

        if(meetFan) {
            sb.append("Yes");
        }else {
            sb.append("yes");
        }
        System.out.println(sb.toString());
    }//end main


    static void dfs(int current) {
        //기저조건
        if(graph[current].size() == 0) {
            meetFan = false; // 팬을 안 만났다
            return;
        }

        for (int next : graph[current]) {
            if(!fans[next]){
                dfs(next);
            }
        }

    }//end bfs
}//end class
