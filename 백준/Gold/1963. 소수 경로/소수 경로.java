
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static boolean[] check = new boolean[10000];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 2; i < 5000; i++) {
            int num = 2;
            if (check[i]) {
                continue;
            }
            while (num * i < 10000) {
                check[num++ * i] = true;
            }
        }

        for (int testCase = 0; testCase < T; testCase++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            System.out.println(solve(start, end));
        }
    }

    public static int solve(int start, int end) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start, 1));
        int[] dp = new int[10000];

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();


            if (dp[curNode.index] != 0) {
                continue;
            }
            dp[curNode.index] = curNode.count;

            for (int i = 1; i <= 9; i++) {
                int mod = curNode.index % 1000;
                int nextIndex = i * 1000 + mod;
                if (dp[nextIndex] != 0 || check[nextIndex]){
                    continue;
                }
                queue.offer(new Node(nextIndex, curNode.count + 1));
            }
            for (int i = 0; i <= 9; i++) {
                int thousand = curNode.index / 1000;
                int mod = curNode.index % 100;
                int nextIndex = thousand * 1000 + i * 100 + mod;
                if (dp[nextIndex] != 0 || check[nextIndex]){
                    continue;
                }
                queue.offer(new Node(nextIndex, curNode.count + 1));
            }
            for (int i = 0; i <= 9; i++) {
                int hundred = curNode.index / 100;
                int mod = curNode.index % 10;
                int nextIndex = hundred * 100 + i * 10 + mod;
                if (dp[nextIndex] != 0 || check[nextIndex]){
                    continue;
                }
                queue.offer(new Node(nextIndex, curNode.count + 1));
            }

            for(int i = 1 ; i <= 9; i ++){
                int ten = curNode.index / 10;
                int nextIndex = ten * 10 + i;
                if (dp[nextIndex] != 0 || check[nextIndex]){
                    continue;
                }
                queue.offer(new Node(nextIndex, curNode.count + 1));
            }

        }

        return dp[end]-1;
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
