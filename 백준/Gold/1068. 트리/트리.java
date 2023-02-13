
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int node = 0; node < N; node++) {
            int parent = sc.nextInt();
            if (parent == -1) {
                continue;
            }
            tree.get(parent).add(node);
        }

        int deleteNode = sc.nextInt();
        solve(deleteNode);

        int answer = 0;
        for (ArrayList<Integer> result : tree) {
            if(result.equals(List.of(deleteNode))){
                answer++;
            }

            if (result.isEmpty()) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static void solve(int deleteNode) {

        for (int node : tree.get(deleteNode)) {
            solve(node);
        }

        tree.get(deleteNode).add(-1);

    }
}
