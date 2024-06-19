package prefixSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    1749 : 점수따먹기
//    ref url : https://www.acmicpc.net/problem/1749
public class 점수따먹기 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int M;
    static int[][] colSum;  // i행의 0번 ~ j 까지의 누적함
    static int[][] rowSum;  // j열의 0번 ~ i 까지의 누적함
    static int[][] diagnoalSum; // 0,0 에서 i,j 부분행렬의 총합
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = p[0];
        M = p[1];
        colSum = new int[N][M];
        rowSum = new int[M][N];
        diagnoalSum = new int[N][M];
        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                int prevColsum = j == 0 ? 0 : colSum[i][j-1];
                int prevRowSum = i == 0 ? 0 : rowSum[j][i-1];
                colSum[i][j] =  prevColsum + row[j];
                rowSum[j][i] =  prevRowSum + row[j];
                // 0,0 ~ i,j 부분행렬의 총합
                int prevDiagnoalSum = i == 0 && j == 0 ? 0 : i == 0 && j != 0 ? prevColsum : i != 0 && j == 0 ? prevRowSum : diagnoalSum[i - 1][j - 1];
                diagnoalSum[i][j] = !(i == 0 || j == 0) ? diagnoalSum[i - 1][j - 1] + colSum[i][j] + rowSum[j][i] - row[j] : prevDiagnoalSum + row[j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // i,j 에서 만들수 있는 총 부분행렬
                for (int ei = i; ei <= N-1 ; ei++) {
                    for (int ej = j; ej <= M-1; ej++) {
                        // i,j 에서 ei, ej 부분행렬의 총합은
                        // 0,0 ~ ei, ej 부분행렬의 총합에서
                        // (0,0 ~ ei, j-1 부분행렬의 총합) + (0,0 ~ i-1, ej 부분행렬의 총합) 을 빼고
                        // 중복되는 영역 (0,0 ~ ei-1, ej-1 부분행렬의 총합) 합을 더하면 된다 (위에서 두번 빼므로)
                        int a = diagnoalSum[ei][ej];
                        int b = j==0 ? 0 : diagnoalSum[ei][j-1];
                        int c = i==0 ? 0 : diagnoalSum[i-1][ej];
                        int d = i==0 || j==0 ? 0 : diagnoalSum[i-1][j-1];
                        max = Math.max(max, (int)(a - (b+c) + d));
                    }
                }
            }
        }
        System.out.println(max);
    }
}