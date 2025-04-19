package binarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

//    2568 : 전깃줄 2
//    ref url : https://www.acmicpc.net/submit/2568
//    tag : longest increasing subsequence, binary search
public class 전깃줄2 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    static int MAX_WIRES = 500000;
    static Wire[] wires;
    static Wire[] memory;
    static Hist[] history;

    static int lis=0;

    public static void main(String[] args) throws IOException {

        int wire_count = Integer.parseInt(in.readLine());
        wires = new Wire[wire_count];
        memory = new Wire[wire_count + 1];
        memory[0] = new Wire(0, 0);
        history = new Hist[wire_count];
        for (int i = 0; i < wire_count; i++) {
            String[] input = in.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to =Integer.parseInt(input[1]);
            wires[i]= new Wire(from, to);
        }
        Arrays.sort(wires, Comparator.nullsFirst(Comparator.comparing(w -> w.from)));
//        System.out.println(Arrays.toString(wires));
        for (int i = 0; i < wires.length; i++) {
            if (wires[i] == null) continue;
            if (memory[lis].to < wires[i].to) {
                memory[++lis] = wires[i];
                history[i] = new Hist(wires[i], lis);
                continue;
            }
            int index=find(memory, wires[i], lis);
            memory[index] = wires[i];
            history[i] = new Hist(wires[i], index);
        }
//        System.out.println(Arrays.toString(history));
//        System.out.println(Arrays.toString(memory));
        out.write(String.valueOf(wire_count - lis));
        out.newLine();
        int idx = lis;
//        System.out.println("lis --> " + lis);
        int[] cut_wires = new int[wire_count];
        for (int i = history.length-1; i >= 0; i--) {
            if(idx == history[i].index) {
                idx--; // 연결된 전깃줄
            } else {
                cut_wires[wire_count-1 - i] = history[i].wire.from;
            }
        }
//        System.out.println(Arrays.toString(cut_wires));
        Arrays.sort(cut_wires);
        for (int wire : cut_wires) {
            if(wire == 0) continue;
            out.write(String.valueOf(wire));
            out.newLine();
        }
        out.flush();
    }

    static int find(Wire[] wires, Wire wire, int ed) {
        int start = 0;
        int end = ed;
        while (start < end) {
            int mid = (start + end) / 2;
            if (wires[mid].to >= wire.to) {
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
        Wire wire;
        int index;

        public Hist(Wire wire, int index) {
            this.wire = wire;
            this.index = index;
        }

        @Override
        public String toString() {
            return String.valueOf(this.index);
        }
    }
}