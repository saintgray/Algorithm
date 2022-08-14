package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    문제
//    땅 위에 달팽이가 있다. 이 달팽이는 높이가 V미터인 나무 막대를 올라갈 것이다.
//    달팽이는 낮에 A미터 올라갈 수 있다.
//    하지만, 밤에 잠을 자는 동안 B미터 미끄러진다.
//    또, 정상에 올라간 후에는 미끄러지지 않는다.
//    달팽이가 나무 막대를 모두 올라가려면, 며칠이 걸리는지 구하는 프로그램을 작성하시오.
//
//    입력
//    첫째 줄에 세 정수 A, B, V가 공백으로 구분되어서 주어진다. (1 ≤ B < A ≤ V ≤ 1,000,000,000)
//
//    출력
//    첫째 줄에 달팽이가 나무 막대를 모두 올라가는데 며칠이 걸리는지 출력한다.
public class 달팽이는올라가고싶다 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long[] params = Arrays.stream(in.readLine().split(" ")).mapToLong(e -> Long.parseLong(e)).toArray();
        long A = params[0];
        long B = params[1];
        long V = params[2];
        // A + (A-B)N >= V
        // (A-B)N >= V-A
        // N >= (V-A)/(A-B)
        // result = N+1 일
        System.out.printf("%d, %d, %d\n",A,B,V);
        System.out.println((V-A)%(A-B) > 0 ? (V-A)/(A-B) + 2 : ((V-A)/(A-B)) +1);
        in.close();
    }
}
