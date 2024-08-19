package backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 4658 : 삼각형게임
// https://www.acmicpc.net/problem/4658
public class 삼각형게임 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int max;
    static Triangle[] triangles = new Triangle[6];
    static Triangle[] resultSet = new Triangle[6];

    public static void main(String[] args) throws IOException {

        String input = null;
        while (!"$".equals(input = in.readLine())) {
            if ("*".equals(input)) continue;
            max = Integer.MIN_VALUE;
            triangles[0] = new Triangle(input.split(" "));
            for (int i = 0; i < 5; i++) {
                triangles[i + 1] = new Triangle(in.readLine().split(" "));
            }
            track(1, triangles[0], 0);
            out.write(max == Integer.MIN_VALUE ? "none" : String.valueOf(max));
            out.newLine();
        }
        out.flush();
    }
    static void track(int depth, Triangle prev, int score) {
        if (depth > 6) {
            max = Math.max(score, max);
            return;
        }
        for (int i = 0; i < 6; i++) {
            Triangle triangle = triangles[i];
            if (triangle.checked) continue;
            for (int r = 0; r < 3; r++) {
                triangle.rotate(r);
                if (validate(depth, prev, triangle)) {
                    triangle.checked = true;
                    resultSet[depth - 1] = triangle;
                    track(depth + 1, triangle, score + triangle.t);
                    triangle.checked = false;
                }
            }
        }
    }

    static boolean validate(int depth, Triangle prev, Triangle t) {
        if (depth == 1) return true;
        // 마지막 조각일 때는 첫 조각의 우측과 마지막조각의 좌측 값까지 같아야 함
        if (depth == 6) return t.r == prev.l && resultSet[0].r == t.l;
        return t.r == prev.l;
    }

    static class Triangle {
        int l;
        int r;
        int t;
        boolean reversed;
        boolean checked;

        public Triangle(String[] params) {
            this.l = Integer.parseInt(params[0]);
            this.r = Integer.parseInt(params[1]);
            this.t = Integer.parseInt(params[2]);
        }
        void rotate(int i) {
            if (i == 0) return;
            int _t = this.t;
            this.t = this.l;
            this.l = this.r;
            this.r = _t;
        }
    }
}

