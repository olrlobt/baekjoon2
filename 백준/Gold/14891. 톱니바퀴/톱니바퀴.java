import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static StringBuffer[] sb = new StringBuffer[4];
    static boolean[] visited ;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int shape = 0; shape < 4; shape++) {
                sb[shape]= new StringBuffer(sc.next());
        }
        int N = sc.nextInt();

        for(int move = 0 ; move < N; move ++){
            visited = new boolean[4];
            solve(sc.nextInt()-1,sc.nextInt());

        }
        int v = 0;
        for(int i = 0 ; i < 4 ; i ++ ){
             v += Integer.parseInt(String.valueOf(sb[i].charAt(0))) * Math.pow(2, i);

        }
        System.out.println(v);

    }


    public static void solve(int num, int a){

        int other = 1;
        if(a == 1){
            other = -1;
        }

        if(visited[num]){
            return;
        }
        visited[num] = true;


        if(num != 3  && sb[num].charAt(2) != sb[num+1].charAt(6)){
            solve(num+1 , other);
        }
        if(num != 0 && sb[num].charAt(6) != sb[num-1].charAt(2)) {
            solve(num-1 , other);
        }


        if(a == 1){
            right(sb[num]);
        }else{
            left(sb[num]);
        }
    }

    public static void right(StringBuffer sb){
        sb.insert(0,sb.charAt(sb.length()-1));
        sb.delete(sb.length()-1,sb.length());
    }

    public static void left(StringBuffer sb){
        sb.append(sb.charAt(0));
        sb.delete(0,1);
    }

}
