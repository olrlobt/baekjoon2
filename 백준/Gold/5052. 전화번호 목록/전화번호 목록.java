
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    static ArrayList<ArrayList<String>> phone;
    static int num;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int testCase = 0; testCase < N; testCase++) {
            num = sc.nextInt();
            phone = new ArrayList<>();

            for (int i = 0; i <= 9; i++) {
                phone.add(new ArrayList<>());
            }

            for (int number = 0; number < num; number++) {
                String input = sc.next();
                phone.get(input.charAt(0) - '0').add(input);
            }

            if (solve()) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }

    public static boolean solve() {

        for (int i = 0; i <= 9; i++) {
            if (phone.get(i).size() <= 1) {
                continue;
            }

            Collections.sort(phone.get(i), new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() - o2.length();
                }
            });

            for (int j = 0; j < phone.get(i).size(); j++) {
                String curPhone = phone.get(i).get(j);

                for (int k = j; k < phone.get(i).size(); k++) {
                    if (curPhone.length() >= phone.get(i).get(k).length()) {
                        continue;
                    }

                    if (curPhone.equals(phone.get(i).get(k).substring(0, curPhone.length()))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
