package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    1946 : 우수 마을
//    ref url : https://www.acmicpc.net/problem/1946
//    tag : dynamic programming in tree
public class 우수마을 {
    static int N;
    static V[] vertex;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        vertex = new V[N + 1];
        for (int i = 0; i < N; i++) {
            vertex[i + 1] = new V();
            vertex[i + 1].connect = new ArrayList<>();
        }
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++) {
            vertex[i + 1].n = arr[i];
        }
        for (int i = 0; i < N - 1; i++) {
            arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            vertex[arr[0]].connect.add(vertex[arr[1]]);
            vertex[arr[1]].connect.add(vertex[arr[0]]);
        }
        track(vertex[1]);
        System.out.println(Math.max(vertex[1].dp1, vertex[1].dp2));
    }

    static void track(V v) {
        v.checked = true;
        int _dp1 = 0;
        int _dp2 = 0;
        boolean isReef = true;
        for (V _v : v.connect) {
            if(!_v.checked) {
                isReef = false;
                track(_v);
                _dp1 += Math.max(_v.dp1, _v.dp2);
                _dp2 += _v.dp2;
            }
        }
        if (isReef) {
            v.dp1 = v.n;
            v.dp2 = 0;
        } else {
            v.dp1 = _dp2 + v.n;
            v.dp2 = _dp1;
        }
    }

    static class V {
        int n;
        int dp1;    // 이 마을이 우수마을로 선정되었을 때 우수마을 최대 주민 수
        int dp2;    // 이 마을이 우수마을이 아닐 때 우수마을 최대 주민 수
        List<V> connect;
        boolean checked;
    }
}
