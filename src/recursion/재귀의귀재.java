package recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//    25501 : 재귀의 귀재
//    ref url : https://www.acmicpc.net/problem/25501
public class 재귀의귀재 {

    static int time = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(in.readLine());
        for (int i = 0; i < T; i++) {
            int result = isPalindrome(in.readLine());
            out.write(String.format("%s %s\n", result, time));
            time = 0;
        }
        out.flush();
    }
    public static int isPalindrome(String s){
        return recursion(s, 0, s.length()-1);
    }
    public static int recursion(String s, int l, int r){
        time++;
        if(l >= r) return 1;
        else if(s.charAt(l) != s.charAt(r)) return 0;
        else return recursion(s, l+1, r-1);
    }
}
