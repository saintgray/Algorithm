package recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//    10830 : 행렬제곱
//    ref url : https://www.acmicpc.net/problem/10830
public class 행렬제곱 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        long[] params = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        int N = (int) params[0];
        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                A[i][j] = row[j] % 1000;
            }
        }
        Map<Long, int[][]> matrixMap = new HashMap<>();
        long pow = params[1];
        int[][] E = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                E[i][j] = i== j ? 1 : 0;
            }
        }
        matrixMap.put(0L, E);
        matrixMap.put(1L, A);
        int[][] result = get(matrixMap, A, pow);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                out.write(String.format("%s ", result[i][j]));
            }
            out.newLine();
        }
        out.flush();
    }

    public static int[][] get(Map<Long, int[][]> matrixMap, int[][] A, long pow) {
        int[][] modMatrix = matrixMap.get(pow);
        if (modMatrix == null) {
            long quotient = pow / 2;
            long rest = pow % 2 == 1 ? (pow / 2) + 1 : quotient;
            modMatrix = multiply(get(matrixMap, A, quotient), get(matrixMap, A, rest));
            matrixMap.put(pow, modMatrix);
        }
        return modMatrix;
    }

    public static int[][] multiply(int[][] modMtx1, int[][] modMtx2) {
        int N = modMtx1.length;
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int val = 0;
                for (int k = 0; k < N; k++) {
                    val += modMtx1[i][k] * modMtx2[k][j];
                }
                result[i][j] = val % 1000;
            }
        }
        return result;
    }
}
