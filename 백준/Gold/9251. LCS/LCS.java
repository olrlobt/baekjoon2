
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static String str1;
    static String str2;
    static List<Integer> dp = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        str1 = sc.nextLine();
        str2 = sc.nextLine();

        solve();
    }

    public static void solve() {
        dp.add(0);
        for (int i = 0; i < str1.length(); i++) {
            dp.add(0);
        }

        for (int i = 0; i < str2.length(); i++) {
            List<Integer> newDp = new ArrayList<>(dp);
            for (int j = 0; j < str1.length(); j++) {

                if (str2.charAt(i) == str1.charAt(j)) {
                    dp.set(j + 1, newDp.get(j) + 1);
                } else {
                    dp.set(j + 1, Math.max(dp.get(j), newDp.get(j + 1)));
                }
            }
        }
        System.out.println(dp.get(str1.length()));
    }
}

