package backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//    2239 : 스도쿠
//    ref url : https://www.acmicpc.net/problem/2239
public class 스도쿠 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int SIZE = 9;
    static int[][] puzzle = new int[SIZE][SIZE];
    static Candidate[][] fragArr = new Candidate[3][3];
    static boolean[][] rowArr = new boolean[9][SIZE + 1];
    static boolean[][] colArr = new boolean[9][SIZE + 1];
    static int question;

    public static void main(String[] args) throws IOException {
        // 담당 영역 내 선택 가능한 후보군 초기화 (1~9까지)
        init();
        for (int i = 0; i < SIZE; i++) {
            int[] p = Arrays.stream(in.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < SIZE; j++) {
                int n = p[j];
                puzzle[i][j] = n;
                int _r = i / 3;
                int _c = j / 3;
                if (n != 0) {
                    // 이미 적혀있는 수를 후보군에서 제외
                    fragArr[_r][_c].used[n] = true;
                    rowArr[i][n] = true;
                    colArr[j][n] = true;
                }
                question += n == 0 ? 1: 0;
            }
        }
        track(0, 0, 0);
    }

    static void track(int r, int c, int solved) throws IOException{
        if (solved == question) {
            // 백트래킹 탐색시 1부터 9까지 오름차순으로 탐색하므로
            // 제일 먼저 찾은 스도쿠의 해가
            // 완성된 정답 스도쿠들의 81자리 숫자 중 가장 낮은 숫자임이 보장되므로 다른 해를 찾을 필요가 없다.
            // 즉 출력 후 바로 시스템을 종료한다
            submit();
            System.exit(0);
        }
        for (int _r = r; _r < SIZE; _r++) {
            for (int _c = (_r == r ? c : 0); _c < SIZE; _c++) {
                if (puzzle[_r][_c] != 0) continue;
                boolean[] fragUsed = fragArr[_r/3][_c/3].used;
                boolean[] rowUsed = rowArr[_r];
                boolean[] colUsed = colArr[_c];
                for (int n = 1; n < fragUsed.length; n++) {
                    if(fragUsed[n]) continue;
                    if(!rowUsed[n] && !colUsed[n]) {
                        puzzle[_r][_c] = n;
                        fragUsed[n] = true;
                        rowUsed[n] = true;
                        colUsed[n] = true;
                        track(_r, _c, solved + 1);
                        puzzle[_r][_c] = 0;
                        fragUsed[n] = false;
                        rowUsed[n] = false;
                        colUsed[n] = false;
                    }
                }
                // 후보군을 다 대입해본 뒤에는 다음 칸들을 더 탐색하면 안되고
                // 이전 후보군의 다음 숫자를 대입해야 하므로 return 한다
                return;
            }
        }
    }

    static void init() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fragArr[i][j] = new Candidate();
            }
        }
    }

    static Set<Integer> fullSet() {
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            result.add(i + 1);
        }
        return result;
    }

    static BigDecimal getResultNumber() {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            buf.append(Arrays.stream(puzzle[i]).mapToObj(String::valueOf).collect(Collectors.joining()));
        }
        return new BigDecimal(buf.toString());
    }

    static void submit() throws IOException {
        String resultStr = String.valueOf(getResultNumber());
        for (int i = 0; i < 81; i++) {
            out.write(resultStr.charAt(i));
            if (i != 80 && (i + 1) % 9 == 0) out.newLine();
        }
        out.flush();
    }

    static class Candidate {
        boolean[] used;

        public Candidate() {
            this.used = new boolean[10];
        }
    }

}
