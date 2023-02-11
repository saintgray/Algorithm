package geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    2477 : 참외밭
//    ref url : https://www.acmicpc.net/problem/2477

public class 참외밭 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int melons = Integer.parseInt(in.readLine());
        Point[] points = new Point[7];
        points[0] = new Point(0, 0);

        for (int i = 1; i < 7; i++)
            points[i] = new Point(in.readLine(), points[i - 1]);

        int rightDiagnoalSum = 0;
        int leftDiagnoalSum = 0;
        for (int i = 0; i < 6; i++) {
            rightDiagnoalSum += points[i].x * points[i + 1].y;
            leftDiagnoalSum += points[i].y * points[i + 1].x;
        }

        System.out.println((Math.abs(rightDiagnoalSum - leftDiagnoalSum) / 2) * melons);

        in.close();
    }

    static class Point {
        int x;
        int y;

        public Point(String read, Point prevPoint) {
            int[] coordinate = Arrays.stream(read.split(" ")).mapToInt(Integer::parseInt).toArray();
            int move = coordinate[1];
            int direction = coordinate[0];
            int prevX = prevPoint.x;
            int prevY = prevPoint.y;
            // coordinate[0] : 1 - 동, 2 - 서, 3 - 남, 4 - 북
            switch (direction) {
                case 1: {
                    this.x = prevX + move;
                    this.y = prevY;
                    break;
                }
                case 2: {
                    this.x = prevX - move;
                    this.y = prevY;
                    break;
                }
                case 3: {
                    this.x = prevX;
                    this.y = prevY - move;
                    break;
                }
                case 4: {
                    this.x = prevX;
                    this.y = prevY + move;
                    break;
                }
                default: {
                    break;
                }
            }
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
