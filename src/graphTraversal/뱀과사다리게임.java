package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//    16928 : 뱀과 사다리 게임
//    ref url : https://www.acmicpc.net/problem/16928
public class 뱀과사다리게임 {

    static Node[] nodes = new Node[101];
    static int[] diceRoll = new int[]{1,2,3,4,5,6};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        for (int i = 1; i < 101; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < N; i++) {
            params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes[params[0]].to = params[1];
        }
        for (int i = 0; i < M; i++) {
            params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes[params[0]].to = params[1];
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while(!q.isEmpty()) {
            int n = q.poll();
            for (int k = 0; k < 6; k++) {
                int _n = n + diceRoll[k];
                if(!isBoundary(_n)) continue;
                Node next = nodes[_n];
                // 뱀이나 사다리일 경우
                if(next.to > 0) {
                    boolean initTarget = nodes[next.to].roll == 0;
                    boolean updateTarget = nodes[next.to].roll > nodes[n].roll + 1;
                    nodes[next.to].roll = initTarget ? nodes[n].roll + 1 : Math.min(nodes[next.to].roll, nodes[n].roll + 1);
                    if(initTarget || updateTarget) q.add(next.to);
                    continue;
                };
                if (next.roll == 0 || next.roll > nodes[n].roll + 1) {
                    next.roll = nodes[n].roll + 1;
                    q.add(_n);
                }
            }
        }
        System.out.println(nodes[100].roll);
    }
    static class Node {
        int n;
        int to;
        int roll;

        public Node(int n) {
            this.n = n;
        }
    }

    static boolean isBoundary(int n) {
        return n <= 100 && n > 0;
    }
}
