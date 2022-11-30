import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {


    static List<Integer> answer = new ArrayList<>();
    static List<Integer> ar = new ArrayList<>();
    private static int N;
    private static int M;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ar.add(sc.nextInt());
            }
        }

        for (int k = 0; k < ar.size(); k++) {
            List<Integer> tetro = new ArrayList<>();
            solve(k + 1, tetro);
        }

        int maxAnswer = Collections.max(answer);

        System.out.println(maxAnswer);
        sc.close();
    }

    private static void solve(int start, List<Integer> tetro) { // 시작지점에서 오른쪽과 아래만 찾는다.

        tetro.add(start);

        if (tetro.size() == 4) { // 배열 복사해서 넘겨줘야할듯.
            answer.add(sumTetro(tetro));
            return;
        }

        if (validate(start + 1, 0, tetro)) {// 우측한칸 가능 ?
            List<Integer> copyTetro = new ArrayList<>(tetro);
            solve(start + 1, copyTetro);
        }
        if (validate(start + M, 1, tetro)) {// 아래한칸 가능 ?
            List<Integer> copyTetro = new ArrayList<>(tetro);
            solve(start + M, copyTetro);
        }
        if (validate(start - 1, 2, tetro)) {// 좌측한칸 가능 ?
            List<Integer> copyTetro = new ArrayList<>(tetro);
            solve(start - 1, copyTetro);
        }

        if (tetro.size() == 3 && validateStick(tetro)) { // 3연속 1자로 배치될때, 예외 검증

            if(tetro.get(1)-tetro.get(0) == 1){
                if (validate(tetro.get(1)+ M , 1, tetro)) {// ㅜ
                    List<Integer> copyTetro = new ArrayList<>(tetro);
                    solve(tetro.get(1) + M , copyTetro);
                }
                if (validate(tetro.get(1)- M , 3, tetro)) {// ㅗ
                    List<Integer> copyTetro = new ArrayList<>(tetro);
                    solve(tetro.get(1) - M , copyTetro);
                }


            }


            else{ // 세로

                if (validate(tetro.get(1)+1, 0, tetro)) {// ㅏ
                    List<Integer> copyTetro = new ArrayList<>(tetro);
                    solve(tetro.get(1)+1, copyTetro);
                }
                if (validate(tetro.get(1)-1, 2, tetro)) {// ㅓ
                    List<Integer> copyTetro = new ArrayList<>(tetro);
                    solve(tetro.get(1) -1, copyTetro);
                }

            }

        }
    }

    private static boolean validateStick(List<Integer> tetro) { // tetro의 값들 3개가 1자 배치일때,
        if(tetro.get(1)-tetro.get(0) == tetro.get(2)- tetro.get(1)){
            return true;
        }

        return false;
    }

    private static Integer sumTetro(List<Integer> tetro) {
        int sum = 0;
        for (int i : tetro) {
            sum += ar.get(i - 1);

        }

        return sum;
    }
    //1 2 3 4 5
    //6 7 8 9 10
    //11 12 13 14 15


    private static boolean validate(int index, int direction, List<Integer> tetro) { // direction 0 오른쪽 /1 아래/ 2왼쪽

        if (direction == 0 && index % M == 1) { // 오른쪽으로 한 칸 갔는데, 우측 끝일때.
            return false;
        }
        if (direction == 1 && index > N * M) { // 아래
            return false;
        }
        if (direction == 2 && index % M == 0) { // 왼쪽
            return false;
        }

        if (direction == 3 && index < 1) { // 위쪽
            return false;
        }


        if (tetro.contains(index)) {
            return false;
        }

        return true;
    }
}
