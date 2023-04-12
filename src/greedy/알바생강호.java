package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 1785 : 알바생 강호
// ref url : https://www.acmicpc.net/problem/1785
public class 알바생강호 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[Integer.parseInt(in.readLine())];
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(in.readLine());
        arr = Arrays.stream(arr).sorted().toArray();

        long result = 0;
        for (int i = arr.length -1 ; i >= 0 ; i--) {
            int order = arr.length - i;
            int tip = arr[i] - (order -1);
            if(tip < 0)
                break;
            result += tip;
        }
        System.out.println(result);
    }
}
