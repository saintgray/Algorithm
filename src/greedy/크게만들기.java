package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

//    2812 : 크게 만들기
//    ref url : https://www.acmicpc.net/problem/2812
public class 크게만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] params = in.readLine().split(" ");
        int N = Integer.parseInt(params[0]);
        int K = Integer.parseInt(params[1]);
        char[] str = in.readLine().toCharArray();

        Deque<Character> dq = new ArrayDeque<>();
        dq.add(str[0]);
        for (int i = 1; i < N ; i++) {
            char c = str[i];
            if (c > dq.getLast()) {
                while (!dq.isEmpty() && c > dq.getLast() && N - K - dq.size() <= N - i - 1) {
                    // 1. queue 가 비어있지 않고
                    // 2. 숫자가 queue 의 마지막 수보다 크면서
                    // 3. K 자리수가 되기 위해 채워야 하는 갯수보다 남아있는 숫자의 갯수가 같거나 클 때
                    dq.removeLast();
                }
                dq.add(c);
            } else {
                dq.add(c);
            }
        }
        // queue 길이에 관계없이 N - K 자리수만큼 출력
        System.out.println(
                dq.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining())
                        .substring(0, N - K)
        );
    }
}
