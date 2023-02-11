package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    11724 : 연결요소의개수
//    ref url : https://www.acmicpc.net/problem/11724
public class 연결요소의개수 {
    static int totalConnections = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        boolean[] checked = new boolean[N + 1];
        boolean[][] connections = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            int[] connect = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int p1 = connect[0];
            int p2 = connect[1];
            connections[p1][p2] = true;
            connections[p2][p1] = true;
        }

        for (int i = 1; i < checked.length; i++) {
            if (!checked[i]) {
                dfs(connections, checked, i);
                totalConnections++;
            }
        }
        System.out.println(totalConnections);
        in.close();
    }

    static void dfs(boolean[][] connections, boolean[] checked, int point) {
        checked[point] = true;
        for (int i = 1; i < connections[point].length; i++) {
            if (connections[point][i] && !checked[i])
                dfs(connections, checked, i);
        }
    }
}
