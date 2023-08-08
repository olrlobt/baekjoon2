

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {-1, 1, 0, 0};

    static int R, C;

    static int fisherX = 0 ;
    static int fisherCount = 0;

    static ArrayList<Shark> sharks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 상어 수

        sharks = new ArrayList<>();

        for (int shark = 0; shark < M; shark++) {
            st = new StringTokenizer(br.readLine());
            sharks.add(new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        solve();
        System.out.println(fisherCount);
    }

    private static void solve() {

        while (fisherX <= C){
            fisherX += 1;  // 낚시왕 이동
            huntShark();  // 사냥
            moveShark();

        }
    }

    private static void moveShark() {
        for(Shark shark : sharks){

            if (shark.dir < 3) { // 위나 아래. row

                for (int i = 0; i < shark.speed; i++) {
                    if(shark.row == 1 && shark.dir == 1){
                        shark.dir = 2;
                    }else if(shark.row == R && shark.dir == 2){
                        shark.dir = 1;
                    }

                    shark.row += dy[shark.dir - 1];
                }

            }else{ //column

                for (int i = 0; i < shark.speed; i++) {

                    if(shark.column == 1 && shark.dir == 4){
                        shark.dir = 3;
                    }else if(shark.column == C && shark.dir == 3){
                        shark.dir = 4;
                    }

                    shark.column += dx[shark.dir - 1];
                }
            }
        }
    }

    private static void huntShark() {
        Collections.sort(sharks);
        int preRow = 0;
        int preColumn = 0;
        boolean kill = false;

        for (int i = 0; i < sharks.size(); i++) {

            if (sharks.get(i).row == preRow && sharks.get(i).column == preColumn) {
                sharks.remove(i--);

                continue;
            }
            preRow = sharks.get(i).row;
            preColumn = sharks.get(i).column;

            if (!kill && sharks.get(i).column == fisherX) {
                fisherCount += sharks.get(i).size;
                sharks.remove(i--);

                kill = true;
            }
        }


    }


    private static class Shark implements Comparable<Shark> {
        int row;
        int column;
        int speed;
        int dir;
        int size;

        public Shark(int row, int column, int speed, int dir, int size) {
            this.row = row;
            this.column = column;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

        @Override
        public int compareTo(Shark o) {
            if(row == o.row){
                if(column == o.column){
                    return Integer.compare(o.size, size);
                }
                return Integer.compare(column, o.column);
            }
            return Integer.compare(row, o.row);
        }


        @Override
        public String toString() {
            return row + " / " + column + " / " + dir;

        }
    }

}
