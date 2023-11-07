import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solve(Integer.parseInt(br.readLine())));
    }

    private static int solve(int goal) {

        Queue<Emoji> queue = new ArrayDeque<>();
        queue.offer(new Emoji(1, 0, 0));
        boolean[] visited = new boolean[1001];

        while (!queue.isEmpty()) {

            Emoji curEmoji = queue.poll();

            if (curEmoji.num == goal) {
                return curEmoji.time;
            }

            // 클립보드 복사
            if (curEmoji.num != curEmoji.clip && !visited[curEmoji.num]) {
                visited[curEmoji.num] = true;
                queue.offer(new Emoji(curEmoji.num, curEmoji.time + 1, curEmoji.num));
            }

            // 붙여넣기
            if (curEmoji.num + curEmoji.clip <= 1000) {
                queue.offer(new Emoji(curEmoji.num + curEmoji.clip, curEmoji.time + 1, curEmoji.clip));
            }

            // 이모지 하나 삭제
            if (curEmoji.num > 1) {
                queue.offer(new Emoji(curEmoji.num - 1, curEmoji.time + 1, curEmoji.clip));
            }
        }
        return 0;
    }

    private static class Emoji {

        int num; // 이모지 수
        int time; // 현재 시간
        int clip; // 클립보드

        public Emoji(int num, int time, int clip) {
            this.num = num;
            this.time = time;
            this.clip = clip;
        }
    }


}