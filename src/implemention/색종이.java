package implemention;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    2563 : 색종이
//    ref url : https://www.acmicpc.net/problem/2563
public class 색종이 {
    static int[][] matrix = new int[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int papers = Integer.parseInt(in.readLine());
        for (int i = 0; i < papers; i++) {
            int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = params[0];
            int y = params[1];
            for (int row = y; row < y + 10; row++) {
                for (int col = x; col < x + 10; col++) {
                    matrix[row][col] = 1;
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1)
                    cnt++;
            }
        }
        System.out.println(cnt);
    }
}
