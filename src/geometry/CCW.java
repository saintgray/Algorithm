package geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    11758 : CCW
//    ref url : https://www.acmicpc.net/problem/11758
public class CCW {
    public static final int x = 0;
    public static final int y = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[][] matrix = new int[3][2];
        for (int i = 0; i < 3; i++) {
            int[] coord = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            matrix[i][0] = coord[0];
            matrix[i][1] = coord[1];
        }
        // |a b|
        // |c d|
        // ccw = ad - bc
        int ccw = (matrix[1][x] - matrix[0][x]) * (matrix[2][y] - matrix[1][y]) - (matrix[1][y] - matrix[0][y]) * (matrix[2][x] - matrix[1][x]);
        System.out.println(Integer.compare(ccw, 0));
    }
}
