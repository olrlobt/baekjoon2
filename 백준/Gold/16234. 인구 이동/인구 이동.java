
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static int N;
    static int L;
    static int R;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static boolean move = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        map = new int[N][N];

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                map[row][column] = sc.nextInt();
            }
        }

        int day = -1;
        while (move) {
            visited = new boolean[N][N];
            move = false;
            day ++;

            for (int row = 0; row < N; row++) {
                for (int column = 0; column < N; column++) {
                    if (visited[row][column]) {
                        continue;
                    }
                    solve(row, column);
                }
            }


        }

        System.out.println(day);
    }

    public static void solve(int y, int x) {
        Queue<Country> pq = new LinkedList<>();
        ArrayList<Country> arr = new ArrayList<>();
        pq.offer(new Country(x, y));

        while (!pq.isEmpty()) {
            Country curCountry = pq.poll();

            if (visited[curCountry.y][curCountry.x]) {
                continue;
            }
            visited[curCountry.y][curCountry.x] = true;
            arr.add(new Country(curCountry.x,curCountry.y));

            for (int i = 0; i < 4; i++) {
                int nextX = curCountry.x + dx[i];
                int nextY = curCountry.y + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
                    continue;
                }

                int gap = Math.abs(map[nextY][nextX] - map[curCountry.y][curCountry.x]);
                if( gap < L || gap > R ){
                    continue;
                }

                pq.offer(new Country(nextX,nextY));
            }
        }

        if(arr.size() == 1){
            return;
        }
        move = true;
        
        int sum = 0 ;
        for(Country country : arr){
            sum += map[country.y][country.x];
        }

        sum /= arr.size();
        for(Country country : arr){
            map[country.y][country.x] = sum;
        }
    }

    private static class Country {
        int x;
        int y;

        public Country(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
