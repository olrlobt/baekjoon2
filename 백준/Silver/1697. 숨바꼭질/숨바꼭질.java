import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(K <= N){
            System.out.println(N - K);
        }else{
            System.out.println(solve(N,K));
        }

    }

    private static int solve(int n, int k) {
        boolean [] dp = new boolean[300_000];

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(n,0));
        while (!queue.isEmpty()){

            Node curNode = queue.poll();

            if(dp[curNode.index]){
                continue;
            }
            dp[curNode.index] = true;


            if(curNode.index == k){
                return curNode.count;
            }

            if(curNode.index > 0){
                queue.offer(new Node(curNode.index-1, curNode.count + 1));
            }
            if(curNode.index < 100_000){
                queue.offer(new Node(curNode.index+1, curNode.count + 1));
            }
            if(curNode.index*2 <= 200_000){
                queue.offer(new Node(curNode.index*2, curNode.count + 1));
            }
        }

        return n;
    }


    private static class Node{
        int index;
        int count;

        public Node(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }
}
