package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

//    11478 : 서로 다른 부분 문자열의 개수
//    ref url : https://www.acmicpc.net/problem/11478
public class 서로다른부분문자열의갯수 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();

        Set<String> set = new HashSet<>();
        int size = 0;
        while(true){
            for (int i = 0; i < input.length(); i++) {
                if (i + size < input.length()) {
                    for (int j = i; j <= i + size; j++)
                        set.add(input.substring(i, i + size + 1));
                }
            }
            if(size++ == input.length())
                break;
        }
        System.out.println(set.size());
    }
}
