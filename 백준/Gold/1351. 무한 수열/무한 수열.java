
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static Map<Long, Long> dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long N = sc.nextLong();
        long P = sc.nextLong();
        long Q = sc.nextLong();

        dp = new HashMap<>();
        dp.put(0L,1L);

//        dp[N] = dp[N/P] + dp[N/Q];

        System.out.println(solve(N, P, Q));
    }

    public static long solve(long N, long P, long Q) {

        if(dp.containsKey(N)){
            return dp.get(N);
        }

        long NP = (long) Math.floor(N/P);
        long NQ = (long) Math.floor(N/Q);

        if(!dp.containsKey(NP)){
            dp.put(NP, solve(NP, P , Q));
        }
        if(!dp.containsKey(NQ)){
            dp.put(NQ, solve(NQ, P , Q));
        }

        dp.put(N , dp.get(NP) + dp.get(NQ));

        return dp.get(N);
    }


}
