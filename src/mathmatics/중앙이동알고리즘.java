package mathmatics;

import java.io.IOException;
import java.util.Scanner;

// 2903 : 중앙 이동 알고리즘
// 문제 참조 : https://www.acmicpc.net/problem/2903
public class 중앙이동알고리즘 {
    public static void main(String[] args) throws IOException {
        //(2^N+1)^2
        System.out.println((int)Math.pow(Math.pow(2, Integer.parseInt(new Scanner(System.in).next())) + 1, 2));
    }
}
