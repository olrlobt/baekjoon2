import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 버퍼리더

        int N = Integer.parseInt(br.readLine()); // N을 입력받는다.
        Stack<Task> schedules = new Stack<>();  // 일들을 Stack에 넣어 관리한다.
        int totalScore = 0;  // 전체 점수를 저장할 totalScore를 선언한다.

        for (int i = 0; i < N; i++) {  // N분기 동안 반복한다.
            StringTokenizer st = new StringTokenizer(br.readLine()); // 입력을 나누기 위해 StringTokenizer로 입력받는다.
            int kind = Integer.parseInt(st.nextToken());  // 업무가 주어지는지 판별한다.

            if (kind != 0) { // 업무가 주어진다면
                int A = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                schedules.push(new Task(A, T)); // 업무를 추가한다.
            }

            if(!schedules.isEmpty()){  // 업무가 있다면,
                schedules.peek().time -= 1; // 맨 나중에 들어온 업무를 1만큼 진행하고

                if(schedules.peek().time == 0){ // 업무가 끝났다면
                    totalScore += schedules.pop().score; // 점수를 더해준다.
                }
            }
        }
        System.out.println(totalScore); // 결과 출력
    }

    private static class Task{  // Task 객체를 만들어 score 와 time 을 저장한다.

        int score;
        int time;

        public Task(int score, int time) {
            this.score = score;
            this.time = time;
        }
    }
}