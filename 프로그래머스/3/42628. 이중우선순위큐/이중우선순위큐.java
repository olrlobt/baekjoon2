import java.util.TreeSet;

class Solution {
    public static int[] solution(String[] operations) {
        int[] answer = new int[2];

        TreeSet<Integer> treeSet = new TreeSet<>();
        for (String operation : operations) {
            String[] s = operation.split(" ");
            if (s[0].equals("I")) { // Insert
                treeSet.add(Integer.parseInt(s[1]));
            } else if (s[0].equals("D")) { // Delete
                if (s[1].equals("-1")) { // Min
                    treeSet.pollFirst();
                }else{ // Max
                    treeSet.pollLast();
                }
            }
        }

        if(!treeSet.isEmpty()){
            answer[0] = treeSet.last();
            answer[1] = treeSet.first();
        }
        return answer;
    }
}