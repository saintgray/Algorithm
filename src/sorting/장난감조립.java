package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//    2637 : 장난감 조립
//    ref url : https://www.acmicpc.net/problem/2637
//    tag : topology sort
public class 장난감조립 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());
        Block[] blocks = new Block[N + 1];
        for (int i = 1; i <= N; i++) {
            blocks[i] = new Block(i, N);
        }

        for (int i = 0; i < M; i++) {
            String[] params = in.readLine().split(" ");
            int X = Integer.parseInt(params[0]);
            int Y = Integer.parseInt(params[1]);
            int K = Integer.parseInt(params[2]);
            blocks[Y].out_degrees.add(blocks[X]);
            blocks[X].in_degrees++;
            blocks[X].order.put(Y, K);
        }

        // 기본 부품 추출
        Queue<Block> q = new LinkedList<>();
        Set<Integer> basic = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            if (blocks[i].in_degrees == 0) {
                basic.add(blocks[i].no);
                blocks[i].basic_comb[blocks[i].no] = 1;
                q.add(blocks[i]);
            }
        }
        // 위상 정렬 + 총 기본 부품 수 계산
        while(!q.isEmpty()) {
            Block b = q.poll();
            for (Block out : b.out_degrees) {
                if (--out.in_degrees == 0) {
                    Map<Integer, Integer> order = out.order;
                    for (Map.Entry<Integer, Integer> entry : order.entrySet()) {
                        Integer k = entry.getKey();
                        Integer v = entry.getValue();
                        if (basic.contains(k)) {
                            out.basic_comb[k] += v;
                        } else {
                            // 전 부품을 만드는 데 필요한 기본 부품 총 수를 merge
                            int[] basic_comb = blocks[k].basic_comb;
                            for (int i = 1; i <= N; i++) {
                                out.basic_comb[i] += v * basic_comb[i];
                            }
                        }
                    }
                    q.add(out);
                }
            }
        }
        int[] result = blocks[N].basic_comb;
        for (int i = 1; i <= N; i++) {
            if (result[i] > 0) {
                out.write(String.format("%d %d\n", i, result[i]));
            }
        }
        out.flush();
    }

    static class Block {
        int no;
        int in_degrees;
        List<Block> out_degrees;
        Map<Integer, Integer> order;
        int[] basic_comb;

        public Block(int no, int N) {
            this.no = no;
            this.out_degrees = new ArrayList<>();
            this.order = new HashMap<>();
            this.basic_comb = new int[N+1];
        }
    }
}
