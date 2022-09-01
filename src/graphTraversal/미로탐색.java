package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    2178 : 미로탐색
//    ref url : https://www.acmicpc.net/problem/2178

public class 미로탐색 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] mazePlate = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        int N = mazePlate[0];
        int M = mazePlate[1];
        Point[][] maze = new Point[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            int[] points = Arrays.stream(in.readLine().split("")).mapToInt(e -> Integer.parseInt(e)).toArray();
            for (int j = 0; j < M; j++) {
                if (points[j] == 1)
                    maze[i][j + 1] = new Point(i, j + 1);
            }
        }
        Point start = maze[1][1];
        Point[] anchor = new Point[1];
        anchor[0] = start;
        anchor[0].checked = true;
        anchor[0].fastest = 1;
        run(maze, anchor, N, M);
        System.out.println(maze[N][M].fastest);
    }

    static void run(Point[][] maze, Point[] nextSearch, int N, int M) {
        if (nextSearch.length == 0)
            return;
        List<Point> nextAnchors = new ArrayList<>();
        for (Point p : nextSearch) {
            int i = p.i;
            int j = p.j;
            if (i - 1 >= 1 && maze[i - 1][j] != null && !maze[i - 1][j].checked) {
                maze[i - 1][j].checked = true;
                maze[i - 1][j].fastest = p.fastest + 1;
                nextAnchors.add(maze[i - 1][j]);
            }
            if (i + 1 <= N && maze[i + 1][j] != null && !maze[i + 1][j].checked) {
                maze[i + 1][j].checked = true;
                maze[i + 1][j].fastest = p.fastest + 1;
                nextAnchors.add(maze[i + 1][j]);
            }
            if (j - 1 >= 1 && maze[i][j - 1] != null && !maze[i][j - 1].checked) {
                maze[i][j - 1].checked = true;
                maze[i][j - 1].fastest = p.fastest + 1;
                nextAnchors.add(maze[i][j - 1]);
            }
            if (j + 1 <= M && maze[i][j + 1] != null && !maze[i][j + 1].checked) {
                maze[i][j + 1].checked = true;
                maze[i][j + 1].fastest = p.fastest + 1;
                nextAnchors.add(maze[i][j + 1]);
            }
        }
        Point[] nextSearches = nextAnchors.toArray(new Point[nextAnchors.size()]);
        run(maze, nextSearches, N, M);

    }

    static class Point {
        int i;
        int j;
        Integer fastest;
        boolean checked;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}

// 함께보기
// BFS (너비 우선 탐색 - 최단 경로 도출)