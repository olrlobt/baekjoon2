import java.util.Arrays;

class Solution {
    static int[][] fatigues;
    static int len;
    static int result = Integer.MAX_VALUE;
    
    public  int solution(int[] picks, String[] minerals) {
        result = Integer.MAX_VALUE;
        int count = 0;
        int idx = 0;
        len = minerals.length / 5;
        if (minerals.length % 5 != 0) {
            len++;
        }

        fatigues = new int[len][3];
        int[] tempFatigue = new int[3]; // dia iron stone

        for (String mineral : minerals) {
            count++;

            if (mineral.equals("diamond")) {
                tempFatigue[0]++;
                tempFatigue[1]+= 5;
                tempFatigue[2]+= 25;
            } else if (mineral.equals("iron")) {
                tempFatigue[0]++;
                tempFatigue[1]++;
                tempFatigue[2] += 5;
            } else if (mineral.equals("stone")) {
                tempFatigue[0]++;
                tempFatigue[1]++;
                tempFatigue[2]++;
            }

            if (count != 5) {
                continue;
            }

            System.arraycopy(tempFatigue, 0, fatigues[idx++], 0, 3);
            Arrays.fill(tempFatigue, 0);
            count = 0;
        }
        if (count != 0) {
            System.arraycopy(tempFatigue, 0, fatigues[idx], 0, 3);
        }

        dfs(Arrays.copyOf(picks, 3), 0, 0);
        return result;
    }

    private void dfs(int[] picks, int idx, int fatigue) {
        if (idx == len) {
            result = Math.min(result, fatigue);
            return;
        } else if (picks[0] == 0 && picks[1] == 0 && picks[2] == 0) {
            result = Math.min(result, fatigue);
            return;
        }

        if(picks[0] > 0){
            picks[0] --;
            dfs(picks, idx + 1, fatigue + fatigues[idx][0]);
            picks[0] ++;
        }
        if(picks[1] > 0){
            picks[1] --;
            dfs(picks, idx + 1, fatigue + fatigues[idx][1]);
            picks[1] ++;
        }
        if(picks[2] > 0){
            picks[2] --;
            dfs(picks, idx + 1, fatigue + fatigues[idx][2]);
            picks[2] ++;
        }
    }
}