
import java.util.Scanner;

public class Main {

    static int N;
    static int[] chessBoard;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        chessBoard = new int[N];

        dfs(0);
        System.out.println(count);
    }

    private static void dfs(int row) {

        if (row == N) {
            count++;
            return;
        }

        for (int column = 0; column < N; column++) {

            chessBoard[row] = column;

            if (check(row)) {
                dfs(row + 1);
            }
        }
    }

    private static boolean check(int row) {

        for (int i = 0; i < row; i++) {

            if (chessBoard[row] == chessBoard[i]) { // 같은 세로줄에 있을 경우
                return false;
            }
            if (chessBoard[row] - chessBoard[i] == row - i) { // 좌상향 대각선에 있을 경우
                return false;
            }
            if (chessBoard[i] - chessBoard[row] == row - i) { // 우상향 대각선에 있을 경우
                return false;
            }

        }
        return true;
    }
}
