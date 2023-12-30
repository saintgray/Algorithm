package mathmatics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    2740 : 행렬 곱셈
//    ref url : https://www.acmicpc.net/problem/2740
public class 행렬곱셈 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] paramA = in.readLine().split(" ");
        int[][] A = new int[Integer.parseInt(paramA[0])][Integer.parseInt(paramA[1])];
        for (int i = 0; i < A.length; i++) {
            A[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        String[] paramB = in.readLine().split(" ");
        int[][] B = new int[Integer.parseInt(paramB[0])][Integer.parseInt(paramB[1])];
        for (int i = 0; i < B.length; i++) {
            B[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int N = A.length;
        int M = B.length;
        int K = B[0].length;
        int[][] C = new int[N][K];
        for (int n = 0; n < N; n++) {
            for (int k = 0; k < K; k++) {
                int val = 0;
                for (int i = 0; i < M; i++) {
                    val += A[n][i] * B[i][k];
                }
                C[n][k] = val;
            }
        }

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[i].length; j++) {
                out.write(String.format("%s ",C[i][j]));
            }
            out.newLine();
        }
        out.flush();
    }
}
