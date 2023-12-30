package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    1940 : 주몽
//    ref url : https://www.acmicpc.net/problem/1940
public class 주몽 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());
        int[] arr = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        int fi = 0;
        int li = N-1;

        int result = 0;
        while (fi < li) {
            int compareResult = Integer.compare(arr[fi] + arr[li], M);
            if (compareResult == 0) {
                result++;
                fi++;
                li--;
            } else {
                fi += compareResult == 1 ? 0 : 1;
                li -= compareResult == 1 ? 1 : 0;
            }
        }
        System.out.println(result);
    }
}
