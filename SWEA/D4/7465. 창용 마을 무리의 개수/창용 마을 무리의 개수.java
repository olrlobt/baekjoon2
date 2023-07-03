
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testCase = 1; testCase <= T; testCase++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int count = 0;
            boolean[] visited = new boolean[N + 1];
            ArrayList<ArrayList<Integer>> relations = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                relations.add(new ArrayList<>());
            }

            for (int number = 0; number < M; number++) {
                int person1 = sc.nextInt();
                int person2 = sc.nextInt();

                relations.get(person1).add(person2);
                relations.get(person2).add(person1);
            }

            for (int start = 1; start <= N; start++) {
                if (visited[start]) {
                    continue;
                }
                checkRelation(start, visited, relations);
                count++;
            }

            System.out.println("#" + testCase + " " + count);
        }
    }

    private static void checkRelation(int curNum, boolean[] visited, ArrayList<ArrayList<Integer>> relations) {

        visited[curNum] = true;

        for (int nextNum : relations.get(curNum)) {
            if (visited[nextNum]) {
                continue;
            }
            checkRelation(nextNum, visited, relations);
        }
    }
}
