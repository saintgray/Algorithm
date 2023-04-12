package geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    17386 : 선분 교차 1
//    ref url : https://www.acmicpc.net/problem/17386
public class 선분교차1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<Point> points = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            long[] coord = Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            points.add(new Point(coord[0], coord[1]));
            points.add(new Point(coord[2], coord[3]));
        }
        // p1 p2 p3 , p1 p2 p4 ccw : A
        // p3 p4 p1 , p3 p4 p2 ccw : B
        // A와 B가 모두 음수일 것 (법선벡터의 방향 반대)
        int A = ccw(points.get(0), points.get(1), points.get(2)) * ccw(points.get(0), points.get(1), points.get(3));
        int B = ccw(points.get(2), points.get(3), points.get(0)) * ccw(points.get(2), points.get(3), points.get(1));
        System.out.println(A < 0 &&  B < 0 ? 1 : 0);

    }

    static int ccw (Point p1, Point p2, Point p3){
        // p1 p2 vector (a,b)
        // p2 p3 vector (c,d)
        // | a b |
        // | c d |
        return Long.compare(
                (p2.x -p1.x) * (p3.y-p2.y) - (p2.y - p1.y) * (p3.x - p2.x), 0
        );
    }

    static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
