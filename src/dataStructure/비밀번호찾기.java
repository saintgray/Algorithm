package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//	17291 : 비밀번호 찾기
//	ref url : https://www.acmicpc.net/problem/17291
public class 비밀번호찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] arr = in.readLine().split(" ");
            map.put(arr[0], arr[1]);
        }
        for (int i = 0; i < M; i++)
            out.write(map.get(in.readLine()) + "\n");
        out.flush();
    }
}
