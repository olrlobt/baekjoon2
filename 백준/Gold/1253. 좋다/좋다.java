
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int [] map ;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        map = new int[N];
        for (int num = 0; num < N; num++) {

            map[num] = sc.nextInt();
        }
        Arrays.sort(map);

        solve();
    }

    public static void solve(){
        int count = 0;
        for(int i = 0 ; i < map.length ; i++){

            int left = 0 ;
            int right = map.length-1;

            while (left < right){

                if(right == i){
                    right--;
                    continue;
                }else if(left == i){
                    left++;
                    continue;
                }


                int sum = map[left]+ map[right];

                if(map[i] == sum){
                    count ++;
                    break;
                } else if (map[i] < sum) {
                    right --;
                } else {
                    left ++ ;
                }
            }
        }

        System.out.println(count);
    }
}
