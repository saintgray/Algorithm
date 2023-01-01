package backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//	15649 : N 과 M(1)
//	ref url : https://www.acmicpc.net/problem/15649
public class N과M_1 {

    static int N = 0;
    static int M = 0;
    static boolean[] visited;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = params[0];
        M = params[1];

        visited = new boolean[N];
        arr = new int[M];
        dfs(0, out);
        out.flush();
    }

    static void dfs(int depth, BufferedWriter out) throws IOException {

        if (depth == M) {
            for (int n : arr)
                out.write(n + " ");
            out.newLine();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = i + 1;
                dfs(depth + 1, out);
                visited[i] = false;
            }
        }
    }
}
