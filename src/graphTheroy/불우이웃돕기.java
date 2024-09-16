package graphTheroy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//    1414 : 불우이웃돕기
//    ref url : https://www.acmicpc.net/problem/1414
//    tag : kruskal algorithm
public class 불우이웃돕기 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static Computer[] computers;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        computers = new Computer[N];
        for (int i = 0; i < N; i++) {
            computers[i] = new Computer(i);
        }

        List<Lan> lans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            char[] row = in.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                char c = row[j];
                if (c == '0') continue;
                lans.add(new Lan(computers[i], computers[j], c));
            }
        }
        lans.sort(Comparator.comparing(l -> l.length));
        for (int l = 0; l < lans.size(); l++) {
            Lan lan = lans.get(l);
            if (lan.a == lan.b) continue;
            Computer p1 = find(lan.a);
            Computer p2 = find(lan.b);
            if(p1 == p2) continue;
            lan.used = true;
            if (p1.no < p2.no) p2.parent = p1;
            else p1.parent = p2;
            lan.a.connected = true;
            lan.b.connected = true;
        }

        Arrays.stream(computers).forEach(불우이웃돕기::find);
        // 결과 값
        int result = 0;
        // 1. 사용하지 않은 Lan 선의 총 길이
        int unusedLanLength = 0;
        for (int i = 0; i < lans.size(); i++) {
            Lan lan = lans.get(i);
            if (!lan.used) unusedLanLength += lan.length;
        }
        // 2. 모든 컴퓨터의 부모 그룹 수
        int parentGroups = (int) Arrays.stream(computers).map(com -> com.parent).distinct().count();
        if (N == 1) {
            result = unusedLanLength;
        } else {
            result = parentGroups != 1 ? -1 : unusedLanLength;
        }
        System.out.println(result);
    }

    static Computer find(Computer com) {
        if (com.parent == com) return com;
        return (com.parent = find(com.parent));
    }

    static class Lan {
        Computer a;
        Computer b;
        int length;
        boolean used;

        public Lan(Computer a, Computer b, char c) {
            this.a = a;
            this.b = b;
            if (c >= 'A' && c <= 'Z') this.length = ((int) c) - 38;
            else this.length = ((int) c) - 96;
        }
    }

    static class Computer {
        int no;
        Computer parent;
        boolean connected;

        public Computer(int no) {
            this.no = no;
            this.parent = this;
        }
    }
}
