import java.lang.*;
import java.util.*;

class Solution {
    
    List<List<Integer>> map = new ArrayList<>();
    boolean[] visited;
    int min = Integer.MAX_VALUE;
    int N;
    
    public int solution(int n, int[][] wires) { // 송전탑 // 전선갯수
        N = n;
        for(int i = 0; i <= n; i ++){
            map.add(new ArrayList<>());
        }
        
        for(int [] num : wires){
            map.get(num[0]).add(num[1]);
            map.get(num[1]).add(num[0]);
        }
        
        for(int [] num : wires){
            solve(num);
        }
        return min;         
    }
    
    private void solve(int[] cur){
        
        visited = new boolean[N+1];
        visited[cur[0]]= true;
        visited[cur[1]]= true;
        
        int one = dfs(cur[0]);
        int two = dfs(cur[1]);
        
        min = Math.min(min, Math.abs(two-one));
    }
    
    private int dfs(int cur){
        int count = 1;
        for(int next : map.get(cur)){
            if(visited[next]){
                continue;
            }
            visited[next] = true;
            count += dfs(next);
        }
        return count;
    }
}