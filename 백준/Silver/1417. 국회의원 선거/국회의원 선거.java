import java.io.*;
import java.util.*;
 
public class Main{
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
      int N = Integer.parseInt(br.readLine());
      int dasom = Integer.parseInt(br.readLine());
 
      PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
 
      for(int i=1; i<N; i++) queue.offer(Integer.parseInt(br.readLine()));
 
      int cnt = 0;
      while(!queue.isEmpty() && dasom <= queue.peek()){
         queue.offer(queue.poll()-1); 
         dasom++;	
         cnt++;
      }
 
      System.out.println(cnt);
 
   }
}