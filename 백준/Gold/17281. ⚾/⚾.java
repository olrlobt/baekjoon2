import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static ArrayList<ArrayList<Integer>> map;
    static int result = 0 ;
    static StringBuilder sb = new StringBuilder();
    static Set<Integer> visited = new HashSet<>();
    static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new ArrayList<>();

        for (int i = 0; i <= 9; i++) {
            map.add(new ArrayList<>());
        }

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int column = 0; column < 9; column++) {
                map.get(column).add(Integer.valueOf(st.nextToken()));
            }
        }
        int flag = 1;

        solve(flag);
        System.out.println(result);
    }

    private static void solve(int flag) {

        if(flag == 511){
            result = Math.max(play(), result);
            sb.deleteCharAt(3);
        }

        for (int i = 0; i < 9; i++) {
            if((flag & (1 << i)) != 0){
                continue;
            }
            flag |= (1 << i);
            sb.append(i);
            solve(flag);
            sb.deleteCharAt(sb.length() - 1);
            flag &= ~(1 << i);
        }
    }

    private static int play() {
        sb.insert(3,0);

        int out = 0;
        int inning  = 0;
        int score = 0;
        int num = 0;


        while (inning < map.get(0).size()) {
            int sum = 0;
            while (out < 3) { // inning change

                int cur = map.get(sb.charAt(num++) - '0').get(inning);

                if (num >= 9) {
                    num = 0;
                }

                if(cur == 0){ // out
                    out++;
                    continue;
                } else if (cur == 4) {
                    score+= queue.size() + 1;
                    queue.clear();
                    sum = 0;
                    continue;
                }

                queue.offer(cur); // run
                sum += cur;

                while (sum > 3){
                    sum -= queue.poll();
                    score ++;
                }

            }
            queue.clear();
            inning++;
            out = 0;
        }
        return score;
    }
}
