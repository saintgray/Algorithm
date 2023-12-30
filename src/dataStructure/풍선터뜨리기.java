package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

//    2346 : 풍선 터뜨리기
//    ref url : https://www.acmicpc.net/problem/2346
public class 풍선터뜨리기 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(in.readLine());
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Map<Integer, Bubble> bubblesNetwork = new TreeMap<>(Comparator.naturalOrder());
        for (int i = 0; i < N; i++) {
            int weight = arr[i];
            int originSeq = i + 1;
            Bubble bubble = new Bubble(originSeq, weight);
            if (i == 0) {
                bubblesNetwork.put(originSeq, bubble);
            } else {
                if (i == arr.length - 1) {
                    Bubble first = bubblesNetwork.get(1);
                    first.left = bubble;
                    bubble.right = first;
                }
                Bubble prevBubble = bubblesNetwork.get(originSeq - 1);
                prevBubble.right = bubble;
                bubble.left = prevBubble;
                bubblesNetwork.put(originSeq, bubble);
            }
        }
        Bubble bubble = bubblesNetwork.get(1);
        while (!bubblesNetwork.isEmpty()) {
            int popBubbleNum = bubble.originSeq;
            Bubble left = bubble.left;
            Bubble right = bubble.right;
            // re-connect network
            left.right = right;
            right.left = left;
            // pop bubble
            bubblesNetwork.remove(popBubbleNum);
            out.write(String.format("%s ", popBubbleNum));
            // find next
            bubble = bubble.findNext();
        }
        out.flush();
    }

    private static class Bubble {
        private int originSeq;
        private int weight;
        private Bubble left;
        private Bubble right;
        public Bubble(int seq, int weight) {
            this.originSeq = seq;
            this.weight = weight;
        }
        public Bubble findNext() {
            Bubble next = null;
            int weight = this.weight;
            boolean rotateClockwise = weight > 0;
            int steps = Math.abs(weight);
            if (rotateClockwise) {
                for (int i = 0; i < steps; i++) {
                    next = next == null ? this.right : next.right;
                }
            } else {
                for (int i = 0; i < steps; i++) {
                    next = next == null ? this.left : next.left;
                }
            }
            return next;
        }
    }
}
