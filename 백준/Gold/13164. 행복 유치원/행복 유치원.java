
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Integer> height = new ArrayList<>();
    static List<Integer> heightGap = new ArrayList<>();
    static int K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        K = sc.nextInt();

        for (int testCase = 0; testCase < N; testCase++) {
            height.add(sc.nextInt());
        }

        solve();
    }

    public static void solve() {

        calHeightGap();
        calCost();
    }

    private static void calCost() {
        int sum = 0;

        for (int j = 0; j < heightGap.size() - K + 1; j++) {
            sum += heightGap.get(j);
        }

        System.out.println(sum);
    }

    private static void calHeightGap() {

        for (int i = 0; i < height.size() - 1; i++) {
            heightGap.add(height.get(i+1) - height.get(i));
        }
        Collections.sort(heightGap);
    }
}
