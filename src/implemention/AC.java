package implemention;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// 5430 : AC
// ref url : https://www.acmicpc.net/problem/5430
public class AC {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());
        while (T-- > 0) {
            Deque<Integer> queue = new LinkedList<>();
            char[] operators = in.readLine().toCharArray();
            int N = Integer.parseInt(in.readLine());
            String arrayString = in.readLine();
            int[] arr = "[]".equals(arrayString) ? new int[0] :
                    Arrays.stream(arrayString.replaceAll("(\\[|\\])", "")
                                    .split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();
            for (int i = 0; i < N; i++) {
                queue.add(arr[i]);
            }
            boolean errorFlag = false;
            boolean isReversed = false;
            for (char o : operators) {
                if (o == 'R') {
                    isReversed = !isReversed;
                    continue;
                }
                // D
                if (queue.isEmpty()) {
                    out.write("error");
                    out.newLine();
                    errorFlag = true;
                    break;
                }
                if (isReversed) {
                    queue.removeLast();
                } else {
                    queue.removeFirst();
                }
            }
            if (errorFlag) continue;
            out.write("[");
            while (!queue.isEmpty()) {
                out.write(isReversed ? String.valueOf(queue.pollLast()) : String.valueOf(queue.pollFirst()));
                if (!queue.isEmpty()) out.write(",");
            }
            out.write("]");
            out.newLine();
        }
        out.flush();
    }
}
