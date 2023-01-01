package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    1697 : 숨바꼭질
//    ref url : https://www.acmicpc.net/problem/1697
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
