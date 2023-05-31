import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        solve();
    }

    public static void solve(){

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1, 0, 0));
        boolean[][] visited = new boolean[1001][1001];
        visited[1][0] = true;

        while (!queue.isEmpty()){

            Node curNode = queue.poll();


            if(curNode.index == N){
                System.out.println(curNode.count);
                return;
            }

            if(curNode.clip != curNode.index){ // 1 클립 저장
                queue.offer(new Node(curNode.index ,curNode.count + 1, curNode.index));
            }
            if(curNode.clip != 0 && curNode.index + curNode.clip < 1001 && !visited[curNode.index + curNode.clip][curNode.clip]){ // 2 붙여넣기
                queue.offer(new Node(curNode.index + curNode.clip ,curNode.count + 1, curNode.clip));
                visited[curNode.index + curNode.clip][curNode.clip] = true;
            }
            if(curNode.index > 1 && !visited[curNode.index - 1][curNode.clip]){ // 3
                queue.offer(new Node(curNode.index - 1, curNode.count + 1, curNode.clip));
                visited[curNode.index - 1][curNode.clip] = true;
            }

        }

    }

    private static class Node {
        int index;
        int count;
        int clip;

        public Node(int index, int count, int clip) {
            this.index = index;
            this.count = count;
            this.clip = clip;
        }
    }
}
