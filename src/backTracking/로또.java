package backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

//    6603 : 로또
//    ref url : https://www.acmicpc.net/problem/6603
public class 로또 {
    static boolean[] visited;
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int N = 0;

    public static void main(String[] args) throws IOException {
        String input = null;
        while (!"0".equals(input = in.readLine())) {
            int[] params = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            N = params[0];
            int[] nums = Arrays.copyOfRange(params, 1, params.length);
            visited = new boolean[N + 1];
            runTracking(nums, -1, -1, new int[6]);
            out.newLine();
        }
        out.flush();
    }

    static void runTracking(int[] nums, int index, int resultIndex, int[] result) throws IOException {
        if (result[result.length-1] != 0) {
            out.write(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
            out.newLine();
        } else {
            for (int i = index + 1; i < nums.length; i++) {
                if (!visited[i]) {
                    result[resultIndex + 1] = nums[i];
                    visited[i] = true;
                    runTracking(nums, i, resultIndex + 1, Arrays.copyOf(result, 6));
                    visited[i] = false;
                    for (int j = resultIndex + 1; j < result.length ; j++) {
                        result[j] = 0;
                    }
                }
            }
        }
    }
}
