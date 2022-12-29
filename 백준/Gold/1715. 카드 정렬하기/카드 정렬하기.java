import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static int sum = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        List<Integer> decks = new ArrayList<>();

        for (int time = 0; time < N; time++) {
            decks.add(sc.nextInt());
        }

        solve(decks);
        System.out.println(sum);
    }

    public static void solve(List<Integer> decks) {
        PriorityQueue<Integer> queDeck = new PriorityQueue<>(decks);
        sumDecks(queDeck);
    }


    private static void sumDecks(PriorityQueue<Integer> decks) {
        if(decks.size()==1){
            return;
        }

        int sumTwin = decks.poll() + decks.poll();
        sum += sumTwin;
        decks.add(sumTwin);

        sumDecks(decks);
    }
}
