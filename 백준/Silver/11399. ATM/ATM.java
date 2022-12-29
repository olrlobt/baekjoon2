import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        List<Integer> P = new ArrayList<>();

        for (int time = 0; time < N; time++) {
            P.add(sc.nextInt());
        }

        solve(N, P);
    }

    private static void solve(int N, List<Integer> P) {
        sortCollections(P);
        System.out.println(sumTimes(P));
    }

    private static void sortCollections(List<Integer> P) {
        Collections.sort(P);
    }

    private static int sumTimes(List<Integer> P) {
        int sum = 0;

        for (int i = 0; i < P.size(); i++){
            for(int j =0; j <= i ; j ++){
                sum += P.get(j);
            }
        }
        return sum;
    }


}
