import java.util.Arrays;

class Solution {
    
    static final int[] PERCENT = {10, 20, 30, 40};
    static int[][] usersInfo;
    static int N;
    static int M;
    static int[] answer = new int[2];
    
     public static int[] solution(int[][] users, int[] emoticons) {
        N = users.length;
        M = emoticons.length;
        usersInfo = users;

        Emoticon[] emoticons1 = new Emoticon[M];
        for (int i = 0; i < M; i++) {
            emoticons1[i] = new Emoticon(emoticons[i], 0);
        }
        dfs(0, Arrays.copyOf(emoticons1, M));
        return answer;
    }

    private static void dfs(int idx, Emoticon[] emoticons) {
        if (idx == M) {
            check(emoticons);
            return;
        }

        for (int percent : PERCENT) {
            Emoticon[] copyOfEmoticons = new Emoticon[M];
            for (int i = 0; i < M; i++) {
                copyOfEmoticons[i] = emoticons[i].clone();
            }
            copyOfEmoticons[idx].percent = percent;
            copyOfEmoticons[idx].price = copyOfEmoticons[idx].price * (100 - percent) / 100;
            dfs(idx + 1, copyOfEmoticons);
        }
    }

    private static void check(Emoticon[] emoticons) {
        int emoticonPlus = 0;
        int totalPrice = 0;

        for (int[] users : usersInfo) {
            int sum = 0;

            for (Emoticon emoticon : emoticons) {
                if(emoticon.percent >= users[0]){
                    sum += emoticon.price;
                }
            }

            if (sum >= users[1]) {
                emoticonPlus++;
            }else{
                totalPrice += sum;
            }
        }
        if (emoticonPlus == answer[0]) {
            answer[1] = Math.max(answer[1], totalPrice);
        }else if(emoticonPlus > answer[0]){
            answer[0] = emoticonPlus;
            answer[1] = totalPrice;
        }
    }

    private static class Emoticon{
        int price;
        int percent;

        public Emoticon(int price, int percent) {
            this.price = price;
            this.percent = percent;
        }

        protected Emoticon clone() {
            return new Emoticon(price, percent);
        }
    }
}