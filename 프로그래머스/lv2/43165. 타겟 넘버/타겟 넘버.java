class Solution {
    public int solution(int[] numbers, int target) {
      
        return dfs(numbers,target,0,0);
    }
    
    public int dfs(int[] numbers, int target,int cur, int count){
    
        
        
        if(count == numbers.length){
            if(cur == target ){
                
                System.out.print("hi" + count);
                return 1;
            }
            
            return 0;
        }
        int nextp = cur + numbers[count];
        int nextm = cur - numbers[count];
        
        return dfs(numbers,target,nextp,count+1) + dfs(numbers,target,nextm,count+1);
    }

}