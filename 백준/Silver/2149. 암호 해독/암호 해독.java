import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String pw = br.readLine();
        String sentence = br.readLine();

        int len = sentence.length() / pw.length();

        HashMap<Integer, Character> sequence = new HashMap<>();

        for (int i = 0; i < pw.length(); i++) {
            sequence.put(i, pw.charAt(i));
        }

        List<Map.Entry<Integer, Character>> list = new ArrayList<>(sequence.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Character>>() {
            @Override
            public int compare(Map.Entry<Integer, Character> o1, Map.Entry<Integer, Character> o2) {
                return Character.compare(o1.getValue(), o2.getValue());
            }
        });

        LinkedHashMap<Integer, Character> sortedSequence = new LinkedHashMap<>();
        for (Map.Entry<Integer, Character> entry : list) {
            sortedSequence.put(entry.getKey(), entry.getValue());
        }

        List<Integer> keySets = new ArrayList<>(sortedSequence.keySet());

        HashMap<Integer, String> amo = new HashMap<>();



        int idx = 0;
        for(int i = 0; i < pw.length(); i++){
            int key = keySets.get(i);
            String subString = sentence.substring(idx * len, len * (idx + 1));

            amo.put(key, subString);
            idx++;
        }

        Collections.sort(keySets);


        int maxLength = amo.values().stream().mapToInt(String::length).max().orElse(0);

        for (int i = 0; i < maxLength; i++) {
            for (Integer key : amo.keySet()) {
                String value = amo.get(key);
                if (i < value.length()) {
                    bw.write(value.charAt(i));
                }
            }
        }

        br.close();
        bw.close();

    }
}