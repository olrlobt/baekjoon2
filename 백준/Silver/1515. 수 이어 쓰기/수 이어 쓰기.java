import java.io.*;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();        
        int result = 0;
        int index = 0;
        
        while(true) {
        	result++;
        	String resString = Integer.toString(result);
        	for(int i=0;i<resString.length();i++) {
        		if(resString.charAt(i) == input.charAt(index))
        			index++;
        		if(index == input.length()) {
        			System.out.println(result);
        			return;
        		}
        	}
        }
    }
}