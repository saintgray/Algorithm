package backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N과M_3 {

    static int[] arr;
    static boolean[] checked;
    static List<Integer> list = new ArrayList<>();
    static int N;
    static int M;
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {

        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = params[0];
        M = params[1];
        arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        checked = new boolean[N];
        Arrays.sort(arr);
        track(0);
    }

    private static void track(int depth) throws IOException {
        if (depth == M) {
            print();
            out.newLine();
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(!checked[i]) {
                list.add(arr[i]);
                checked[i]=true;
                track(depth+1);
                list.remove(list.get(list.size()-1));
                checked[i] = false;
            }
        }
    }

    static void print() throws IOException {
        for (int i = 0; i < list.size(); i++) {
            out.write(String.format("%d ", list.get(i)));
        }
        out.flush();
    }
}
