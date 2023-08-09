import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int [][] map;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());


        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < M; column++) {
                map[row][column] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int command = 0; command < R; command++) {
            solve(st.nextToken());
        }


        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                sb.append(map[row][column]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void solve(String command) {

        switch (command) {
            case "1":
                one();
                break;
            case "2":
                two();
                break;
            case "3":
                map = three();
                break;
            case "4":
                map = four();
                break;
            case "5":
                five();
                break;
            case "6":
                six();
                break;
        }


    }

    private static void one() {
        Queue<Integer> queue = new LinkedList<>();

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                queue.offer(map[row][column]);
            }
        }

        for (int row = map.length-1; row >= 0; row--) {
            for (int column = 0; column < map[0].length; column++) {
                map[row][column] = queue.poll();
            }
        }
    }


    private static void two() {
        Queue<Integer> queue = new LinkedList<>();

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                queue.offer(map[row][column]);
            }
        }

        for (int row = 0; row < map.length; row++) {
            for (int column = map[0].length - 1 ; column >= 0 ; column--) {
                map[row][column] = queue.poll();
            }
        }
    }


    private static int[][] three() {
        Queue<Integer> queue = new LinkedList<>();

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                queue.offer(map[row][column]);
            }
        }

        int[][] newMap = new int[map[0].length][map.length];
        for (int column = newMap[0].length - 1 ; column >= 0 ; column--) {
            for (int row = 0; row < newMap.length; row++) {
                newMap[row][column] = queue.poll();
            }
        }

        return newMap;
    }


    private static int[][] four() {
        Queue<Integer> queue = new LinkedList<>();

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                queue.offer(map[row][column]);
            }
        }
        int[][] newMap = new int[map[0].length][map.length];
        for (int column = 0; column < newMap[0].length; column++) {
            for (int row = newMap.length-1; row >= 0; row--) {
                newMap[row][column] = queue.poll();
            }
        }
        return newMap;
    }



    private static void five() {
        Queue<Integer> queue = new LinkedList<>();

        for (int row = 0; row < map.length/2; row++) {
            for (int column = 0; column < map[0].length/2; column++) {
                queue.offer(map[row][column]);
            }
        }

        for (int row = 0; row < map.length/2; row++) {
            for (int column = map[0].length/2; column < map[0].length; column++) {
                queue.offer(map[row][column]);
            }
        }

        for (int row = map.length/2; row < map.length; row++) {
            for (int column = map[0].length/2; column < map[0].length; column++) {
                queue.offer(map[row][column]);
            }
        }

        for (int row = map.length/2; row < map.length; row++) {
            for (int column = 0; column < map[0].length/2; column++) {
                queue.offer(map[row][column]);
            }
        }

        //
        for (int row = 0; row < map.length/2; row++) {
            for (int column = map[0].length/2; column < map[0].length; column++) {
                map[row][column] = queue.poll();
            }
        }

        for (int row = map.length/2; row < map.length; row++) {
            for (int column = map[0].length/2; column < map[0].length; column++) {
                map[row][column] = queue.poll();
            }
        }

        for (int row = map.length/2; row < map.length; row++) {
            for (int column = 0; column < map[0].length/2; column++) {
                map[row][column] = queue.poll();
            }
        }

        for (int row = 0; row < map.length/2; row++) {
            for (int column = 0; column < map[0].length/2; column++) {
                map[row][column] = queue.poll();
            }
        }


    }

    private static void six() {
        Queue<Integer> queue = new LinkedList<>();

        for (int row = 0; row < map.length/2; row++) {
            for (int column = 0; column < map[0].length/2; column++) {
                queue.offer(map[row][column]);
            }
        }

        for (int row = 0; row < map.length/2; row++) {
            for (int column = map[0].length/2; column < map[0].length; column++) {
                queue.offer(map[row][column]);
            }
        }

        for (int row = map.length/2; row < map.length; row++) {
            for (int column = map[0].length/2; column < map[0].length; column++) {
                queue.offer(map[row][column]);
            }
        }

        for (int row = map.length/2; row < map.length; row++) {
            for (int column = 0; column < map[0].length/2; column++) {
                queue.offer(map[row][column]);
            }
        }


        for (int row = map.length/2; row < map.length; row++) {
            for (int column = 0; column < map[0].length/2; column++) {
                map[row][column] = queue.poll();
            }
        }
        for (int row = 0; row < map.length/2; row++) {
            for (int column = 0; column < map[0].length/2; column++) {
                map[row][column] = queue.poll();
            }
        }
        //
        for (int row = 0; row < map.length/2; row++) {
            for (int column = map[0].length/2; column < map[0].length; column++) {
                map[row][column] = queue.poll();
            }
        }

        for (int row = map.length/2; row < map.length; row++) {
            for (int column = map[0].length/2; column < map[0].length; column++) {
                map[row][column] = queue.poll();
            }
        }

    }
}
