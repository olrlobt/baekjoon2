import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    static final String[] OPERATIONS = new String[]{"+", "-", "*"};
    static long[] numbers;
    static String[] operation;
    static long result = -1;
    
    public long solution(String expression) {
        long answer = 0;

        numbers = Arrays.stream(expression.split("[\\+\\-\\*]"))
                .mapToLong(Long::parseLong)
                .toArray();
        operation = expression.split("[0-9]+");

        Queue<String> queOper = new ArrayDeque<>();
        Arrays.stream(operation).forEach(queOper::offer);
        queOper.poll();
        Queue<Long> queNum = new ArrayDeque<>();
        Arrays.stream(numbers).forEach(queNum::offer);

        solve(queOper, queNum, 0);
        return result;
    }

    private void solve(Queue<String> queOper, Queue<Long> queNum, int bit) {

        if (bit == 7) {
            result = Math.max(result, Math.abs(queNum.peek()));
            return;
        }

        int size = queOper.size();
        long curNum;

        Queue<String> copyOper = new ArrayDeque<>(queOper);
        Queue<Long> copyNum = new ArrayDeque<>(queNum);

        if ((bit & (1)) == 0) { // plus
            Queue<String> tempOper = new ArrayDeque<>();
            Queue<Long> tempNum = new ArrayDeque<>();
            bit |= (1);
            curNum = copyNum.poll();
            for (int i = 0; i < size; i++) {
                String oper = copyOper.poll();

                if (!oper.equals(OPERATIONS[0])) {
                    tempOper.add(oper);
                    tempNum.add(curNum);
                    curNum = copyNum.poll();
                    continue;
                }
                curNum += copyNum.poll();
            }
            tempNum.add(curNum);
            solve(tempOper, tempNum, bit);

            bit ^= (1);
        }

        copyOper.addAll(queOper);
        copyNum.addAll(queNum);

        if ((bit & (1 << 1)) == 0) { // minus
            Queue<String> tempOper = new ArrayDeque<>();
            Queue<Long> tempNum = new ArrayDeque<>();
            bit |= (1 << 1);
            curNum = copyNum.poll();

            for (int i = 0; i < size; i++) {
                String oper = copyOper.poll();
                if (!oper.equals(OPERATIONS[1])) {
                    tempOper.add(oper);
                    tempNum.add(curNum);
                    curNum = copyNum.poll();

                    continue;
                }
                curNum -= copyNum.poll();

            }
            tempNum.add(curNum);
            solve(tempOper, tempNum, bit);
            bit ^= (1 << 1);

        }

        copyOper.addAll(queOper);
        copyNum.addAll(queNum);

        if ((bit & (1 << 2)) == 0) { // multi
            Queue<String> tempOper = new ArrayDeque<>();
            Queue<Long> tempNum = new ArrayDeque<>();
            bit |= (1 << 2);
            curNum = copyNum.poll();

            for (int i = 0; i < size; i++) {
                String oper = copyOper.poll();
                if (!oper.equals(OPERATIONS[2])) {
                    tempOper.add(oper);
                    tempNum.add(curNum);
                    curNum = copyNum.poll();

                    continue;
                }
                curNum *= copyNum.poll();

            }
            tempNum.add(curNum);
            solve(tempOper, tempNum, bit);
            bit ^= (1 << 2);
        }
    }
}