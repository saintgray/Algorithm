package numberTheory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//    1963 : 소수경로
//    ref url : https://www.acmicpc.net/problem/1963
public class 소수경로 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static Set<Integer> prime;

    public static void main(String[] args) throws IOException {
        prime = sieveOfEratosthenes(9999);
        int T = Integer.parseInt(in.readLine());
        while (T-- > 0) {
            String[] input = in.readLine().split(" ");
            String st = input[0];
            String ed = input[1];
            if (st.equals(ed)) {
                out.write("0\n");
                continue;
            }
            Queue<Number> queue = new LinkedList<>();
            queue.add(new Number(st.toCharArray(), 0));
            out.write(track(queue, ed.toCharArray()));
            out.newLine();
        }
        out.flush();
    }

    static String track(Queue<Number> queue, char[] ed) {
        // 방문한 소수 까지의 최소 변경 횟수
        Map<String, Integer> visited = new HashMap<>();
        while (!queue.isEmpty()) {
            Number n = queue.poll();
            char[] st = n.number;
            for (int i = 0; i < 4; i++) {
                int start = i == 0 ? 1 : 0;
                for (int num = start; num <= 9; num++) {
                    if (st[i] == (char) (num + 48)) continue;
                    String pushNumber = new String(Arrays.copyOf(st, 4));
                    if (!prime.contains(Integer.parseInt(pushNumber))) continue;
                    Number push = new Number();
                    push.number = pushNumber.toCharArray();
                    push.number[i] = (char) (num + 48);
                    push.changed = n.changed + 1;
                    // 방문할 소수까지의 최소 변경 횟수
                    Integer changeCount = visited.get(String.valueOf(push.number));
                    if (changeCount == null) {
                        visited.put(String.valueOf(push.number), push.changed);
                        queue.add(push);
                    } else {
                        if (push.changed + 1 < changeCount) {
                            queue.add(push);
                        }
                    }
                }
            }
        }
        Integer changeCountOfEnd = visited.get(String.valueOf(ed));
        return changeCountOfEnd == null ? "Impossible" : String.valueOf(changeCountOfEnd);
    }

    static Set<Integer> sieveOfEratosthenes(int N) {
        int[] base = {2, 3, 5, 7};
        int primeNumbers = N;
        int n = (int) Math.ceil(Math.sqrt(N));
        Integer[] arr = new Integer[N];
        for (int i = 1; i <= N; i++) {
            arr[i - 1] = i;
        }
        // 1은 소수가 아니므로 제외한다
        arr[0] = null;
        primeNumbers--;
        // 2, 3, 5, 7 의 배수를 제외한다
        for (int i = 0; i < base.length; i++) {
            int num = base[i];
            for (int _n = 2 * num; _n <= N; _n += num) {
                primeNumbers -= (arr[_n - 1] != null) ? 1 : 0;
                arr[_n - 1] = null;
            }
        }
        // 11 부터 N 의 제곱근까지 각 수의 배수를 제외한다
        for (int i = 11; i < n; i++) {
            for (int _n = i * 2; _n <= N; _n += i) {
                primeNumbers -= (arr[_n - 1] != null) ? 1 : 0;
                arr[_n - 1] = null;
            }
        }
        Set<Integer> sieve = new HashSet<>();
        for (Integer num : arr) {
            if (num == null) continue;
            sieve.add(num);
        }
        return sieve;
    }

    static class Number {
        char[] number;
        int changed;

        public Number() {
        }

        public Number(char[] number, int changed) {
            this.number = number;
            this.changed = changed;
        }
    }
}
