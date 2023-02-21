
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static int N;
    static int M;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); //row
        M = sc.nextInt(); //column

        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            String input = sc.next();
            for (int column = 0; column < M; column++) {
                map[row][column] = input.charAt(column) - '0';
            }
        }

        System.out.println(solve());

    }

    public static int solve() {  // 0,0  >> N-1, M-1
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        boolean[][] breakVisited = new boolean[N][M];
        queue.offer(new Node(0, 0, 1, false));

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            if (curNode.x == M - 1 && curNode.y == N - 1) {
                return curNode.count;
            }

            if(curNode.isBreak){
                if(breakVisited[curNode.y][curNode.x]){
                    continue;
                }
                breakVisited[curNode.y][curNode.x] = true;
            }else{
                if(visited[curNode.y][curNode.x]){
                    continue;
                }
                visited[curNode.y][curNode.x] = true;
            }



            for (int i = 0; i < 4; i++) {
                int nextX = curNode.x + dx[i];
                int nextY = curNode.y + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
                    continue;
                }

                if (map[nextY][nextX] == 1) {
                    if(curNode.isBreak){
                        continue;
                    }
                    queue.offer(new Node(nextX, nextY, curNode.count + 1 , true));
                    continue;
                }

                queue.offer(new Node(nextX, nextY, curNode.count + 1 , curNode.isBreak));
            }
        }
        return -1;
    }

    private static class Node implements Comparable<Node>{
        int x;
        int y;
        int count;
        boolean isBreak;

        public Node(int x, int y, int count, boolean isBreak) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.isBreak = isBreak;
        }

        @Override
        public int compareTo(Node o) {
            return count - o.count;
        }
    }


}
