package prefixSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    17390 : 이건 꼭 풀어야 해!
//    ref url : https://www.acmicpc.net/problem/17390
public class 이건꼭풀어야해 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int Q = params[1];
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        int[] sum = new int[N];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i-1] + arr[i];
        }
        while(Q-- > 0) {
            String[] param = in.readLine().split(" ");
            int idx1 = Integer.parseInt(param[0]) - 1;
            int idx2 = Integer.parseInt(param[1]) - 1;
            out.write(String.valueOf(sum[idx2] - (idx1 - 1 < 0 ? 0 : sum[idx1 - 1])));
            out.newLine();
        }
        out.flush();
    }
}
