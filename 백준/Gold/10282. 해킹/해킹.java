
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {


    static ArrayList<ArrayList<Node>> gragh;
    static int hackingCom;
    static int com;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int testCase = 0; testCase < N; testCase++) {

            com = sc.nextInt();
            int dependence = sc.nextInt();
            hackingCom = sc.nextInt();
            gragh = new ArrayList<>();

            for (int i = 0; i <= com; i++) {
                gragh.add(new ArrayList<>());
            }

            for (int i = 0; i < dependence; i++) {
                int com1 = sc.nextInt();
                int com2 = sc.nextInt();
                int seconds = sc.nextInt();

                gragh.get(com2).add(new Node(com1, seconds));
            }
            solve();

        }
    }

    public static void solve() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dp = new int[com + 1];
        Arrays.fill(dp, INF);

        pq.add(new Node(hackingCom, 0));
        dp[hackingCom] = 0;
        int comCount = 0;

        while (!pq.isEmpty()) {

            Node currentCom = pq.poll();
            for (Node deCom : gragh.get(currentCom.index)) {

                if(dp[deCom.index] > deCom.seconds + currentCom.seconds ){
                    dp[deCom.index] = deCom.seconds + currentCom.seconds;
                    pq.add(new Node(deCom.index, deCom.seconds + currentCom.seconds));

                }


            }
        }


        int max = 0;
        for(int i = 0; i <= com ; i ++){
            if(dp[i] != INF){
                comCount ++;
                max = Math.max(dp[i],max);
            }
        }

        System.out.println(comCount + " " + max);

    }

    public static class Node implements Comparable<Node> {

        int index;
        int seconds;

        public Node(int index, int seconds) {
            this.index = index;
            this.seconds = seconds;
        }

        @Override
        public int compareTo(Node o) {
            return seconds - o.seconds;
        }
    }
}
