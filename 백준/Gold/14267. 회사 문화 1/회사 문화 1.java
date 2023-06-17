import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static int[] result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        result = new int[N];

        map.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            int parent = sc.nextInt();
            if (parent == -1) {
                continue;
            } else {
                map.get(parent).add(i);
            }
        }

        for (int i = 0; i < M; i++) {
            result[sc.nextInt()-1] += sc.nextInt();
        }

        solve(1);
        for( int re : result){
            System.out.print(re + " ");
        }
    }

    public static void solve(int index) {

        for(int child : map.get(index)){

            result[child-1] += result[index-1];
            solve(child);
        }
    }
}
