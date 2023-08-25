import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int fishierKing = 0;
    static int score = 0;

    static final int[] dx = {0, 0, 0, 1, -1};
    static final int[] dy = {0, -1, 1, 0, 0};

    static int R, C, M;

    static PriorityQueue<Shark> pq;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

       pq = new PriorityQueue<>();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()); // 속력
            int d = Integer.parseInt(st.nextToken()); //위 아래 오 왼
            int z = Integer.parseInt(st.nextToken()); // 사이즈

            pq.offer(new Shark(r, c, s, d, z));
        }

        solve(C);
        System.out.println(score);
    }

    private static void solve(int C) {

        while (!pq.isEmpty() && fishierKing++ <= C) {
            // 낚시
            fishing();
            //상어 이동
            moveSharks();
            // 상어 > 상어 잡아먹기
            fightSharks();
            pq.comparator();
        }
    }

    private static void fightSharks() {
        if(pq.isEmpty()){
            return ;
        }
        PriorityQueue<Shark> newPQ = new PriorityQueue<>();

        Shark before = pq.poll();
        while (!pq.isEmpty()){
            Shark next = pq.poll();

            if (before.row == next.row && before.column == next.column) {
                continue;
            }
            newPQ.offer(before);
            before = next;
        }
        newPQ.offer(before);
        pq = newPQ;
    }

    private static void moveSharks() {
        PriorityQueue<Shark> newPQ = new PriorityQueue<>();
        int size = pq.size();

        while (size-- > 0) {
            Shark curShark = pq.poll();

            int speed = curShark.speed;
            while (speed-- > 0) {

                if (curShark.column == 1 && curShark.direction == 4) { // 방향 전환
                    curShark.direction = 3;
                }else if(curShark.column == C && curShark.direction == 3){
                    curShark.direction = 4;
                }else if(curShark.row == 1 && curShark.direction == 1){
                    curShark.direction = 2;
                }else if(curShark.row == R && curShark.direction == 2){
                    curShark.direction = 1;
                }

                curShark.row += dy[curShark.direction];
                curShark.column += dx[curShark.direction];
            }
            newPQ.offer(curShark);
        }
        pq = newPQ;
    }

    private static void fishing() {
        if (!pq.isEmpty() && pq.peek().column == fishierKing) {
            score += pq.poll().size;
        }
    }


    private static class Shark implements Comparable<Shark> {
        int row;
        int column;
        int speed;
        int direction;
        int size;

        public Shark(int row, int column, int speed, int direction, int size) {
            this.row = row;
            this.column = column;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }

        @Override
        public int compareTo(Shark o) {

            if (o.column == fishierKing + 1) {
                if(column == o.column){
                    if (row == o.row) {
                        return Integer.compare(o.size,size);
                    }
                    return Integer.compare(row, o.row);
                }
                return 1;
            }else if(column == fishierKing + 1){
                if(column == o.column){
                    if (row == o.row) {
                        return Integer.compare(o.size,size);
                    }
                    return Integer.compare(row, o.row);
                }
                return -1;
            }else if(column == o.column){
                if (row == o.row) {
                    return Integer.compare(o.size, size);
                }
                return Integer.compare(row, o.row);
            }
            return Integer.compare(column, o.column);
        }
    }

}
