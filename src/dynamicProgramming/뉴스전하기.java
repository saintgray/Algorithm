package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//    1135 : 뉴스 전하기
//    ref url : https://www.acmicpc.net/problem/1135
//    tag : dynamic programming in tree, greedy, tree
public class 뉴스전하기 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        V[] vtx = new V[N];
        for (int i = 0; i < N; i++) {
            vtx[i] = new V(i);
        }
        String[] conn = in.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(conn[i]);
            if (v == -1) continue;
            vtx[v].children.add(vtx[i]);
        }
        calcSpreadTime(vtx[0]);
        System.out.println(vtx[0].time);
    }

    static void calcSpreadTime(V v) {
        if (v.children.isEmpty()) {
            return;
        }
        // 모든 직속 부하의 뉴스 최소 전달시간 계산
        for (int i = 0; i < v.children.size(); i++) {
            calcSpreadTime(v.children.get(i));
        }
        // 전파 시간이 큰 순으로 정렬
        v.children.sort(Comparator.comparing(_v -> _v.time, Comparator.reverseOrder()));
        // 정렬순으로 전파를 할때마다 후순위 부하들은 기다리는 시간만큼의 시간이 추가로 계산되어야 하므로
        // 전파를 하면서 전달시간이 가장 긴 부하의 전달시간이 이 직원의 부하들에게 뉴스를 전파하는 최소 시간이다.
        int max = 0;
        List<V> children = v.children;
        for (int i = 0; i < children.size(); i++) {
            max = Math.max(max, children.get(i).time + i);
        }
        v.time = max + 1;
    }

    static class V {
        int no; // 직원 번호
        int time;   // 이 직원 밑에 속한 모든 부하에게 뉴스를 전달하는 최소시간
        List<V> children;

        public V(int no) {
            this.no = no;
            this.children = new ArrayList<>();
        }
    }
}
