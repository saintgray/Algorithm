package graphTraversal;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

//    1260 : DFS와 BFS
//
//    문제
//    그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오.
//    단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고,
//    더 이상 방문할 수 있는 점이 없는 경우 종료한다.
//    정점 번호는 1번부터 N번까지이다.
//
//    입력
//    첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000),
//    간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다.
//    다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다.
//    어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다.
//    입력으로 주어지는 간선은 양방향이다.
//
//    출력
//    첫째 줄에 DFS를 수행한 결과를,
//    그 다음 줄에는 BFS를 수행한 결과를 출력한다.
//    V부터 방문된 점을 순서대로 출력하면 된다.

public class DFS와BFS {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        int N = params[0]; // 정점의 개수
        int M = params[1]; // 간선의 개수
        int V = params[2]; // 탐색을 시작할 정점 번호
        Vertex[] vertexes = new Vertex[N + 1];
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= M; i++) {
            int[] edgesInfo = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
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
        if (vertex == null || (vertex != null && vertex.searched == false)) {
            result.append(String.format("%d ", V));
            // 간선이 없을 시 탐색 종료
            if (vertex == null)
                return;
            // 탐색 flag
            vertex.searched = true;
        }
        List<Integer> edges = vertex.edges;
        edges = edges.stream()
                .filter(e -> vertexes[e].searched == false)
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
            if (vertex == null || (vertex != null && vertex.searched == false)) {
                result.append(String.format("%d ", n));
                // 간선이 없을 시 탐색 종료
                if (vertex == null)
                    continue;
                // 탐색 flag
                vertex.searched = true;
            }
            List<Integer> edges = vertex.edges;
            edges = edges.stream()
                    .filter(e -> vertexes[e].searched == false)
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());

            for (int i = 0; i < edges.size(); i++) {
                // 탐색 flag
                int edge = edges.get(i);
                // 간선의 방향은 양방향이기 때문에 이미 탐색 이 끝난 정점에 대해
                // 경로 추가 하지 않음
                if(vertexes[edge].searched == false)
                    result.append(String.format("%d ", edge));
                vertexes[edge].searched = true;
            }
            nextVArr.addAll(edges);
        }
        bfs(result, vertexes, nextVArr.stream().mapToInt(e -> e).toArray());
    }


    private static void writeAndClear(BufferedWriter out, StringBuilder result, Vertex[] vertexes) throws IOException {
        write(out, result);
        Arrays.stream(vertexes).filter(e -> e != null).forEach(e -> e.searched = false);
    }

    private static void write(BufferedWriter out, StringBuilder result) throws IOException {
        out.write(String.format("%s\n", result));
        result.delete(0, result.length());
    }
}
