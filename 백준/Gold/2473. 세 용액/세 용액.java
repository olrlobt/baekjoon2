
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static long[] map;
    static long[] result = new long[3];
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        map = new long[N];
        for (int num = 0; num < N; num++) {
            map[num] = sc.nextLong();
        }
        Arrays.sort(map);

        for(int mid = 0; mid < map.length-1; mid ++){
            solve(mid);
        }

        Arrays.sort(result);
        System.out.println(result[0]+ " " + result[1] + " " + result[2]);

    }


    public static void solve(int mid){

        int left = 0;
        int right = map.length -1;

        while (left < mid && mid < right){

            long sum = map[left] + map[mid] + map[right];
            if(Math.abs(sum) < min){
                min = Math.abs(sum);
                result[0] = map[left];
                result[1] = map[mid];
                result[2] = map[right];
            }


            if(sum < 0){
                left ++;
            }else{
                right --;
            }
        }
    }
}
