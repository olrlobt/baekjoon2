
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Integer> point = new ArrayList<>();
    static List<Integer> distanceOfPoint = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        for (int testCase = 0; testCase < N; testCase++) {
            point.add(sc.nextInt());
        }

        solve(K);
    }

    public static void solve(int K) {
        Collections.sort(point);
        setDistanceOfPoint();
        calMinDistance(K);
    }

    private static void calMinDistance(int k) {
        int sum = 0;

        Collections.sort(distanceOfPoint);
        for (int i=0; i< distanceOfPoint.size() - k + 1; i++){
            sum += distanceOfPoint.get(i);
        }
        System.out.println(sum);
    }

    private static void setDistanceOfPoint() {
        for (int i = 0; i < point.size() - 1; i++) {
            distanceOfPoint.add(point.get(i + 1) - point.get(i));
        }
    }


}
