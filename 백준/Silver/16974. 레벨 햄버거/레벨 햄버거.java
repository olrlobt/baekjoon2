import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // level
        long X = sc.nextLong(); // 먹은 갯수

        long size = getSize(N); // size

        long result = solve(size, X);
        System.out.println(result);
    }

    public static long solve(long size, long X) {
        if (size == 1) {
            return 1;
        }

        if (X == 1) {
            return 0;
        } else if (X == size) {
            return size / 2 + 1;
        }

        if ((size + 1) / 2 > X) { // 절만 미만 먹음
            return solve((size - 3) / 2, X - 1);
        } else if ((size + 1) / 2 == X) {
            return 1 + solve((size - 3) / 2, (size - 3) / 2);
        } else { // 절만 이상 먹음
            return 1 + solve((size - 3) / 2, (size - 3) / 2)
                    + solve((size - 3) / 2, X - ((size - 3) / 2 + 2));
        }

    }

    private static long getSize(long level) {
        if (level == 0) {
            return 1;
        }
        return 3 + getSize(level - 1) * 2;
    }
}
