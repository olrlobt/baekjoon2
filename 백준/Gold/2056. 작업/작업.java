
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int N;
    static ArrayList<ArrayList<Integer>> map;

    static int[] times;
    static int[] durations;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        map = new ArrayList<>();
        times = new int[N + 1];
        durations = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for (int work = 1; work <= N; work++) {

            times[work] = sc.nextInt();

            int num = sc.nextInt();
            for (int i = 0; i < num; i++) {
                map.get(work).add(sc.nextInt());
            }
        }
        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(solve(i) , max);
        }

        System.out.println(max);
    }

    public static int solve(int num) {

        if(durations[num] != 0 ){
            return durations[num];
        }

        int time = 0;
        for(int next : map.get(num)){
            time = Math.max(solve(next), time);
        }
        durations[num] = time + times[num];

        return durations[num];
    }
}
