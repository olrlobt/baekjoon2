
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static int[] maxDp;
    static int[] minDp;
    static int[] temp;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][3];
        temp = new int[3];
        maxDp = new int[3];
        minDp = new int[3];

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < 3; column++) {
                map[row][column] = sc.nextInt();
            }

            temp[0] = map[row][0] + Math.max(maxDp[0], maxDp[1]);
            temp[1] = map[row][1] + Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]));
            temp[2] = map[row][2] + Math.max(maxDp[1], maxDp[2]);

            maxDp[0] = temp[0];
            maxDp[1] = temp[1];
            maxDp[2] = temp[2];

            temp[0] = map[row][0] + Math.min(minDp[0], minDp[1]);
            temp[1] = map[row][1] + Math.min(minDp[0], Math.min(minDp[1], minDp[2]));
            temp[2] = map[row][2] + Math.min(minDp[1], minDp[2]);

            minDp[0] = temp[0];
            minDp[1] = temp[1];
            minDp[2] = temp[2];
        }

        System.out.println(Math.max(maxDp[0], Math.max(maxDp[2], maxDp[1])) + " " + Math.min(minDp[0], Math.min(minDp[2], minDp[1])));



    }
}
