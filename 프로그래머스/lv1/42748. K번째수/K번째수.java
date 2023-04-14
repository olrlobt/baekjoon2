import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int i = 0;
        for(int [] command : commands){
            int [] newArray = new int[commands.length];
            newArray = Arrays.copyOfRange(array,command[0]-1,command[1]);
            Arrays.sort(newArray);
            answer[i++] = newArray[command[2]-1];
        }
        
        return answer;
    }
}