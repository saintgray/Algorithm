package bruteForceAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    1543 : 문서검색
//    ref url : https://www.acmicpc.net/problem/1543

public class 문서검색 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] word = in.readLine().toCharArray();
        char[] findWord = in.readLine().toCharArray();
        int findIndex = 0;
        int result = 0;
        for (int i =0; i< word.length; i++) {
            if (word[i] == findWord[findIndex]) {
                findIndex++;
            } else {
                if(findIndex != 0){
                    i -= findIndex;
                    findIndex=0;
                }
            }
            if (findIndex == findWord.length) {
                findIndex = 0;
                result ++;
            }
        }
        System.out.println(result);
        in.close();
    }
}
