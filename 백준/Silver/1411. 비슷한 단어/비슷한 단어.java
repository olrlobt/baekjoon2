import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];
		
		for(int i=0;i<N;i++)
			words[i] = br.readLine();
		
		int cnt = 0;
		
		for(int i=0;i<N;i++) {		
			for(int j=i+1;j<N;j++) {		
				boolean flag = true;
				HashMap<Character,Character> map = new HashMap<>(); 
				for(int k=0;k<words[j].length();k++) {
					char origin = words[i].charAt(k); 
					char compare = words[j].charAt(k); 
					if(map.containsKey(origin)) {
						if(map.get(origin)!=compare) { 
							flag=false;
							break;
						}
					}
					else {
						Iterator<Character> keys = map.keySet().iterator();
						while(keys.hasNext()) {
							char key = keys.next();
							if(map.get(key)==compare) {
								flag=false;
								break;
							}
						}
						if(flag)
							map.put(origin, compare);
					}
				}
				if(flag)
					cnt++;
			}
		}
		System.out.println(cnt);
		
	}
}