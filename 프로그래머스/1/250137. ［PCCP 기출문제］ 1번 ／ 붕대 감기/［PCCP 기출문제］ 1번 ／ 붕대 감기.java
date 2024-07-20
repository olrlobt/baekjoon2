import java.util.HashMap;
import java.util.Map;

class Solution {

    public int solution(int[] bandage, int health, int[][] attacks) {
        Map<Integer, Integer> attackInfo = new HashMap<>();
        int curHP = health;
        int endTime = 0;
        
        for (int[] attack : attacks) {
            attackInfo.put(attack[0], attack[1]);
            endTime = attack[0];
        }

        int time = 0;
        
        for (int i = 1; i <= endTime; i++) {
            if (attackInfo.containsKey(i)) { 
                curHP -= attackInfo.get(i);
                time = 0;
            } else { 
                curHP += bandage[1];
                time++;

                if (time == bandage[0]) {
                    curHP += bandage[2];
                    time = 0;
                }
                if (curHP > health) {
                    curHP = health;
                }
            }

            if (curHP > 0) { 
                continue;
            }
            return -1;
        }
        return curHP;
    }
}