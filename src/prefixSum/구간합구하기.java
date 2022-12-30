package prefixSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    11659 : 구간합구하기
//    ref url : https://www.acmicpc.net/problem/11659
public class 구간합구하기 {
    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        int[] arr  = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sumArr = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++)
            sumArr[i] = (sum += arr[i]);
        for (int i = 0; i < M; i++) {
            int[] range = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            out.write(String.valueOf(sumArr[range[1] - 1] - (range[0] - 2 < 0 ? 0 : sumArr[range[0] - 2])));
            out.newLine();
        }
        out.flush();
    }
}
