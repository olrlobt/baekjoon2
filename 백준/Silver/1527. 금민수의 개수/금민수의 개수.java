import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int a,b;
	static int answer;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		solve(0);
		System.out.println(answer);

	}
    
    public static void solve(long num) {
		
		if(num>b) return;
		if(num >=a && num<=b) answer++;

		solve(num*10+4);
		solve(num*10+7);
	}
}