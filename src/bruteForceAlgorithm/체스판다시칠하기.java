package bruteForceAlgorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    1018 : 체스판 다시 칠하기
//    ref url : https://www.acmicpc.net/problem/1018
public class 체스판다시칠하기 {
    static char[][] type1;
    static char[][] type2;
    static char[][] origin;
    static char[] wb = {'W', 'B'};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int h = params[0];
        int w = params[1];
        int max = Math.max(h, w);

        type1 = new char[max][max];
        type2 = new char[max][max];

        for (int i = 0; i < max; i++) {
            type1[i] = new char[max];
            type2[i] = new char[max];
            if (i == 0) {
                for (int j = 0; j < max; j++) {
                    type1[i][j] = wb[j % 2];
                    type2[i][j] = wb[(j + 1) % 2];
                }
            } else {
                type1[i] = type2[i - 1];
                type2[i] = type1[i - 1];
            }
        }
        origin = new char[h][w];
        for (int i = 0; i < h; i++) {
            char[] row = in.readLine().toCharArray();
            origin[i] = new char[w];
            for (int j = 0; j < w; j++) {
                origin[i][j] = row[j];
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i + 8 <= h && j + 8 <= w) {
                    int r1 = 0;
                    int r2 = 0;
                    for (int k = i; k < i + 8; k++) {
                        for (int l = j; l < j + 8; l++) {
                            r1 += type1[k][l] != origin[k][l] ? 0 : 1;
                            r2 += type2[k][l] != origin[k][l] ? 0 : 1;
                        }
                    }
                    result = Math.min(result, Math.min(r1, r2));
                }

            }
        }
        System.out.println(result);
    }
}
