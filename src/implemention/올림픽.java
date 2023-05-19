package implemention;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 올림픽 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int K = params[1];
        A target = null;
        List<A> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            A a = new A(in.readLine());
            list.add(a);
            if (a.n == K)
                target = a;
        }
        list.sort(Comparator.comparing(e -> ((A) e).g, Comparator.reverseOrder())
                .thenComparing(e -> ((A) e).s, Comparator.reverseOrder())
                .thenComparing(e -> ((A) e).b, Comparator.reverseOrder()));

        int r = 1;  // 등수
        int weight = 0; // 등수 보정 (공동 고려)
        list.get(0).r = r;
        for (int i = 1; i < list.size(); i++) {
            A a = list.get(i);
            A prev = list.get(i - 1);
            if (!(a.g == prev.g && a.s == prev.s && a.b == prev.b)) {
                r++;
                a.r = r + weight;
                r = a.r;
                weight = 0;
            } else {
                a.r = r;
                weight++;
            }
        }
        System.out.println(target.r);
    }

    static class A {
        int n;  // 국가
        int g;  // 금
        int s;  // 은
        int b;  // 동
        int r;  // 등수

        public A(String input) {
            int[] infos = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            this.n = infos[0];
            this.g = infos[1];
            this.s = infos[2];
            this.b = infos[3];
        }
    }
}
