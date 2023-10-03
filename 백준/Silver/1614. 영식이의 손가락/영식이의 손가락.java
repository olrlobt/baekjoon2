import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long hurt = Long.parseLong(br.readLine());
        long num = Long.parseLong(br.readLine());
        long result;
        
        if(hurt == 1) {
            result = (long)(num*8);
        }else if(hurt == 2) {
            result = (long)(1+(num/2)*8+(num%2)*6);
        }else if(hurt == 3) {
            result = (long)(2+(num/2)*8+(num%2)*4);
        }else if(hurt == 4) {
            result = (long)(3+(num/2)*8+(num%2)*2);
        }else {
            result = (long)(4 + num*8);
        }
        
        System.out.println(result);
    }
}