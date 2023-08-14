import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());

        String task = br.readLine();
        int H = Integer.parseInt(br.readLine());
        int cur = 0;

        if(task.lastIndexOf("U") < task.lastIndexOf("D")){
            cur+=2;
        }
        if(task.lastIndexOf("L") < task.lastIndexOf("R")){
            cur+=1;
        }

        if (cur / 2 == 1) {
            H += 2;
            if(H >= 4){
                H -= 4;
            }
        }
        if (cur % 2 == 1) {
            H += 1;
            if(H % 2 == 0){
                H -= 2;
            }
        }

        int length = (int) Math.pow(2, K);
        int current = H;

        for (int row = 0; row < length; row++) {
            for (int column = 0; column < length; column++) {
                sb.append(current).append(" ");
                if (current % 2 == 1) {
                    current--;
                } else {
                    current++;
                }
            }
            if (current / 2 == 1) {
                current -= 2;
            } else {
                current += 2;
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

}
