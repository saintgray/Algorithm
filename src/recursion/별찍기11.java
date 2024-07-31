package recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    2448 : 별찍기 - 11
//    ref url : https://www.acmicpc.net/problem/2448
public class 별찍기11 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static Character[][] map;
    static final char STAR = '*';
    static final char SPACE = ' ';

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(in.readLine());
        int k = (int) (Math.log(N / 3) / Math.log(2));
        int size = (int) (5 * Math.pow(2, k) + (k == 0 ? 0 : Math.pow(2, k) -1));   // 별표 개수 + 빈 칸의 개수

        map = new Character[N][size];
        Arrays.stream(map).forEach(arr -> {
            arr = new Character[size];
            Arrays.fill(arr, SPACE);
        });
        draw(k, 0, N-1, 0, size-1);

        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                out.write(String.valueOf(map[r][c] == null ? SPACE : map[r][c]));
            }
            out.newLine();
        }
        out.flush();
    }

    static void draw(int k, int r1, int r2, int c1, int c2) {
        if(k != 0) {
            // 상단
            // 상단의 좌하단 c1, c2
            int topBottomCount = (int) (5 * Math.pow(2, k-1));  // 별표 개수
            int topBlankCount = (int) Math.pow(2, k-1) - 1;     // 빈칸 개수
            int topC1 = ((c1 + c2) / 2) -  ((topBottomCount) / 2) - (topBlankCount - 1) / 2;
            int topC2 = ((c1 + c2) / 2) +  ((topBottomCount) / 2) + (topBlankCount - 1) / 2;
            draw(k - 1, r1, ((r1 + r2) / 2), topC1, topC2);
            draw(k - 1, ((r1 + r2) / 2) + 1, r2, c1, ((c1 + c2) / 2) - 1);
            draw(k - 1, ((r1 + r2) / 2) + 1, r2, ((c1 + c2) / 2) + 1, c2);
            return;
        }
        map[r1][(c1 + c2) /2] = STAR;
        map[(r1+r2)/2][((c1+c2) / 2) -1] = STAR;
        map[(r1+r2)/2][((c1+c2) / 2) +1] = STAR;
        for (int c = c1; c <= c2; c++) {
            map[r2][c] = STAR;
        }

    }

    static void debug() {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                System.out.printf("%s", String.valueOf(map[r][c] == null ? SPACE : map[r][c]));
            }
            System.out.println();
        }
    }

}
