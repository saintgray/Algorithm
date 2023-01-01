package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//    1463 : 1로 만들기
//    ref url : https://www.acmicpc.net/problem/1463
public class 일로만들기 {
    static int X;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(in.readLine());
        int[] calc = new int[X];
        findShortestCalc(calc);
        System.out.println(calc[0]-1);
        in.close();
    }

    static void findShortestCalc(int[] calc) {

        Queue<Integer> queue = new LinkedList<>();
        calc[X - 1] = 1;
        queue.add(X);
        while (!queue.isEmpty()) {
            int n = queue.poll();
            if (n % 3 == 0 && (n / 3) - 1 >= 0 && calc[(n / 3) - 1] == 0) {
                calc[(n / 3) - 1] = calc[n - 1] + 1;
                queue.add(n / 3);
            }
            if (n % 2 == 0 && (n / 2) - 1 >= 0 && calc[(n / 2) - 1] == 0) {
                calc[(n / 2) - 1] = calc[n - 1] + 1;
                queue.add(n / 2);
            }
            if (n - 2 >= 0 && calc[n - 2] == 0) {
                calc[(n - 2)] = calc[n - 1] + 1;
                queue.add(n - 1);
            }
            if (calc[0] != 0)
                return;
        }
    }
}
