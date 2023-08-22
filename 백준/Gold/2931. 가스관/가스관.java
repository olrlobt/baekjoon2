import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] left = {0, -1};  // 각각 의 방향을 dy, dx 로 저장한다.
    static final int[] right = {0, 1};
    static final int[] up = {-1, 0};
    static final int[] down = {1, 0};

    enum Block {  // 방향을 저장하는 Enum Block 이다.
        I('|', Arrays.asList(up, down)),
        M('-', Arrays.asList(left, right)),
        P('+', Arrays.asList(up, down, left, right)),
        One('1', Arrays.asList(down, right)),
        Two('2', Arrays.asList(up, right)),
        Three('3', Arrays.asList(left, up)),
        Four('4', Arrays.asList(left, down));

        private static Map<Character, Block> mapping;
        private char shape;
        private List<int[]> direction;

        static {
            mapping = new HashMap<>();
            for (Block block : Block.values()) {
                mapping.put(block.shape, block);
            }
        }

        Block(char shape, List<int[]> direction) {
            this.shape = shape;
            this.direction = direction;
        }

        static List<int[]> getDirection(char shape) {  // 방향을 얻어온다.
            return mapping.get(shape).direction;
        }

        static Character getShape(List<int[]> direction) {  // 모양을 얻어온다.

            for (Block block : Block.values()) {
                int cnt = 0;

                if (block.direction.size() != direction.size()) {
                    continue;
                }

                for (int[] ndir : block.direction) {
                    for (int[] dir : direction) {
                        if (dir[0] == ndir[0] && dir[1] == ndir[1]) {
                            cnt++;
                        }
                    }
                }
                if (cnt == direction.size()) {
                    return block.shape;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 버퍼리더
        StringBuilder sb = new StringBuilder(); // 출력을 위한 스트링빌더
        StringTokenizer st = new StringTokenizer(br.readLine());// 입력을 나누기 위해 StringTokenizer로 입력받는다.

        int R = Integer.parseInt(st.nextToken()); //  R입력받는다
        int C = Integer.parseInt(st.nextToken()); // C입력받는다

        char[][] map = new char[R][C]; // 지도를 생성한다.
        for (int row = 0; row < R; row++) {
            map[row] = br.readLine().toCharArray(); // 맵 입력 받는다.
        }

        int[] result = solve(map);  // 한칸 빠진 좌표를 얻어온다
        List<int[]> directions = solve2(result, map); // 해당 좌표의 모양을 가져온다.

        sb.append(result[0] + 1).append(" ").append(result[1] + 1)  //결과를 만들어 준다.
                .append(" ").append(Block.getShape(directions)).append("\n");

        System.out.println(sb);  // 결과를 출력한다.
    }

    private static List<int[]> solve2(int[] result, char[][] map) {
        List<int[]> direction = new ArrayList<>();  // 방향을 저장한다.

        for (int[] dir : Arrays.asList(up, down, left, right)) { // 네가지 방향을 찾는다.
            int nextRow = result[0] + dir[0];
            int nextColumn = result[1] + dir[1];  // 다음에 찾을  row  colmun

            if (nextRow < 0 || nextRow >= map.length || nextColumn < 0 || nextColumn >= map[0].length) {
                continue;
            }

            if (map[nextRow][nextColumn] == '.' || map[nextRow][nextColumn] == 'M' || map[nextRow][nextColumn] == 'Z') { // . 이라면 탐색하지 않는다.
                continue;
            }

            for (int[] nextDir : Block.getDirection(map[nextRow][nextColumn])) {  // 방향을 추가해준다.
                if (dir[0] + nextDir[0] == 0 && dir[1] + nextDir[1] == 0) {
                    direction.add(dir);
                }
            }
        }
        return direction;
    }

    private static int[] solve(char[][] map) {

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (map[row][column] == '.' || map[row][column] == 'M' || map[row][column] == 'Z') {  // 도로를 탐색한다. 나머지는 건너 뛴다.
                    continue;
                }

                for (int[] dir : Block.getDirection(map[row][column])) {   // 도로라면 이어져 있는지 확인한다.
                    int nextRow = row + dir[0];
                    int nextColumn = column + dir[1];

                    if (nextRow < 0 || nextRow >= map.length || nextColumn < 0 || nextColumn >= map[0].length) {
                        continue;
                    }

                    if (map[nextRow][nextColumn] == '.') {  // 끊어져 있다면 좌표를 반환한다.
                        return new int[]{nextRow, nextColumn};
                    }
                }
            }
        }
        return null;
    }
}