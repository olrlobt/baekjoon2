
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int M;
    static List<Integer> leftPosition = new ArrayList<>();
    static List<Integer> rightPosition = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        M = sc.nextInt();

        for (int testCase = 0; testCase < N; testCase++) {
            int position = sc.nextInt();

            if (position < 0) {
                leftPosition.add(Math.abs(position));
            } else {
                rightPosition.add(position);
            }

        }
        leftPosition.add(0);
        rightPosition.add(0);

        solve();
    }

    public static void solve() {

        Collections.sort(leftPosition, Collections.reverseOrder());
        Collections.sort(rightPosition, Collections.reverseOrder());

        int b = leftPosition.get(0);

        if (leftPosition.get(0) > rightPosition.get(0)) {
            b = rightPosition.get(0);
        }

        int sum = b + calMinStep(leftPosition) + calMinStep(rightPosition);
        System.out.println(sum);
    }

    private static int calMinStep(List<Integer> position) {
        int step = position.get(0);

        for (int i = M; i < position.size() - 1; i++) {
            int a = i / M;
            step += (position.get(i) - position.get(i + 1)) * 2 * a;
        }
        return step;
    }


}
