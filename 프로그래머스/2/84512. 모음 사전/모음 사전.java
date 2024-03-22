class Solution {
    public int solution(String word) {
        int answer = 0;
        
        int[] dis = {781,156,31,6,1};
        int len = word.length();
        
        for(int i = 0 ; i < len; i ++){
            char ch = word.charAt(i);
            if(ch == 'A'){
                answer += dis[i] * 0;
            }else if(ch == 'E'){
                answer += dis[i] * 1;
            }else if(ch == 'I'){
                answer += dis[i] * 2;
            }else if(ch == 'O'){
                answer += dis[i] * 3;
            }else if(ch == 'U'){
                answer += dis[i] * 4;
            }
        }
        
        return answer + len;
    }
}