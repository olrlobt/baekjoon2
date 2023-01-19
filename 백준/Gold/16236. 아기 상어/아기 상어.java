import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N;
    static int[][] map;
    static boolean[][] target;
    static int result = 0;
    static int targetCount = 0;
    static int[] shark;
    static int size = 2;
    static int eat = 0;
    static int[] dx = {0, -1, 1, 0}; // 상 좌 우 하
    static int[] dy = {-1, 0, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        target = new boolean[N][N];

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                int input = sc.nextInt();
                map[row][column] = input;

                if (input == 9) {
                    map[row][column] = 0;
                    shark = new int[]{row, column};
                } else if (input == 1) {
                    target[row][column] = true;
                    targetCount++;
                }
            }
        }
        solve();
        System.out.println(result);
    }


    public static void solve() {
        while (targetCount != 0) {

            int[] fish = distance();
            if(fish == null){
                break;
            }

            move(fish);
        }
    }

    public static void move(int[] fish) {

        map[fish[0]][fish[1]] = 0;
        target[fish[0]][fish[1]] = false;
        targetCount--;
        eat++;

        if (eat == size) {
            eat = 0;
            size++;
            targetAdd();
        }
        shark = fish;
    }

    private static void targetAdd() {
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                if (map[row][column] == size - 1) {
                    target[row][column] = true;
                    targetCount++;
                }
            }
        }

    }


    public static int[] distance() {

        Queue<int[]> visited = new LinkedList<>();
        int[][] distanceMap = new int[N][N];

        List<int[]> targetList = new ArrayList<>();

        visited.add(shark);

        while (!visited.isEmpty()) {

            int[] position = visited.poll();
            int row = position[0];
            int column = position[1];

            for (int i = 0; i < 4; i++) {
                int nextRow = row + dy[i];
                int nextColumn = column + dx[i];

                if (nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= N) {
                    continue;
                }

                if (map[nextRow][nextColumn] <= size && distanceMap[nextRow][nextColumn] == 0) {

                    if (target[nextRow][nextColumn]) {
                        targetList.add(new int[]{nextRow, nextColumn});
                    }

                    distanceMap[nextRow][nextColumn] = distanceMap[row][column] + 1;

                    if (targetList.isEmpty()) {
                        visited.offer(new int[]{nextRow, nextColumn});
                    }
                }
            }
        }


        if(targetList.isEmpty()){
            return null;
        }
        Collections.sort(targetList, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (distanceMap[o1[0]][o1[1]] == distanceMap[o2[0]][o2[1]]) {
                    if (o1[0] == o2[0]) {
                        return o1[1] - o2[1];
                    }
                    return o1[0] - o2[0];
                } else {

                    return distanceMap[o1[0]][o1[1]] - distanceMap[o2[0]][o2[1]];
                }
            }
        });


        result += distanceMap[targetList.get(0)[0]][targetList.get(0)[1]];
        return targetList.get(0);
    }


}
