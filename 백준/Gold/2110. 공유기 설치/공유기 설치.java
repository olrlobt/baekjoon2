
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] map;
    static int C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        C = sc.nextInt();
        map = new int[N];

        for (int num = 0; num < N; num++) {
            map[num] = sc.nextInt();
        }
        Arrays.sort(map);
        solve();
    }

    public static void solve() {

        C -= 1; // 처음과 끝 집 설치

        int low = 1;
        int high = map[map.length-1] - map[0] + 1;

        while(low < high){

            int mid = (low+high)/2;
            int count = distance(mid);


            if(count < C){
                high = mid;
            }else{
                low = mid + 1;
            }
        }
        System.out.println(low -1);
    }

    public static int distance(int distance){

        int last = map[0];
        int count = 0;
        for(int x : map){
            if(x - last >= distance){
                count++;
                last = x;
            }
        }
        return count;
    }
}
