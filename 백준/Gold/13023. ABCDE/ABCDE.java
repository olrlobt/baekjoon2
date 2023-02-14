
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Integer>> friends = new ArrayList<>();
    static boolean[] visited;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();//친구 수
        int M = sc.nextInt(); //관계
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            friends.add(new ArrayList<>());
        }

        for (int relation = 0; relation < M; relation++) {
            int friend1 = sc.nextInt();
            int friend2 = sc.nextInt();

            friends.get(friend1).add(friend2);
            friends.get(friend2).add(friend1);
        }

        int max = 0;
        for (int start = 0; start < N; start++) {
            visited[start] = true;
            if(solve(start,1)){
                max = 1;
                break;
            }
            visited[start] = false;
        }
        System.out.println(max);
    }

    public static boolean solve(int start, int count) {

        if (count == 5) {
            return true;
        }

        for (int friend : friends.get(start)) {

            if (visited[friend]) {
                continue;
            }
            visited[friend] = true;


            if (solve(friend, count+1) ){
                return true;
            };
            visited[friend] = false;
        }

        return false;
    }
}
