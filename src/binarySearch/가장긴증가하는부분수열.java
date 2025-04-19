package binarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Objects;

//    14003 : 가장 긴 증가하는 부분수열
//    ref url : https://www.acmicpc.net/problem/14003
//    tag : longest increasing subsequence, binary search
public class 가장긴증가하는부분수열 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] wires;
    static int[] memory;
    static Hist[] history;

    static int lis=0;

    public static void main(String[] args) throws IOException {

        int wire_count = Integer.parseInt(in.readLine());
        wires = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        memory = new int[wire_count + 1];
        memory[0] = -1000000001;
        history = new Hist[wire_count];
        for (int i = 0; i < wires.length; i++) {
            if (memory[lis] < wires[i]) {
                memory[++lis] = wires[i];
                history[i] = new Hist(wires[i], lis);
                continue;
            }
            int index=find(memory, wires[i], lis);
            memory[index] = wires[i];
            history[i] = new Hist(wires[i], index);
        }
        out.write(String.valueOf(lis));
        out.newLine();
        int idx = lis;
        Integer[] result = new Integer[wire_count];
        for (int i = history.length-1; i >= 0; i--) {
            if(idx == history[i].index) {
                result[wire_count-1 - i] = history[i].num;
                idx--;
            }
        }
        Arrays.sort(result = Arrays.stream(result).filter(Objects::nonNull).toArray(Integer[]::new));
        for (Integer num : result) {
            if(num == null) continue;
            out.write(String.format("%d ", num));
        }
        out.flush();
    }

    static int find(int[] wires, int wire, int ed) {
        int start = 0;
        int end = ed;
        while (start < end) {
            int mid = (start + end) / 2;
            if (wires[mid] >= wire) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    static class Wire {
        Integer from;
        Integer to;

        boolean connected;

        public Wire(Integer from, Integer to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return String.format("{%s, %s}", this.from, this.to);
        }

    }

    static class Hist {
        int num;
        int index;

        public Hist(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public String toString() {
            return String.valueOf(this.index);
        }
    }
}