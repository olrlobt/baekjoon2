
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Node> map = new HashMap<>();
        Set<Node> list = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (str.length() < M) {
                continue;
            }

            Node node = map.getOrDefault(str, new Node(str, 0));
            node.num++;
            map.put(str, node);
            list.add(node);
        }
        List<Node> sortList = new ArrayList<>(list);
        Collections.sort(sortList);

        StringBuilder sb = new StringBuilder();
        for (Node node : sortList) {
            sb.append(node.str).append('\n');
        }
        System.out.println(sb);

    }

    private static class Node implements Comparable<Node> {
        String str;
        int num;

        public Node(String str, int num) {
            this.str = str;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            if (this.num == o.num) {
                if (this.str.length() == o.str.length()) {
                    return String.CASE_INSENSITIVE_ORDER.compare(this.str, o.str);
                }
                return o.str.length() - this.str.length();
            }
            return o.num - this.num;
        }
    }
}
