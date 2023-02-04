package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//    12605 : 단어순서 뒤집기
//    ref url : https://www.acmicpc.net/problem/12605
public class 단어순서뒤집기 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(in.readLine());
        int tc = 0;
        while (tc++ < N) {
            out.write(String.format("Case #%d: ", tc));
            String[] arr = in.readLine().split(" ");
            for (int i = arr.length - 1; i >= 0; i--)
                out.write(String.format("%s ", arr[i]));
            out.newLine();
        }
        out.flush();
    }
}
