import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
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
        int last = arr.length - k + 1;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            if (isSorted(curNode.arr)) {
                return curNode.count;
            }
            curNode.count++;
            for (int idx = 0; idx < last; idx++) {
                int[] nextArr = swapping(curNode.arr, idx);
                if (!visited.add(Arrays.hashCode(nextArr))) {
                    continue;
                }
                que.offer(new Node(nextArr, curNode.count));
            }
        }
        return -1;
    }

    private static int[] swapping(int[] arr, int startIdx) {
        int lastIdx = startIdx + k - 1;
        int[] newArr = new int[arr.length];
        System.arraycopy(arr, 0, newArr, 0, arr.length);

        for (int idx = 0; idx < k; idx++) {
            newArr[startIdx + idx] = arr[lastIdx - idx];
        }
        return newArr;
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
