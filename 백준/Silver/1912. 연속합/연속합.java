import java.util.*;

public class Main { // 1912. 연속합 35%

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();	
		int[] arr = new int[n];
		// Input
		for (int num = 0; num < n; num++) {			
			arr[num] = sc.nextInt();			
		}
		int [] dp = new int [n];
		dp[0] = arr[0];
		int max = dp[0];
		// Logic
		for(int i = 1; i<n; i++) { // i별 최대값을 저장.
			dp[i] = Math.max(arr[i], dp[i-1] + arr[i]); // i-1 최대값 + 다음값이랑 현재값 비교
			max = (dp[i] >= max ) ? dp[i] : max;
		}
		
		// Output
		System.out.println(max);

		sc.close();
	}// main
}// solution