package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    1517 : 버블 소트
//    ref url : https://www.acmicpc.net/problem/1517
public class 버블소트 {
    static long count = 0;
    static int[] sortedArray;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        sortedArray = new int[N];
        sort(0, N - 1, arr);
        System.out.println(count);
    }


    static void sort(int low, int high, int[] arr) {
        if (high - low < 1) {
            return;
        }
        int mid = (low + high) / 2;
        sort(low, mid, arr);
        sort(mid + 1, high, arr);
        merge(low, high, arr);
    }

    static void merge(int low, int high, int[] arr) {

        int _idx = low;
        int mid = (low + high) / 2;
        int idx1 = low;
        int idx2 = mid + 1;
        while(idx1 <= mid && idx2 <= high) {
            int leftVal = arr[idx1];
            int rightVal = arr[idx2];
            if (leftVal > rightVal) {
                // Bubble Sort 의 SWAP 수 += idx2 - _idx 인 이유
                //
                // Bubble Sort 는 SWAP 1번당 inversion 을 1씩 감소시키며, inversion 이 0 이 될 때 종료된다.
                // inversion : i < j && a[i] > a[j] 를 만족하는 쌍의 수
                // ref : https://www.geeksforgeeks.org/inversion-count-in-array-using-merge-sort/
                //
                // 결과적으로 두 구간만큼 SWAP 이 일어나야 inversion 에 해당하는 쌍의 값 index 위치가 바뀌기 때문이다.
                count += idx2 - _idx;
                sortedArray[_idx++] = rightVal;
                idx2++;
            } else {
                sortedArray[_idx++] = leftVal;
                idx1++;
            }
        }

        while(idx1 <= mid) {
            sortedArray[_idx++] = arr[idx1++];
        }
        while(idx2 <= high) {
            sortedArray[_idx++] = arr[idx2++];
        }

        for (int i = low; i <= high; i++) {
            arr[i] = sortedArray[i];
        }
    }
}
