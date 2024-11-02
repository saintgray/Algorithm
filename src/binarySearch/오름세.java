package binarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//    3745 : 오름세
//    ref url : https://www.acmicpc.net/problem/3745
//    tag : longest increasing subsequence
public class 오름세 {
    static String N;
    static int lis;
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        while ((N = in.readLine()) != null) {
            if(N.trim().isEmpty()) {
                break;
            }
            lis = 0;
            int n = Integer.parseInt(N.trim());
            int[] arr = new int[n];
            int[] A = new int[n + 1];
            A[0] = 0;
            StringTokenizer st = new StringTokenizer(in.readLine().trim());
            int arrIndex = 0;
            while(st.hasMoreTokens()) {
                arr[arrIndex++] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < n; i++) {
                if(A[lis] < arr[i]) {
                    A[++lis] = arr[i];
                    continue;
                }
                A[find(A, arr[i], lis)] = arr[i];
            }
            out.write(String.valueOf(lis));
            out.newLine();
        }
        out.flush();
    }

    // A 수열의 0 ~ ed index 구간 내에 p 보다 같거나 큰 값을 가지는 index중 최소 index 반환
    static int find(int[] A, int p, int ed) {
        int start = 0;
        int end = ed;
        while(start < end) {
            int mid = (start + end) / 2;
            if(A[mid] >= p) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }
}