
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] map;
    static int[] operator;
    static int N;

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N];
        operator = new int[4];
        for (int num = 0; num < N; num++) {
            map[num] = sc.nextInt();
        }

        for (int i = 0; i < 4; i++) {
            operator[i] = sc.nextInt();
        }

        solve(1, map[0], operator);

        System.out.println(max);
        System.out.println(min);
    }

    public static void solve(int index, int result, int[] operator) {

        if (index == N) {
            if (result < min) {
                min = result;
            }
            if (result > max) {
                max = result;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int [] nextOperator = Arrays.copyOf(operator,4);
            if (nextOperator[i] == 0) {
                continue;
            }
            nextOperator[i] -= 1;

            switch (i) {

                case 0:
                    solve(index + 1, result + map[index], nextOperator);
                    continue;
                case 1 :
                    solve(index + 1, result - map[index], nextOperator);
                    continue;
                case 2:
                    solve(index + 1, result * map[index], nextOperator);
                    continue;
                case 3 :
                    solve(index + 1, result / map[index], nextOperator);
                    continue;

            }
        }
    }
}
