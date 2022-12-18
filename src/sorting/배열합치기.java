package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    11728 : 배열 합치기
//    ref url : https://www.acmicpc.net/problem/11728
public class 배열합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        in.readLine();
        int[] A = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] B = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int p1 = 0;
        int p2 = 0;
        while (true) {
            try {
                if (A[p1] == B[p2]) {
                    out.write(String.format("%d %d ", A[p1], B[p2]));
                    p1++;
                    p2++;
                } else if (A[p1] > B[p2]) {
                    out.write(B[p2] + " ");
                    p2++;
                } else {
                    out.write(A[p1] + " ");
                    p1++;
                }
            } catch (IndexOutOfBoundsException e) {
                if (p1 >= A.length) {
                    for (int index = p2; index < B.length; index++)
                        out.write(B[index] + " ");
                } else {
                    for (int index = p1; index < A.length; index++)
                        out.write(A[index] + " ");
                }
                break;
            }
        }
        out.flush();
        in.close();
        out.close();
    }
}
