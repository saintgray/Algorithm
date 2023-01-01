package prefixSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    1806 : 부분합
//    ref url : https://www.acmicpc.net/problem/1806
public class 부분합 {

    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int S = params[1];
        int[] sumArr = new int[N];
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 누적합
        for (int i = 0; i < N; i++)
            sumArr[i] = i == 0 ? arr[0] : sumArr[i - 1] + arr[i];
        out.write(sumArr[sumArr.length-1] < S ? "0" : String.valueOf(solve(sumArr, S)));
        out.flush();
    }

    static int solve(int[] sumArr, int S){
        int start = 0;
        int end = 0;
        while (start < sumArr.length) {
            if (sumArr[start] - (end > 0 ? sumArr[end - 1] : 0) < S){
                start++;
            }else{
                while (sumArr[start] - (end > 0 ? sumArr[end - 1] : 0) >= S) {
                    end++;
                }
                result = Math.min(result, (start - end + 2));
            }
        }
        return result;
    }
}
