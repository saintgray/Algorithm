package backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

//	15651 : N 과 M(3)
//	ref url : https://www.acmicpc.net/problem/15651
public class N과M_3 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int M;
    static int[] arr;
    static int[] tracking;

    public static void main(String[] args) throws IOException {
        int[] param = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = param[0];
        M = param[1];
        arr = new int[N];
        tracking = new int[M];
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }
        track(0, 0);
        out.flush();
    }

    static void track(int index, int depth) throws IOException {
        if(depth == M)  {
            out.write(String.format("%s\n", Arrays.stream(tracking).mapToObj(String::valueOf).collect(Collectors.joining(" "))));
            return;
        };
        for (int i = 0; i < N; i++) {
            tracking[depth] = arr[i];
            track(i, depth + 1);
        }
    }
}

