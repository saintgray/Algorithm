package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 수고르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(in.readLine());
        Arrays.sort(arr);

        // M 의 최대값은 20억이므로 result 초기값 > M
        int result = Integer.MAX_VALUE;
        int fi = 0;
        int li = 0;
        while (li < N) {
            int fn = arr[fi];
            int ln = arr[li];
            int diff = ln - fn;
            if (diff < M) {
                li++;
            } else if (diff > M) {
                result = Math.min(result, diff);
                fi++;
            } else {
                result = M;
                break;
            }
        }
        System.out.println(result);
    }
}

