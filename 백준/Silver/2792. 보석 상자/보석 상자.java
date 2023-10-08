import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] color;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 아이 수
        int M = sc.nextInt(); // color
        color = new int[M];
        int max = 0;
        for (int c = 0; c < M; c++) {
            color[c] = sc.nextInt();
            if(color[c] > max){
                max = color[c];
            }
        }

        solve(max);
    }

    public static void solve(int max) {

        int low = 1;
        int high = max;

        while (low < high){

            int mid = (low + high) / 2;

            if(count(mid) > N){

                low = mid + 1 ;

            }else{

                high = mid;
            }

        }

        System.out.println(low);

    }

    public static int count(int mid){

        int count = 0 ;

        for(int i : color){

            count += i/mid;

            if(i%mid > 0){
                count++;
            }
        }
        return count;
    }
}
