import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();
        char[] str = new char[N];
        int countR = 0;
        int countB = 0;

        for (int i = 0; i < N; i++) {
            str[i] = input.charAt(i);
            if(str[i] == 'R') countR++;
            else if(str[i] == 'B') countB++;
        }

        char start = str[0];
        char end = str[str.length - 1];
        int startCount = N;
        int endCount = N;

        for (int idx = 1; idx < N; idx++) {
            if(startCount != N && endCount != N) {
                break;
            }
            if(startCount == N && str[idx] != start){
                startCount = idx;
            }
            if(endCount == N && str[str.length - 1 - idx] != end){
                endCount = idx;
            }
        }

        int min = Math.min(countB, countR);
        if (start == 'B') {
            min = Math.min(min, countB - startCount);
        }else{
            min = Math.min(min, countR - startCount);
        }

        if(end == 'B') {
            min = Math.min(min, countB - endCount);
        }else {
            min = Math.min(min, countR - endCount);

        }

        System.out.println(min);
    }
}
