import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int k;
    static Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int idx = 0; idx < n; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve(arr));
    }

    private static int solve(int[] arr) {
        Queue<Node> que = new ArrayDeque<>();
        que.add(new Node(arr, 0));
        visited.add(Arrays.hashCode(arr));

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            if (isSorted(curNode.arr)) {
                return curNode.count;
            }
            for (int idx = 0; idx < arr.length - k + 1; idx++) {
                int[] nextArr = swapping(curNode.arr, idx);
                if (!visited.add(Arrays.hashCode(nextArr))) {
                    continue;
                }
                que.offer(new Node(nextArr, curNode.count + 1));
            }
        }
        return -1;
    }

    private static int[] swapping(int[] arr, int startIdx) {
        int lastIdx = startIdx + k - 1;
        arr = Arrays.copyOf(arr, arr.length);
        for (int idx = 0; idx < k / 2; idx++) {
            int temp = arr[startIdx + idx];
            arr[startIdx + idx] = arr[lastIdx - idx];
            arr[lastIdx - idx] = temp;
        }
        return arr;
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private static class Node {
        int[] arr;
        int count;

        public Node(int[] arr, int count) {
            this.arr = arr;
            this.count = count;
        }
    }
}
