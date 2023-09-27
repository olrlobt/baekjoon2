import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] square_flag = new int[9]; // 사분면
    static int[] row_flag = new int[9];
    static int[] column_flag = new int[9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[][] map = new char[9][9];
        for (int row = 0; row < 9; row++) {
            String input = br.readLine();
            for (int column = 0; column < 9; column++) {
                map[row][column] = input.charAt(column);

                if (map[row][column] - '0' == 0) {
                    continue;
                }
                int square = (row / 3) * 3 + (column / 3);

                row_flag[row] |= (1 << (map[row][column] - '0'));
                column_flag[column] |= (1 << (map[row][column] - '0'));
                square_flag[square] |= (1 << (map[row][column] - '0'));
            }
        }
        solve(map);

        for (char[] rows : map) {
            for (char idx : rows) {
                sb.append(idx);
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    private static void solve(char[][] map) {

        Node[][] map_flag = new Node[9][9];
        List<Node> list = new ArrayList<>();

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (map[row][column] - '0' != 0) {
                    continue;
                }
                int square = (row / 3) * 3 + (column / 3);
                int flag = row_flag[row] | column_flag[column] | square_flag[square];
                Node curNode = new Node(row, column, flag);
                map_flag[row][column] = curNode;
                list.add(curNode);
            }
        }
        list.sort((o1, o2) -> o1.count - o2.count);
        Iterator<Node> iterator = list.iterator();

        boolean IsCountOne = true;
        while (IsCountOne) {
            IsCountOne = false;
            while (iterator.hasNext()) {
                Node node = iterator.next();
                node.reLoad();
                if (node.count == 1) {
                    int check = (int) (Math.log(1022 - node.flag) / Math.log(2));
                    node.flag |= (1 << (check));
                    map[node.row][node.column] = (char) (check + '0');

                    int square = (node.row / 3) * 3 + (node.column / 3);
                    row_flag[node.row] |= (1 << (check));
                    column_flag[node.column] |= (1 << (check));
                    square_flag[square] |= (1 << (check));
                    IsCountOne = true;
                    iterator.remove();
                }
            }
        }

        list.sort((o1, o2) -> {
            if (o1.row == o2.row) {
                return o1.column - o2.column;
            }
            return o1.row - o2.row;
        });
        tracking(list, map, 0);
    }

    private static boolean tracking(List<Node> list, char[][] map, int index) {
        if (index == list.size()) {
            return true;
        }

        Node node = list.get(index);
        node.reLoad();
        for (int i = 1; i <= 9; i++) {
            if ((node.flag & (1 << i)) != 0) {
                continue;
            }

            int square = (node.row / 3) * 3 + (node.column / 3);
            if((row_flag[node.row] & (1 << i)) != 0 || (column_flag[node.column] & (1 << i)) != 0 || (square_flag[square] & (1 << i)) != 0){
                continue;
            }

            node.flag |= (1 << i);
            row_flag[node.row] |= (1 << (i));
            column_flag[node.column] |= (1 << (i));
            square_flag[square] |= (1 << (i));
            map[node.row][node.column] = (char) (i + '0');

            if(tracking(list, map, index + 1)){
                return true;
            }

            node.flag &= ~(1 << (i));
            row_flag[node.row] &= ~(1 << (i));
            column_flag[node.column] &= ~(1 << (i));
            square_flag[square] &= ~(1 << (i));
        }

        return false;
    }

//    private static void print(char[][] map) {
//
//        for (int row = 0; row < 9; row++) {
//            for (int column = 0; column < 9; column++) {
//
//                System.out.print(map[row][column] - '0');
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
//
//
//    }

    private static class Node {

        int row;
        int column;
        int flag;
        int count;


        public Node(int row, int column, int flag) {
            this.row = row;
            this.column = column;
            this.flag = flag;

            for (int i = 1; i <= 9; i++) {
                if ((this.flag & (1 << i)) != 0) {
                    continue;
                }
                this.count++;
            }
        }

        public void reLoad() {
            int square = (row / 3) * 3 + (column / 3);
            this.flag = row_flag[row] | column_flag[column] | square_flag[square];
            count = 0;
            for (int i = 1; i <= 9; i++) {
                if ((this.flag & (1 << i)) != 0) {
                    continue;
                }
                this.count++;
            }
        }

//        @Override
//        public int compareTo(Node o) {
//            return count - o.count;
//        }

        @Override
        public String toString() {
            return "Node{" +
                    "flag=" + flag +
                    ", count=" + count +
                    '}';
        }
    }
}
