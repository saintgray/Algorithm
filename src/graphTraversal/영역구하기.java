package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

//    2583 : 영역구하기
//    ref url : https://www.acmicpc.net/status?from_problem=1&problem_id=2583
public class 영역구하기 {
    static int M;
    static int N;
    static int K;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        N = params[0];
        M = params[1];
        K = params[2];
        GridPaper[][] papers = new GridPaper[N][M];
        for (int k = 0; k < K; k++) {
            int[] sizeParam = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
            int x1 = sizeParam[0];
            int y1 = sizeParam[1];
            int x2 = sizeParam[2];
            int y2 = sizeParam[3];
            for (int i = x1; i < x2; i++) {
                for (int j = y1; j < y2; j++) {
                    if (papers[j][i] == null)
                        papers[j][i] = new GridPaper(i, j, true);
                }
            }
        }
        List<Integer> areaSizes = new ArrayList<>();
        run(papers, areaSizes);
        areaSizes = areaSizes.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        System.out.println(areaSizes.size());
        for(int n : areaSizes)
            System.out.printf("%d ",n);
        in.close();
    }

    static void run(GridPaper[][] papers, List<Integer> areaSizes) {
        Queue<GridPaper> queue = new LinkedList<>();
        for (int i = 0; i < papers.length; i++) {
            for (int j = 0; j < papers[i].length; j++) {
                if (papers[i][j] == null) {
                    int areaSize = 1;
                    GridPaper gridInfo = new GridPaper(i,j,false);
                    papers[i][j] = gridInfo;
                    queue.add(gridInfo);
                    while (!queue.isEmpty()) {
                        GridPaper paper = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int x = paper.j + dx[k];
                            int y = paper.i + dy[k];
                            if (boundary(x, y)) {
                                GridPaper target = papers[y][x];
                                if (target == null) {
                                    target = new GridPaper(y, x, false);
                                    papers[y][x] = target;
                                    queue.add(target);
                                    areaSize++;
                                }
                            }
                        }
                    }
                    areaSizes.add(areaSize);
                }
            }
        }
    }

    static boolean boundary(int x, int y) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    static class GridPaper {
        int i;
        int j;
        boolean covered;

        public GridPaper(int i, int k, boolean covered) {
            this.i = i;
            this.j = k;
            this.covered = covered;
        }
    }
}

