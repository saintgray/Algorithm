package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    1932 : 정수 삼각형
//    ref url : https://www.acmicpc.net/problem/1932
public class 정수삼각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int height = Integer.parseInt(in.readLine());
        Point[][] points = new Point[height][];
        for (int i = 0; i < height; i++) {
            int[] infos = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            points[i] = new Point[infos.length];
            for (int j = 0; j < infos.length; j++)
                points[i][j] = new Point(i, j, infos[j], points);
        }
        System.out.println(Arrays.stream(points[points.length - 1]).mapToInt(e -> e.max).max().getAsInt());
        in.close();
    }

    static class Point {
        int i;
        int j;
        int point;
        int max;

        public Point(int i, int j, int point, Point[][] points) {
            this.i = i;
            this.j = j;
            this.point = point;
            this.max = point;
            int plusLeft = 0;
            int plusRight = 0;
            if(this.i-1 >= 0){
                if(this.j < points[this.i-1].length)
                    plusLeft = points[this.i-1][this.j].max;
                if(this.j -1 >= 0)
                    plusRight = points[this.i-1][this.j-1].max;
            }
            this.max += Math.max(plusRight, plusLeft);
        }
    }

}
