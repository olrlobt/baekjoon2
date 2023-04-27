import java.util.*;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();

        Queue<Integer> Q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=n; i++) Q.offer(i);
        sb.append('<');

        while(Q.size()!=1){
            for(int i=1; i<k; i++) Q.offer(Q.poll());
            sb.append(Q.poll()).append(", ");
        }
        sb.append(Q.poll()).append('>');
        System.out.println(sb);
    }
}