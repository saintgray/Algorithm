package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    1697 : 숨바꼭질
//
//    문제
//    수빈이는 동생과 숨바꼭질을 하고 있다.
//    수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고,
//    동생은 점 K(0 ≤ K ≤ 100,000)에 있다.
//    수빈이는 걷거나 순간이동을 할 수 있다.
//    만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
//    순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
//    수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
//
//    입력
//    첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
//
//    출력
//    수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

public class 숨바꼭질 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] times = new int[100001];
        boolean[] searched = new boolean[100001];

        int[] coordinate = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        int subin = coordinate[0];
        int sister = coordinate[1];
        searched[subin] = true;
        Integer[] nextRun  = new Integer[1];
        nextRun[0] = subin;

        try {
            run(times, searched, nextRun, sister);
        } catch (Exception e) {
            System.out.println(times[sister]);
        }
        in.close();
    }

    static void run(int[] times, boolean[] searched, Integer[] nextRun, int sister) throws Exception {
        List<Integer> nextRuns = new ArrayList<>();
        for (int subin : nextRun) {
            if (subin - 1 >= 0 && !searched[subin - 1]) {
                nextRuns.add(subin - 1);
                searched[subin - 1] = true;
                times[subin - 1] = times[subin] + 1;
            }
            if (subin * 2 <= 100000 && !searched[subin * 2]) {
                nextRuns.add(subin * 2);
                searched[subin * 2] = true;
                times[subin * 2] = times[subin] + 1;
            }
            if (subin + 1 <= 100000 && !searched[subin + 1]) {
                nextRuns.add(subin + 1);
                searched[subin + 1] = true;
                times[subin + 1] = times[subin] + 1;
            }
        }
        if (searched[sister])
            throw new Exception();

        if (!nextRuns.isEmpty())
            run(times, searched, nextRuns.toArray(new Integer[nextRuns.size()]), sister);
    }
}
