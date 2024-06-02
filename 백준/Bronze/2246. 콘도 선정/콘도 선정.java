import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        int answer = 0;

        int[][] cond = new int[num][2];

        for(int i = 0; i < num; i++){
            cond[i][0] = sc.nextInt();
            cond[i][1] = sc.nextInt();
        }

        for(int i = 0; i < num; i++){
            if(checkQ(cond, i)) answer++; 
        }

        System.out.println(answer);
    }   
    
    static boolean checkQ(int[][] arrs, int index){
        for(int i = 0; i < arrs.length; i++){
            if(i == index) continue;

            if(arrs[index][0] >= arrs[i][0] && arrs[index][1] >= arrs[i][1]) return false;
        }
        return true;
    }
}