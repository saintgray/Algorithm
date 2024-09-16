package graphTheroy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

//    1197 : 최소 스패닝 트리
//    ref url : https://www.acmicpc.net/problem/1197
//    tag : union-find, kruskal algorithm
public class 최소스패닝트리 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    static Node[] nodes;
    static Line[] lines;

    public static void main(String[] args) throws IOException {
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int V = params[0];
        int E = params[1];
        nodes = new Node[V + 1];
        lines = new Line[E];
        for (int i = 0; i < E; i++) {
            int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Node n1 = nodes[input[0]];
            Node n2 = nodes[input[1]];
            if (n1 == null) {
                n1 = new Node(input[0]);
                nodes[input[0]] = n1;
            }
            ;
            if (n2 == null) {
                n2 = new Node(input[1]);
                nodes[input[1]] = n2;
            }
            ;
            Line line = new Line();
            line.n1 = n1;
            line.n2 = n2;
            line.weight = input[2];
            lines[i] = line;
        }
        Arrays.sort(lines, Comparator.comparing(line -> line.weight));
        for (int i = 0; i < V; i++) {
            nodes[i + 1].parent = nodes[i + 1].n;
        }
        int result = 0;
        for (int i = 0; i < E; i++) {
            Line line = lines[i];
            int n1Parent = find_parent(line.n1.n);
            int n2Parent = find_parent(line.n2.n);
            if (n1Parent == n2Parent) {
                continue;
            }
            union(line.n1, line.n2);
            result += line.weight;

        }
        System.out.println(result);
    }

    static int find_parent(int n) {
        if (nodes[n].parent == n) {
            return n;
        }
        return nodes[n].parent = find_parent(nodes[n].parent);
    }

    static void union(Node n1, Node n2) {
        int p1 = find_parent(n1.n);
        int p2 = find_parent(n2.n);
        // 핵심
        if (p1 < p2) nodes[p2].parent = p1;
        else nodes[p1].parent = p2;
    }

    static class Line {
        Node n1;
        Node n2;
        int weight;
    }

    static class Node {
        int n;
        int parent;

        public Node(int n) {
            this.n = n;
        }
    }
}
