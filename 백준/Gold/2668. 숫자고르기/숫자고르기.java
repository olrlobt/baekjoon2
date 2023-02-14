
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[][] map;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        map = new int[2][N + 1];
        int[] possible = new int[N];

        for (int num = 1; num <= N; num++) {
            map[1][num] = sc.nextInt();
            possible[num - 1] = map[1][num];
        }

        possible = Arrays.stream(possible).distinct().toArray();

        int[] result = solve(possible);
        System.out.println(result.length);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public static int[] solve(int[] possible) {

        int[] newPossible = new int[possible.length];
        int j = 0;

        for (int i : possible) {
            newPossible[j++] = map[1][i];
        }

        newPossible = Arrays.stream(newPossible).distinct().toArray();

        if (newPossible.length == possible.length) {
            Arrays.sort(newPossible);
            return newPossible;
        }
        return solve(newPossible);
    }
}
