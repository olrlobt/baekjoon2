
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    static int M;
    static int N;
    static int count = 0;

    static ArrayList<int[]> chicken = new ArrayList<>();
    static ArrayList<int[]> home = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

         N = sc.nextInt();
         M = sc.nextInt();



        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {

                int b = sc.nextInt();

                if(b == 1){
                    home.add(new int[]{row,column});
                }

                if(b == 2){
                    chicken.add(new int[]{row,column});
                    count ++;
                }
            }
        }
        solve();

        System.out.println(min);
    }

    public static void solve(){

        boolean[] visited = new boolean[count];

        if(M < count){
            comb(visited,0,count, count - M);
        }else{
             min = distance(visited);
        }
    }

    public static void comb(boolean[] visited ,int start, int total, int choose){
        if(choose == 0) {
            min = Math.min(min,distance(visited));
            return;
        }

        for(int i = start; i < total; i++) {
            visited[i] = true;
            comb( visited,i + 1, total, choose - 1);
            visited[i] = false;
        }
    }


    public static int distance(boolean[] visited){

        int distance = 0;

        for(int [] ho : home){

            int min = Integer.MAX_VALUE;

            for(int i = 0 ; i < chicken.size() ; i ++){

                if(visited[i]){
                    continue;
                }

                int dis = Math.abs(ho[0] - chicken.get(i)[0]) + Math.abs(ho[1] - chicken.get(i)[1]);

                if(min > dis){
                    min = dis;
                }
            }

            distance += min;
        }

        return distance;
    }
}
