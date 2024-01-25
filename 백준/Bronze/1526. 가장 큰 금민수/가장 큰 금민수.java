import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int result;
	
	static void DFS(int number, int N) {
		if (number > N) return;
		result = Math.max(result, number);
		DFS(number*10 + 4 , N);
		DFS(number*10 + 7 , N);
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		DFS(4,N);
		DFS(7,N);
		
		System.out.println(result);
	}
}