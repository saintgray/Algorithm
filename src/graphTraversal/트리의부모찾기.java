package graphTraversal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    11725 : 트리의 부모 찾기 
//    ref url : https://www.acmicpc.net/problem/11725
public class 트리의부모찾기 {

    static int N;
    static ArrayList<Integer>[] graph;

    // idx 번 node 의 부모 node 정보를 저장
    static NodeInfo[] nodes;


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(in.readLine());
        graph = new ArrayList[N+1];
        nodes = new NodeInfo[N+1];
        for (int i = 0; i < N+1; i++) {
            nodes[i] = new NodeInfo();
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n1 = params[0];
            int n2 = params[1];
            graph[n1].add(n2);
            graph[n2].add(n1);
        }
        initParentNode(1);
        for (int i = 2; i < N+1; i++) {
            out.write(String.valueOf(nodes[i].parentNode));
            out.newLine();
        }
        out.flush();
    }


    static void initParentNode(int parentNode) {
        List<Integer> children = graph[parentNode];
        for (Integer childNode : children) {
            // 각 node 의 부모는 하나이므로 갱신할 필요가 없다.
            if(nodes[childNode].parentNode == 0) {
                nodes[childNode].parentNode = parentNode;
                initParentNode(childNode);
            }
        }
    }

    static class NodeInfo {
        int parentNode;
    }
}
