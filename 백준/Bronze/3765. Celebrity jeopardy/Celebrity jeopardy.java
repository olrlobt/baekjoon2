import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder();

        while(true){
            String s = br.readLine();
            if(s == null || s.equals(""))
                break;
            str.append(s).append("\n");
        }

        System.out.print(str);
        br.close();
    }
}