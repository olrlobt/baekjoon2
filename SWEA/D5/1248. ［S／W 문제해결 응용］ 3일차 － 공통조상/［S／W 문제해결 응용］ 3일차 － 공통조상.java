
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            int V = sc.nextInt(); // 정점 개수
            int E = sc.nextInt(); // 간선 개수
            int N1 = sc.nextInt();
            int N2 = sc.nextInt();

            ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                tree.add(new ArrayList<>());
            }

            for (int line = 0; line < E; line++) {
                tree.get(sc.nextInt()).add(sc.nextInt());
            }
            int[] result = solve(tree, N1, N2);

            System.out.println("#" + testCase + " " + result[0] + " " + result[1]);
        }
    }

    private static int[] solve(ArrayList<ArrayList<Integer>> tree, int n1, int n2) {
        int[] depth = new int[tree.size()];
        int[] parent = new int[tree.size()];

        depth[1] = 1;
        getDepth(tree, depth, parent, 1);

        int sameParent = getSameParent(depth, parent, n1, n2);
        int count = getCount(tree, sameParent);

        return new int[]{sameParent, count};
    }

    private static int getCount(ArrayList<ArrayList<Integer>> tree, int parent) {
        int count = 1;
        for (int child : tree.get(parent)) {
            count += getCount(tree, child);
        }
        return count;
    }

    private static int getSameParent(int[] depth, int[] parent, int n1, int n2) {

        if (depth[n2] > depth[n1]) {
            n2 = parent[n2];
            depth[n2]--;
        } else if (depth[n2] < depth[n1]) {
            n1 = parent[n1];
            depth[n1]--;
        } else if (n1 != n2) {
            n1 = parent[n1];
            n2 = parent[n2];
        } else {
            return n1;
        }

        return getSameParent(depth, parent, n1, n2);
    }

    private static void getDepth(ArrayList<ArrayList<Integer>> tree, int[] depth, int[] parent, int cur) {

        for (int next : tree.get(cur)) {
            parent[next] = cur;
            depth[next] = depth[cur] + 1;

            getDepth(tree, depth, parent, next);
        }
    }


}
