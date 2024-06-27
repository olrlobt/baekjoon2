import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        int size = input.length();

        int[] alp = new int[26];
        for(int i=0; i<size; i++){     
            alp[input.charAt(i)-'A']++;      
        }

        int center=0;
        int odd=0;
        for(int i=0; i<alp.length; i++){
            if(alp[i]%2 != 0 ){
                center = i ;
                odd++;
            }
        }
      
        if(odd>1 || (odd==1 && size%2==0)) {           
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        StringBuffer result = new StringBuffer();

        for(int i=0; i<alp.length; i++){
            for(int j=0; j<alp[i]/2; j++){
                result.append((char)(i+'A'));
            }
        }
        StringBuffer tmp = new StringBuffer(result.toString());     
        if(odd==1){
            result.append((char)(center+'A'));
        }
        System.out.println(result.toString()+tmp.reverse());     
    }
}