
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N;
    static int M;
    static Character[][] map;
    static ArrayList<int[]> coins;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new Character[N][M];
        coins = new ArrayList<>();

        for (int row = 0; row < N; row++) {
            String input = sc.next();
            for (int column = 0; column < M; column++) {
                map[row][column] = input.charAt(column);
                if (map[row][column] == 'o') {
                    coins.add(new int[]{row, column});
                }
            }
        }

        System.out.println(solve());
    }

    public static int solve() {

        Queue<Coin> queue = new LinkedList<>();
        queue.offer(new Coin(coins.get(0)[0], coins.get(0)[1], coins.get(1)[0], coins.get(1)[1], 0));
        boolean [][][][] visited = new boolean[N][M][N][M];


        while (!queue.isEmpty()) {

            Coin curCoins = queue.poll();

            if (curCoins.count >= 10) {
                return -1;
            }

            if (visited[curCoins.row1][curCoins.column1][curCoins.row2][curCoins.column2]) {
                continue;
            }
            visited[curCoins.row1][curCoins.column1][curCoins.row2][curCoins.column2] = true;


            for (int i = 0; i < 4; i++) {
                int coin1Row = curCoins.row1 + dy[i];
                int coin1Column = curCoins.column1 + dx[i];
                int coin2Row = curCoins.row2 + dy[i];
                int coin2Column = curCoins.column2 + dx[i];

                boolean drop1 = isDrop(coin1Row, coin1Column);
                boolean drop2 = isDrop(coin2Row, coin2Column);

                if (drop1 || drop2) {
                    if (drop1 && drop2) {
                        continue;
                    }
                    return curCoins.count + 1;
                }

                if(map[coin1Row][coin1Column] == '#' && map[coin2Row][coin2Column] == '#'){
                    continue;
                }
                if(map[coin1Row][coin1Column] == '#'){
                    queue.offer(new Coin(curCoins.row1, curCoins.column1, coin2Row, coin2Column, curCoins.count + 1));
                    continue;
                }else if(map[coin2Row][coin2Column] == '#'){
                    queue.offer(new Coin(coin1Row, coin1Column, curCoins.row2, curCoins.column2, curCoins.count + 1));
                    continue;
                }

                queue.offer(new Coin(coin1Row, coin1Column, coin2Row, coin2Column, curCoins.count + 1));
            }
        }

        return -1;
    }

    private static boolean isDrop(int row, int column) {
        if (row < 0 || column < 0 || row >= N || column >= M) {
            return true;
        }
        return false;
    }

    private static class Coin {

        int row1;
        int column1;
        int row2;
        int column2;
        int count;

        public Coin(int row1, int column1, int row2, int column2, int count) {
            this.row1 = row1;
            this.column1 = column1;
            this.row2 = row2;
            this.column2 = column2;
            this.count = count;
        }
    }
}
