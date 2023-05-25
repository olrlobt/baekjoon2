
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N;
    static int K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        solve(N, K);
    }

    public static void solve(int N, int K) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(K, 1));
        int[] visited = new int[100_001];

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();

            if (visited[curNode.index] != 0) {
                continue;
            }
            visited[curNode.index] = curNode.count;

            if (curNode.index == N) {
                System.out.println((curNode.count - 1));
                int temp = curNode.index;
                for (int i = curNode.count; i > 0; i--) {

                    if (temp * 2 <= 100_000 && visited[temp * 2] == i) {
                        temp = temp * 2;
                    } else if (temp + 1 <= 100_000 && visited[temp + 1] == i) {
                        temp = temp + 1;
                    } else if (temp - 1 >= 0 && visited[temp - 1] == i) {
                        temp = temp - 1;
                    }
                    System.out.print(temp + " ");
                }
                return;
            }

            if (curNode.index % 2 == 0) {
                queue.offer(new Node(curNode.index / 2, curNode.count + 1));
            }

            if (curNode.index > 0) {
                queue.offer(new Node(curNode.index - 1, curNode.count + 1));
            }
            if (curNode.index < 100_000) {

                queue.offer(new Node(curNode.index + 1, curNode.count + 1));
            }

        }
    }

    private static class Node {

        int index;
        int count;

        public Node(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }


}
