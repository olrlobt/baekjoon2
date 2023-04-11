import java.util.Scanner;

public class Main {

    static int [] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        map = new int[N];
        for (int num = 0; num < N; num++) {
            map[num] = sc.nextInt();
        }

        solve();
    }

    public static void solve(){

        int left = 0;
        int right = map.length-1;
        int min = Integer.MAX_VALUE;
        int minx = 0;
        int miny = 0;
        while(left<right){

            int sum = map[left] + map[right];

            if(Math.abs(sum) < min){
                min = Math.abs(sum);
                minx = left;
                miny = right;
            }
            if(sum < 0){
                left ++;
            }
            else {
                right --;
            }
        }

        System.out.println(map[minx] + " " + map[miny]);
    }
}
