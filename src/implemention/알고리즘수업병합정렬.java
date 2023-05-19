package implemention;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 알고리즘수업병합정렬 {

    static int count = 0;
    static int K = 0;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        K = params[1];
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        merge_sort(0, N - 1, arr);
        System.out.println(result);
    }

    static void merge_sort(int low, int high, int[] arr) {
        if (low < high) {
            int mid = (low + high) / 2;
            merge_sort(low, mid, arr);
            merge_sort(mid + 1, high, arr);
            merge(low, high, arr);
        }
    }

    static void merge(int p, int r, int[] arr) {
        int q = (p + r) / 2;
        // 정렬된 두 배열을 합병 정렬시
        int i = p;
        int j = q + 1;

        int[] temp = new int[r - p + 1];
        int t = 0;
        while (i <= q && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= q)
            temp[t++] = arr[i++];
        while (j <= r)
            temp[t++] = arr[j++];
        i = p;
        t = 0;
        while (i <= r) {
            count++;
            int num = temp[t++];
            arr[i++] = num;
            if (count == K)
                result = num;
        }
    }
}
