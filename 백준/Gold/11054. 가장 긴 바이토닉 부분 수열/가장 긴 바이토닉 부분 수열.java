
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Integer> A = new ArrayList<>();
    static List<Integer> right = new ArrayList<>();
    static List<Integer> reverse = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int testCase = 0; testCase < N; testCase++) {
            A.add(sc.nextInt());
        }

        solve();
    }

    public static void solve() {

        smaller(right);
        Collections.reverse(A);
        smaller(reverse);
        
        int max = 0;
        for(int i = 0; i < right.size(); i ++){
            max = Math.max(max, right.get(i) + reverse.get(reverse.size() - i - 1));
        }

        System.out.println(max-1);
        
    }

    private static void smaller(List<Integer> list) {

        list.add(1); // 기저 값


        for (int index = 1; index < A.size(); index++) {
            int max = 1;

            for (int i = index; i >= 0; i--) {

                if (A.get(i) < A.get(index) ) {
                    max = Math.max(max,list.get(i) +1) ;
                }


            }
            list.add(max);

        }

    }
}
