
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Integer>> map ;

    static int[] depth;
    static int[] parents;
    static int N;
    static int one;
    static int two;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int testCase = 0; testCase < T; testCase++) {

            N = sc.nextInt();
            map = new ArrayList<>();
            depth = new int[N + 1];
            parents = new int[N + 1];
            boolean[] root = new boolean[N + 1];
            for (int i = 0; i <= N; i++) {
                map.add(new ArrayList<>());
            }

            for (int i = 0; i < N - 1; i++) {
                int start = sc.nextInt();
                int end = sc.nextInt();

                root[end] = true;
                map.get(start).add(end);
            }
            one = sc.nextInt();
            two = sc.nextInt();

            for (int i = 1; i < N; i++) {
                if (!root[i]) {
                    dfs(i, 1);
                }
            }
            System.out.println(solve(one, two));
        }
    }


    public static void dfs(int root, int dep) {
        depth[root] = dep;

        for (int next : map.get(root)) {
            parents[next] = root;
            dfs(next, dep + 1);
        }
    }

    public static int solve(int one, int two) {

        while (depth[one] != depth[two]) {
            if (depth[one] > depth[two]) {
                one = parents[one];
            } else {
                two = parents[two];
            }
        }

        while (one != two) {
            one = parents[one];
            two = parents[two];
        }

        return one;
    }
}
