import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken()); // 사대 갯수
        int N = Integer.parseInt(st.nextToken()); // 동물 수
        long L = Integer.parseInt(st.nextToken()); // 사정거리

        st = new StringTokenizer(br.readLine());
        int[] position = new int[M];
        for (int idx = 0; idx < M; idx++) {
            position[idx] = Integer.parseInt(st.nextToken());
        }

        List<Animal> animals = new ArrayList<>();
        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine());
            animals.add(new Animal(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
        }
        System.out.println(solve(position, animals, L));
    }

    private static int solve(int[] position, List<Animal> animals, long L) {
        Arrays.sort(position);
        int count = 0;


        for (Animal animal : animals) {
            long Ydiff = L - animal.row;
            if (Ydiff < 0) {
                continue; // do not catch
            }

            int left = -1;
            int right = position.length;
            int mid = 0;

            while (left + 1 < right) {
                mid = (left + right) / 2;

                if ( animal.col + Ydiff >= position[mid] && animal.col - Ydiff <= position[mid] ) {
                    count++;
                    break;
                } else if (animal.col - Ydiff > position[mid] ) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        return count;
    }

    public static class Animal {
        long col;
        long row;

        public Animal(long col, long row) {
            this.col = col;
            this.row = row;
        }
    }
}
