
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int [] Nmap = new int[N];
        for (int num = 0; num < N; num++) {
            Nmap[num] = sc.nextInt();
        }
        Arrays.sort(Nmap);

        int M = sc.nextInt();
        int [] Mmap = new int[M];
        for (int num = 0; num < M; num++) {
            Mmap[num] = sc.nextInt();
        }

        solve(Nmap,Mmap);
    }

    public static void solve(int [] Nmap,int [] Mmap){

        for(int i :Mmap){
            int find = 0;
            int low = 0 ;
            int high = Nmap.length-1;


            while(low <= high){
                int mid = (high+low)/2 ;


                if(Nmap[mid] == i){
                    find = 1;
                    break;
                }

                else if(Nmap[mid] > i){
                    high = mid-1;
                }else{
                    low = mid+1;
                }

            }
            System.out.println(find);
        }
    }
}
