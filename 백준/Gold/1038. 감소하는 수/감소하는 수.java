
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static int N;
    static ArrayList<ArrayList<Long>> decrease = new ArrayList<>();
    static ArrayList<Long> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        solve();
    }

    public static void solve() {

        for(int index = 0 ; index < 10; index ++){
            decrease.add(new ArrayList<>());

            allDecrease(index);
        }
        if(N >= result.size()){
            System.out.println(-1);
            return;
        }
        Collections.sort(result);
        System.out.println(result.get(N));
    }

    private static void allDecrease(int index) {

        ArrayList<Long> arr = new ArrayList<>();
        arr.add((long) index);

        for(int i = index-1 ; i >= 0; i--){

            for(long j : decrease.get(i)){
                String k = String.valueOf(index) + String.valueOf(j);
                arr.add(Long.parseLong(k));
            }

        }
        decrease.get(index).addAll(arr);
        result.addAll(arr);
    }
}
