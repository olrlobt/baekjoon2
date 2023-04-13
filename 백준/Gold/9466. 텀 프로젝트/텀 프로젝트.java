import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] map;
    static boolean[] visited;
    static boolean[] finish;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int testCase = 0; testCase < N; testCase++) {

            count = 0;
            int num = sc.nextInt();
            map = new int[num + 1];
            visited = new boolean[num + 1];
            finish = new boolean[num + 1];
            for (int i = 1; i <= num; i++) {
                map[i] = sc.nextInt();
            }

            for (int i = 1; i <= num; i++) {

                solve(i);
            }

            System.out.println(num - count);
        }
    }

    public static void solve(int num) {

        if (visited[num]) {
            return;
        }
        visited[num] = true;

        if (!visited[map[num]]) {
            solve(map[num]);
        } else {
            if (!finish[map[num]]) {
                count++;
                for (int i = map[num]; i != num; i = map[i]) {
                    count++;
                }
            }
        }
        finish[num] = true;
    }
}
