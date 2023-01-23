package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    1041 : 주사위
//    ref url : https://www.acmicpc.net/problem/1041
public class 주사위 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] nums = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int A = nums[0];
        int B = nums[1];
        int C = nums[2];
        int D = nums[3];
        int E = nums[4];
        int F = nums[5];
        int max = Math.max(A, Math.max(B, Math.max(C, Math.max(D, Math.max(E, F)))));
        int min1 = Math.min(A, Math.min(B, Math.min(C, Math.min(D, Math.min(E, F)))));
        int min2 = Math.min(A + C, Math.min(A + E, Math.min(A + B, Math.min(A + D, Math.min(B + F, Math.min(E + F, Math.min(D + F, Math.min(C + F, Math.min(B + D, Math.min(D + E, Math.min(E + C, B + C)))))))))));
        int min3 = Math.min(A + B + C, Math.min(A + C + E, Math.min(A + E + D, Math.min(A + B + D, Math.min(F + B + D, Math.min(F + D + E, Math.min(F + B + C, F + E + C)))))));
        if (N == 1) {
            System.out.println(Arrays.stream(nums).sum() - max);
        } else {
            long calc1 = ((long) Math.pow(N - 2, 2)) * min1 * 5;
            long calc2 = (long) (N - 2) * min1 * 4;
            long calc3 = (min2 * (((long) (N - 2) * 8) + 4));
            long calc4 = (long) min3 * 4;
            System.out.println(calc1 + calc2 + calc3 + calc4);
        }
    }
//    N 개의 주사위를 생각해보면
//    1. 안쪽 = ((N-2)*(N-2) * 주사위 눈 최소값)*5
//    2. 바닥에 붙어있는 모서리면 : (N-2)*주사위 눈 최소값*4
//    3. 바닥에 붙어있는 꼭지점 과 바닥에 붙어있는 모서리면을 제외한 모든 모서리면 :
//    MIN2 =  A+C, A+E, A+B, A+D, B+F, E+F, D+F, C+F, B+D, D+E, E+C, B+C  중 최소값
//      => MIN2 * (((N-2) * 8) + 4)
//    4. 맨 윗면 꼭지점 :
//    MIN3 = A+B+C, A+C+E, A+E+D, A+D+B, F+B+D, F+D+E, F+B+C, F+E+C 중 최소값
//       => MIN3 * 4
//    최소값 = 1 + 2 + 3 + 4;
}
