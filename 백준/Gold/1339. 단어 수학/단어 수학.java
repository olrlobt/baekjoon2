
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {

    private static Map<Character, Integer> variable = new HashMap<Character, Integer>();
    private static Map<Character, Integer> variablePoint = new HashMap<Character, Integer>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        List<String> variables = new ArrayList<>();

        for (int testCase = 0; testCase < N; testCase++) {
            variables.add(sc.next());
        }

        solve(variables);
    }

    public static void solve(List<String> variables) {
        setKindVariable(variables);
        setPoint(variables);
        setVariablePoint();
        replaceVariable(variables);
    }

    private static void replaceVariable(List<String> variables) {
        List<Integer> numberVariable = new ArrayList<>();

        for (String va : variables) {
            String sample = "";
            for (int i = 0; i < va.length(); i++) {

                sample += variable.get(va.charAt(i));

            }
            numberVariable.add(Integer.valueOf(sample));
        }

        System.out.println(sum((ArrayList<Integer>) numberVariable));


    }

    public static int sum(ArrayList<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }

    private static void setVariablePoint() {
        List<Entry<Character, Integer>> sortList = new ArrayList<Entry<Character, Integer>>(variable.entrySet());

        Collections.sort(sortList, new Comparator<Entry<Character, Integer>>() {
            @Override
            public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        int max=9;
        for(Entry<Character, Integer> entry : sortList){
            variable.put(entry.getKey(),max--);
        }
    }

    private static void setPoint(List<String> variables) {
        for (String va : variables) {
            for (int i = 0; i < va.length(); i++) {
                variable.put(va.charAt(i), (int) (variable.get(va.charAt(i)) + Math.pow(10, va.length() - i - 1)));
            }
        }
    }

    private static void setKindVariable(List<String> variables) {
        for (String va : variables) {
            for (int i = 0; i < va.length(); i++) {
                variable.put(va.charAt(i), 0);
                variablePoint.put(va.charAt(i), 0);
            }
        }
    }
}
