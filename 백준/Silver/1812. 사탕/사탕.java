import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        int[] nextSum = new int[n];
        int sum = 0;
        
        for(int i=0;i<n;i++){
            nextSum[i] = input.nextInt();
            sum += nextSum[i];
        }sum/=2;
 
        for(int i=0;i<n;i++){
            int cnt = i%2; int val = sum;
            
            if(i<2) cnt = i+1;
            
            while(true){
                if(cnt>=n) break;
                if(cnt==i) {cnt++; continue;}
                val -= nextSum[cnt];
                cnt+=2;
            }
            System.out.println(val);
        }
    }
}