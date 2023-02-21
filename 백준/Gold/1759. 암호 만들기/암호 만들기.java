
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static final String[] VOWEL = {"a", "i", "o", "e", "u"};
    static String[] passwordList;
    static int L;
    static int C;

    static ArrayList<String> answer = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        L = sc.nextInt();
        C = sc.nextInt();
        passwordList = new String[C];

        for (int alphabet = 0; alphabet < C; alphabet++) {
            passwordList[alphabet] = sc.next();
        }
        Arrays.sort(passwordList);

        for (int i = 0; i <= C - L; i++) {
            solve(new StringBuffer(passwordList[i]), i);
        }

        for(String ans : answer){
            System.out.println(ans);
        }
    }

    public static void solve(StringBuffer sb, int index) {
        if (sb.length() == L) {
            if (check(sb)) {
                answer.add(String.valueOf(sb));
            }
            return;
        }

        for (int i = index + 1; i < C; i++) { // L = 4 C =6
            if(L - sb.length() > C - i ){ // 남은 숫자 <
                continue;
            }
            solve(new StringBuffer(sb).append(passwordList[i]), i);
        }
    }

    private static boolean check(StringBuffer sb) {
        int count = 0;

        for(String vowel : VOWEL){
            if(String.valueOf(sb).contains(vowel)){
                count ++;
            }
        }

        if(count < 1 || count > L - 2){
            return false;
        }
        return true;
    }
}
