import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int gap = -100;
        int[] map = new int[9];
        for (int i = 0; i < 9; i++) {
            gap += map[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(map);
        int left= 0;
        int right = 8;

        while (left < right) {
            int sum = map[left] + map[right];
            if(sum == gap){
                break;
            }else if( sum > gap ){
                right--;
            }else{
                left ++;
            }
        }

        for (int i = 0; i < 9; i++) {
            if(i == right || i == left){
                continue;
            }
            System.out.println(map[i]);
        }
    }
}
