package graphTraversal;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

//    1260 : DFS 와 BFS
//    ref url : https://www.acmicpc.net/problem/126
public class DFS와BFS {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0]; // 정점의 개수
        int M = params[1]; // 간선의 개수
        int V = params[2]; // 탐색을 시작할 정점 번호
        Vertex[] vertexes = new Vertex[N + 1];
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= M; i++) {
            int[] edgesInfo = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int depart = edgesInfo[0];
            int arrival = edgesInfo[1];
            if (vertexes[depart] == null)
                vertexes[depart] = new Vertex();
            if (vertexes[arrival] == null)
                vertexes[arrival] = new Vertex();
            vertexes[depart].edges.add(arrival);
            vertexes[arrival].edges.add(depart);
        }

        dfs(result, vertexes, V);
        writeAndClear(out, result, vertexes);
        int[] anchor = new int[1];
        anchor[0] = V;
        bfs(result, vertexes, anchor);
        write(out, result);

        out.flush();
        in.close();
        out.close();

    }


    static class Vertex {
        List<Integer> edges;
        boolean searched;

        public Vertex() {
            this.edges = new Vector<>();
        }
    }

    public static void dfs(StringBuilder result, Vertex[] vertexes, int V) {
        Vertex vertex = vertexes[V];
        if (vertex == null || !vertex.searched) {
            result.append(String.format("%d ", V));
            // 간선이 없을 시 탐색 종료
            if (vertex == null)
                return;
            // 탐색 flag
            vertex.searched = true;
        }
        List<Integer> edges = vertex.edges;
        edges = edges.stream()
                .filter(e -> !vertexes[e].searched)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        if (edges.size() == 0)
            return;

        for (int n : edges)
            dfs(result, vertexes, n);
    }


    public static void bfs(StringBuilder result, Vertex[] vertexes, int[] VArr) {

        if (VArr.length == 0)
            return;
        List<Integer> nextVArr = new ArrayList<>();
        for (int n : VArr) {
            Vertex vertex = vertexes[n];
            if (vertex == null || !vertex.searched) {
                result.append(String.format("%d ", n));
                // 간선이 없을 시 탐색 종료
                if (vertex == null)
                    continue;
                // 탐색 flag
                vertex.searched = true;
            }
            List<Integer> edges = vertex.edges;
            edges = edges.stream()
                    .filter(e -> !vertexes[e].searched)
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());

            for (int i = 0; i < edges.size(); i++) {
                // 탐색 flag
                int edge = edges.get(i);
                // 간선의 방향은 양방향이기 때문에 이미 탐색 이 끝난 정점에 대해
                // 경로 추가 하지 않음
                if(!vertexes[edge].searched)
                    result.append(String.format("%d ", edge));
                vertexes[edge].searched = true;
            }
            nextVArr.addAll(edges);
        }
        bfs(result, vertexes, nextVArr.stream().mapToInt(e -> e).toArray());
    }


    private static void writeAndClear(BufferedWriter out, StringBuilder result, Vertex[] vertexes) throws IOException {
        write(out, result);
        Arrays.stream(vertexes).filter(Objects::nonNull).forEach(e -> e.searched = false);
    }

    private static void write(BufferedWriter out, StringBuilder result) throws IOException {
        out.write(String.format("%s\n", result));
        result.delete(0, result.length());
    }
}
