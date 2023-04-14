import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new  PriorityQueue<>();
        
        for(int i : scoville){
            pq.offer(i);
        }
        
        while(pq.size() > 1 && pq.peek() < K){
            
            int one = pq.poll();
            int two = pq.poll();
            int mix = mix(one,two);
            
            pq.offer(mix);
            answer++;
        }
        
        if(!pq.isEmpty()  && pq.peek() < K ){
            return -1;
        }
        
        return answer;
    }
    
    public int mix(int one , int two){
        if(one > two){
            return one*2 + two;
        }else{
            return two*2 + one;
        }
    }
}