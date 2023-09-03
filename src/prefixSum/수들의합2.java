package prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    2003 : 수들의 합 2
//    ref url : https://www.acmicpc.net/problem/2003
public class 수들의합2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        int[] nums = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sum = new int[N];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        int result = 0;
        int p1 = 0;
        int p2 = 0;
        // idx 부터 시작해서 누적합이 M 이되는 모든 경우의 수를 따니자
        result += sum[p1] == M ? 1 : 0;
        while (true){
            while(p2 + 1 < sum.length) {
                int _rangeSum = sum[p2+1] - (p1 == 0 ? 0 : sum[p1 - 1]);
                result += (_rangeSum == M) ? 1 : 0;
                p2++;
                if (_rangeSum > M)
                    break;
            }
            while(p1 + 1 <= p2) {
                int _rangeSum = sum[p2] - sum[p1];
                result += (_rangeSum == M) ? 1 : 0;
                p1++;
                if (_rangeSum < M)
                    break;
            }
            if (p1 == sum.length - 1 && p2 == sum.length - 1)
                break;
        }
        System.out.println(result);
    }
}
