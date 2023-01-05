package prefixSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    16507 : 어두운건 무서워
//    ref url : https://www.acmicpc.net/problem/16507
public class 어두운건무서워 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int X = params[0];
        int Y = params[1];
        int tc = params[2];

        int[][] room = new int[X][Y];
        int[][] sumArr = new int[X][Y];
        for (int i = 0; i < X; i++) {
            room[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < Y; j++)
                sumArr[i][j] = sumArr[i][j] = j == 0 ? room[i][j] : sumArr[i][j - 1] + room[i][j];
        }
        for (int caseCnt = 0; caseCnt < tc; caseCnt++) {
            int[] coordParams = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x1 = coordParams[0];
            int y1 = coordParams[1];
            int x2 = coordParams[2];
            int y2 = coordParams[3];
            int prefixSum = 0;
            int subtractSum = 0;
            for (int i = x1 - 1; i < x2; i++) {
                prefixSum += sumArr[i][y2 - 1];
                subtractSum += y1 == 1 ? 0 : sumArr[i][y1 - 2];
            }
            int result = prefixSum - subtractSum;
            int divide = (x2 - x1 + 1) * (y2 - y1 +1);
            out.write(String.valueOf(result / (divide)).concat("\n"));
        }
        out.flush();
    }
}
