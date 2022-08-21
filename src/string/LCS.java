package string;

//    9251 : LCS
//
//
//    문제
//    LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때,
//    모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
//
//    예를 들어,
//    ACAYKP 와
//    CAPCAK의 LCS는
//    ACAK가 된다.
//
//    입력
//    첫째 줄과 둘째 줄에 두 문자열이 주어진다.
//    문자열은 알파벳 대문자로만 이루어져 있으며,
//    최대 1000글자로 이루어져 있다.
//
//    출력
//    첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를 출력한다.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] A = in.readLine().toCharArray();
        char[] B = in.readLine().toCharArray();

        int[][] lcs = new int[B.length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (B[j] == A[i]) {
                    lcs[j][i] = i==0 || j==0 ? 1 : lcs[j-1][i-1] + 1;
                } else {
                    lcs[j][i] = Math.max(j ==0 ? 0 : lcs[j - 1][i],  i ==0? 0 : lcs[j][i - 1]);
                }
            }
        }

        int lcsLen = 0;
        for(int i=0; i< B.length; i++){
            lcsLen = Math.max(lcs[i][A.length-1], lcsLen);
        }
        System.out.println(lcsLen);

        in.close();

    }
}
