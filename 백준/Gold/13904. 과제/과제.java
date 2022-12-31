
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {


    static List<int[]> task = new ArrayList<>();
    static List<Integer> usableTask = new ArrayList<>();
    static int max = 0;

    static int maxScore = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int testCase = 0; testCase < N; testCase++) {
            int d = sc.nextInt();
            int w = sc.nextInt();

            if (max < d) {
                max = d;
            }

            int[] P = {d, w};
            task.add(P);
        }

        solve();
        System.out.println(maxScore);
    }

    public static void solve() {

        while (max != 0) {
            setUsableTask();
            setAssignTask();
        }
    }

    private static void setAssignTask() {
        if (usableTask.size() != 0) {
            Integer maxValue = usableTask.stream()
                    .max(Comparator.comparing(x -> x))
                    .orElseThrow(NoSuchElementException::new);

            usableTask.remove(maxValue);
            maxScore += maxValue;
        }
        max--;
    }

    private static void setUsableTask() {
        for (int i = 0; i < task.size(); i++) {

            if (task.get(i)[0] >= max) {
                usableTask.add(task.get(i)[1]);
                task.remove(task.get(i));
                i--;
            }
        }
    }
}
