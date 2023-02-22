import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Stack;

public class Main {

    static String boom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String N = br.readLine();
        boom = br.readLine();

        solve(N);
        bw.close();
        br.close();
    }

    public static void solve(String N) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < N.length(); i++) {

            stack.push(N.charAt(i));
            if (stack.peek() == boom.charAt(boom.length() - 1) && stack.size() >= boom.length()) {

                boolean check = true;

                for (int j = 0; j < boom.length(); j++) {
                    if (stack.get(stack.size() - j - 1) != boom.charAt(boom.length() - 1 - j)) {
                        check = false;
                    }
                }

                if (check) {
                    for (int k = 0; k < boom.length(); k++) {
                        stack.pop();
                    }
                }


            }
        }

        if(stack.isEmpty()){
            System.out.println("FRULA");
            return;
        }
        StringBuffer bf = new StringBuffer();
        for(int i = 0 ; i < stack.size(); i ++){
            bf.append(stack.get(i));
        }
        System.out.println(bf);

    }
}
