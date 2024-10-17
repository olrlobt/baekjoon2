import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int[] arr;
    static boolean[][] check;
    static int N;
    static StringBuilder sb = new StringBuilder(1);
    static List<List<Integer>> cache = new ArrayList<>(3);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cache.add(null);
        cache.add(new ArrayList<>());
        cache.add(new ArrayList<>());
        cache.add(new ArrayList<>());

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        arr[0] = 1;
        cache.get(1).add(0);
        check = new boolean[N][4];
        check[0][1] = true;

        solve(1);
        System.out.println(sb);
    }

    private static void solve(int idx) {
        if (sb.length() != 0) {
            return;
        } else if (idx == N) {
            Arrays.stream(arr).forEach(sb::append);
            return;
        }

        for (int next = 1; next <= 3; next++) {
            if (next == arr[idx - 1]) {
                continue;
            }
            arr[idx] = next;
            if (verification(idx, next)) {
                cache.get(next).add(idx); // index
                solve(idx + 1);
                cache.get(next).remove(cache.get(next).size() - 1);
            }
            arr[idx] = 0;
        }
    }

    private static boolean verification(int idx, int next) {
        boolean result = true;
        List<Integer> indexes = cache.get(next);

        if (!indexes.isEmpty()) {
            int size = indexes.size() - 1;

            while (size >= 0) {
                int lastIdx = indexes.get(size);
                if (lastIdx < idx / 2) {
                    break;
                }

                int i = 1; // last = 1   // idx = 3
                while (idx - i >= 0 && lastIdx - i >= 0 && arr[idx - i] == arr[lastIdx - i]) {
                    i++;
                }
                if (i == idx - lastIdx) { // 나쁜 수열14
                    result = false;
                    break;
                }
                size--;
            }
        }
        return result;
    }
}
