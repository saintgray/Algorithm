package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    2847 : 게임을만든동준이
//    ref url : https://www.acmicpc.net/problem/2847
public class 게임을만든동준이 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(in.readLine());
        }
        int max = arr[arr.length - 1];
        int result = 0;
        for (int i = arr.length - 2; i >= 0; i--) {
            int score = arr[i];
            if (score >= max) {
                // score - x = max -1
                int x = score - max + 1;
                result += x;
                arr[i] = score - x;
            }
            max = arr[i];
        }
        // System.out.println(Arrays.toString(arr));
        System.out.println(result);
    }
}
