package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    10775 : 공항
//    ref url : https://www.acmicpc.net/problem/10775
//    see : Disjoint Set (union-find)
public class 공항 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int G;
    static int P;
    static Gate[] gates;
    static int maxGates = 0;

    public static void main(String[] args) throws IOException {
        G = Integer.parseInt(in.readLine());
        P = Integer.parseInt(in.readLine());
        gates = new Gate[G + 1];
        for (int i = 0; i < G; i++) {
            int g = i +1;
            gates[g] = new Gate(g);
        }

        for (int i = 0; i < P; i++) {
            int p = Integer.parseInt(in.readLine());
            Gate gate = gates[p];
            Gate nextDockGate = find(gate);
            // 1번 Gate 까지 모두 점유되어 있다는 말임
            if(nextDockGate == null) break;
            union(gate);
            maxGates++;
        }
        System.out.println(maxGates);
    }

    static Gate find(Gate gate) {
        Gate parent = gates[gate.g - gate.range];
        if(parent == null) return null;
        return parent.range > 0 ? find(parent) : parent;
    }

    static void union(Gate gate) {
        Gate parent = find(gate);
        parent.occupied = true;
        // 부모 gate 의 범위가 초기화 되지 않았을 시 1로 초기화 해야 함
        // 부모는 union 과정에서 점유되었고 이 부모의 다음 최적화 Gate 판단 시 다음 비행기가
        // 부모 gate 에 착륙 시 도킹할 게이트를 판단해야 하기 때문임
        if(parent.range == 0) parent.range = 1;
        // gate.range : gate 번호부터 (gate 번호 - range + 1) 번 gate 까지 모두 점유되있음을 의미
        gate.range = gate.g - parent.g + 1;
    }


    static class Gate {
        int g;
        boolean occupied;
        int range;

        public Gate(int g) {
            this.g = g;
            this.range = 0;
        }
    }
}
