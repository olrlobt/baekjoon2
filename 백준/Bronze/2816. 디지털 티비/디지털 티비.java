import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];
        for(int i = 0 ; i<n; i++) {
        	arr[i] = sc.next();
        }
        
       	for(int i = 0 ;i<n; i++) {
       		if(arr[i].equals("KBS1")) {
       			String tmp = "";
       			for(int j = i; j>0; j--) {
        			System.out.print(4);
        			tmp = arr[j-1];
        			arr[j-1] = arr[j];
        			arr[j] = tmp;
       			}
       			break;
        	}
        	else 
        		System.out.print(1);
       	}
       	
       	if(arr[1].equals("KBS2"))
        	System.exit(0);
        	
       	for(int i = 0 ;i<n; i++) {
       		if(arr[i].equals("KBS2")) {
       			String tmp = "";
       			for(int j = i; j>1; j--) {
       				System.out.print(4);
       				tmp = arr[j-1];
       				arr[j-1] = arr[j];
        			arr[j] = tmp;
        		}
        		break;
       		}
       		else
       			System.out.print(1);
        }
    }
}