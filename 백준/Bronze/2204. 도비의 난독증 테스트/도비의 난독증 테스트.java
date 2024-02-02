import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
        public static void main(String [] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            while(true){
                String n = br.readLine();
                if(n.equals("0")){
                    break;
                }
                List<String> wordList = new ArrayList<>();
                for(int i=0; i < Integer.parseInt(n); i++){
                    wordList.add(br.readLine());
                }
                wordList.sort(String::compareToIgnoreCase);
                System.out.println(wordList.get(0));
            }
            br.close();
        }
    }