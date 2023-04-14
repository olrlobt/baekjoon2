import java.util.*;
import java.lang.*;
class Solution {
    
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited = new boolean[n];
        for(int i =0 ; i < n ; i ++){
            
            if(visited[i]){
                continue;
            }
            
            bfs(i,  computers);
            answer++;
        }
        
        return answer;
    }
    
    public void bfs(int start, int [][] computers){
        
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        
        while(!que.isEmpty()){
            int cur = que.poll();
            
            if(visited[cur]){
                continue;
            }
            visited[cur] = true;
            
            
            for(int i = 0 ; i < computers.length; i ++){
                
                if(i == cur || computers[cur][i] == 0){
                    continue;
                }
                System.out.print("hi");
                que.offer(i);
            }
            
        }
        
        
    }
}