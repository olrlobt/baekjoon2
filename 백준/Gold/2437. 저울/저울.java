
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Integer> weight = new ArrayList<>();
    static Long findNum = 0L;
    private static int findIndex = 0;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int testCase = 0; testCase < N; testCase++) {
            weight.add(sc.nextInt());
        }

        solve();
        System.out.println(findNum);
    }

    public static void solve() {
        Collections.sort(weight);
        makeNumber();
    }

    private static void makeNumber() {
        findNumber();
    }

    private static void findNumber() {
        int sum = weight.get(0);

        if (weight.get(0) != 1) {
            findNum = 1L;
            return;
        }

        for (int i = 1; i < weight.size(); i++) {
            if (sum < weight.get(i)-1) {

                break;
            }
            sum += weight.get(i);
        }

        findNum = Long.valueOf(sum +1);
    }
}
