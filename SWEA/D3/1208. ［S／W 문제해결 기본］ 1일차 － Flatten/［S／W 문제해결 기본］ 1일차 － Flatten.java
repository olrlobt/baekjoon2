import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st ;
        for (int testCase = 1; testCase <= 10; testCase++) {
            int num = Integer.parseInt(br.readLine());
//            int[] map = new int[100];
            PriorityQueue<Integer> queue = new PriorityQueue();
             st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 100; i++) {
                queue.offer(Integer.parseInt(st.nextToken())) ;
            }

            sb.append("#").append(testCase).append(" ").append(solve(queue, num)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(PriorityQueue<Integer> map , int num) {

        PriorityQueue<Integer> queue = new PriorityQueue(Collections.reverseOrder());
        queue.addAll(map);

        for (int i = 0; i < num; i++) {
            map.offer((map.poll() + 1));
            queue.offer((queue.poll() - 1));
        }

        if(map.peek() > queue.peek()){
            return 1;
        }

        return queue.peek() - map.peek();
    }
}
