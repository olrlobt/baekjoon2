import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
       int n = Integer.parseInt(br.readLine());
       int bitset = 0;

       while(n-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int num;

            
            switch (op){
                case "add" :
                    num = Integer.parseInt(st.nextToken());
                   
                    bitset |= (1 << (num - 1));
                    break;
                case "remove" :
                    num = Integer.parseInt(st.nextToken());
                    bitset = bitset & ~(1 << (num - 1));
                    break;
                case "check" :
                    num = Integer.parseInt(st.nextToken());
                    sb.append((bitset & (1 << (num - 1))) != 0 ? "1\n" : "0\n");
                    break;
                case "toggle" :
                    num = Integer.parseInt(st.nextToken());
                    bitset ^= (1 << (num - 1));
                    break;
                case "all" :
                    bitset |= (~0);
                    break;
                case "empty" :
                    bitset &= 0;
                    break;
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}