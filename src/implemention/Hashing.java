package implemention;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    15829 : Hashing
//    ref url : https://www.acmicpc.net/problem/15829
public class Hashing {
    public static final int M = 1234567891;
    public static final int r = 31;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(in.readLine());
        int[] nums = in.readLine().chars().toArray();
        long[] mods = new long[L];
        mods[0] = 1; // 31 의 0승
        for (int i = 1; i < L; i++)
            mods[i] = (mods[i - 1] * 31) % M;

        long result = 0;
        for (int i = 0; i < L; i++)
            result += (mods[i] * (nums[i] - 96)) % M;
        System.out.println(result % M);
    }
}
