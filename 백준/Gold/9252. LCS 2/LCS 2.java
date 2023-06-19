
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static String line1;
    static String line2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        line1 = sc.next();
        line2 = sc.next();

        solve();
    }

    public static void solve(){

        int [][] dp = new int[line2.length()+1][line1.length()+1];

        for (int i = 1; i <= line2.length(); i++) {
            for (int j = 1; j <= line1.length(); j++) {

                if(line2.charAt(i-1) == line1.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        int count = dp[line2.length()][line1.length()];
        int row = line2.length();
        int column = line1.length();
        Stack<Character> result = new Stack<>();
        while (count > 0){

            if(dp[row-1][column] == count){
                row -= 1;
                continue;
            }else if(dp[row][column-1] == count){
                column -= 1;
                continue;
            }else{
                result.push(line1.charAt(column-1));
                row -= 1;
                column -= 1;
                count--;
            }
        }

        System.out.println(result.size());
        while (!result.isEmpty()){
            System.out.print(result.pop());
        }

    }
}
