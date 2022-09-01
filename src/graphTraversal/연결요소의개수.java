package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    11724 : 연결 요소의 개수
//
//    문제
//    방향 없는 그래프가 주어졌을 때,
//    연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.
//
//    입력
//    첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다.
//    (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2)
//    둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다.
//    (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.
//
//    출력
//    첫째 줄에 연결 요소의 개수를 출력한다.

public class 연결요소의개수 {
    static int totalConnections = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        int N = params[0];
        int M = params[1];
        boolean[] checked = new boolean[N + 1];
        boolean[][] connections = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            int[] connect = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
            int p1 = connect[0];
            int p2 = connect[1];
            connections[p1][p2] = true;
            connections[p2][p1] = true;
        }

        for(int i =1; i< checked.length; i++){
            if(!checked[i]){
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
            if(connections[point][i] == true && !checked[i])
                dfs(connections, checked, i);
        }
    }
}
