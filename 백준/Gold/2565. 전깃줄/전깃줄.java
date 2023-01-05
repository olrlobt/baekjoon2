import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int N;
    static List<Integer> dp = new ArrayList<>();
    static List<int[]> poles = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        for (int testCase = 0; testCase < N; testCase++) {
            int pole1 = sc.nextInt();
            int pole2 = sc.nextInt();

            int[] pole = {pole1, pole2};

            poles.add(pole);
        }
        solve();
    }

    public static void solve() {

        Collections.sort(poles, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < N ; i ++) {
            int line = 0;

            for(int j =0; j < i ; j ++ ){
                if(poles.get(i)[1] > poles.get(j)[1]){
                    line = Math.max(line,dp.get(j));
                }
            }
            line ++;
            dp.add(line);
        }
        System.out.println(N - Collections.max(dp));
    }
}
