package graphTraversal;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//    24479 : 깊이우선탐색
//    ref url : https://www.acmicpc.net/problem/24479
public class 깊이우선탐색 {

    static int globalOrder = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        int R = params[2];

        boolean[] visited = new boolean[N + 1];
        int[] visitedOrder = new int[N+1];
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < M; i++) {
            int[] input = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Node rNode = nodeMap.get(input[0]);
            Node sNode = nodeMap.get(input[1]);
            if (rNode != null)
                rNode.list.add(input[1]);
            if (sNode != null)
                sNode.list.add(input[0]);
            if (rNode == null)
                nodeMap.put(input[0], new Node(input[0], input[1]));
            if (sNode == null)
                nodeMap.put(input[1], new Node(input[1], input[0]));
        }
        nodeMap.forEach((key, value) -> value.list.sort(Comparator.naturalOrder()));
        run(visitedOrder, visited, R, nodeMap);
        for (int i = 1; i < visitedOrder.length; i++) {
            out.write(String.valueOf(visitedOrder[i]));
            out.newLine();
        }
        out.flush();
        out.close();
        in.close();
    }

    static void run(int[] order, boolean[] visited, int R, Map<Integer, Node> nodeMap) {
        visited[R] = true;
        order[R] = globalOrder++;
        Node node = nodeMap.get(R);
        if (node != null) {
            for (Integer s : node.list)
                if(!visited[s])
                    run(order, visited, s, nodeMap);
        }
    }

    static class Node {
        int r;
        boolean visited;
        List<Integer> list;

        public Node(int r, int s) {
            this.list = new ArrayList<>();
            this.r = r;
            list.add(s);
        }
    }
}
