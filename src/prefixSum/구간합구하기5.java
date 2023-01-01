package prefixSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    11660 : 구간합구하기5
//    ref url : https://www.acmicpc.net/problem/11660
public class 구간합구하기5 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < arr.length; j++)
                map[i][j] = j == 0 ? arr[j] : map[i][j - 1] + arr[j];
        }
        for (int i = 0; i < M; i++) {
            int[] coordPair = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x1 = coordPair[0];
            int y1 = coordPair[1];
            int x2 = coordPair[2];
            int y2 = coordPair[3];
            // x1 행의 y2 열부터 x2 행의 y2 열까지의 누적합
            int sum = 0;
            for (int x = x1; x <= x2; x++)
                sum += map[x - 1][y2 - 1];
            // x1 행의 y1 -1 열부터 x2 행의 y1-1 열의 누적합 차감
            int diff = 0;
            if (y1 - 1 > 0)
                for (int x = x1; x <= x2; x++)
                    diff += map[x - 1][y1 - 2];
            out.write((sum - diff) + "\n");
        }
        out.flush();
    }
}
