import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map;
    static int N, M, R;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 1; column <= M; column++) {
                map[row][column] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] rcs = new int[R][3];
        int flag = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            rcs[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        solved(rcs, map, flag, 0);
        System.out.println(min);
    }

    private static void solved(int[][] rcs, int[][] previousMap, int flag, int count) {
        if (count == R) {

            for (int row = 1; row < N+1; row++) {
                min = Math.min(Arrays.stream(previousMap[row]).sum(), min);
            }
//
//            for (int row = 0; row < N+1; row++) {
//                System.out.println(Arrays.toString(previousMap[row]));
//            }
            return;
        }

        for (int i = 0; i < rcs.length; i++) {

            if ((flag & (1 << i)) != 0) {
                continue;
            }

            int r = rcs[i][0];
            int c = rcs[i][1];
            int s = rcs[i][2];

            int[][] curMap = new int[N + 1][M + 1];
            for (int row = 0; row < N + 1; row++) {
                System.arraycopy(previousMap[row], 0, curMap[row], 0, M + 1);
            }

            flag |= (1 << i);
//            System.out.println(Integer.toBinaryString(flag));

            while (r - s < r + s) {
                solve(r - s, c - s, r + s, c + s, previousMap,curMap);
                s--;
            }
            solved(rcs, curMap  , flag , count+1);
            flag &= ~(1 << i);
        }
    }


    private static void solve(int startRow, int startColumn, int lastRow, int lastColumn, int[][] previousMap, int[][] curMap) {

        Deque<Integer> queue = new LinkedList<>();

        for (int i = startColumn; i < lastColumn; i++) {
            queue.offerLast(previousMap[startRow][i]);
        }
        for (int i = startRow; i < lastRow; i++) {
            queue.offerLast(previousMap[i][lastColumn]);
        }
        for (int i = lastColumn; i > startColumn; i--) {
            queue.offerLast(previousMap[lastRow][i]);
        }
        for (int i = lastRow; i > startRow; i--) {
            queue.offerLast(previousMap[i][startColumn]);
        }

        queue.addFirst(queue.pollLast());

        for (int i = startColumn; i < lastColumn; i++) {
            curMap[startRow][i] = queue.pollFirst();
        }
        for (int i = startRow; i < lastRow; i++) {
            curMap[i][lastColumn] = queue.pollFirst();
        }
        for (int i = lastColumn; i > startColumn; i--) {
            curMap[lastRow][i] = queue.pollFirst();
        }
        for (int i = lastRow; i > startRow; i--) {
            curMap[i][startColumn] = queue.pollFirst();
        }

    }


}
