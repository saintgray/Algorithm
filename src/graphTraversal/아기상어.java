package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


//    16236 : 아기 상어
//    ref url : https://www.acmicpc.net/problem/16236
public class 아기상어 {
    static int N;
    static int[][] arr;
    static boolean[][] checked;
    static Shark shark = new Shark();
    static int[] di = {0, 0, 1, -1};
    static int[] dj = {1, -1, 0, 0};
    static int totalTime = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        arr = new int[N][N];
        checked = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            int[] info = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < info.length; j++) {
                if (info[j] == 9) {
                    shark.i = i;
                    shark.j = j;
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = info[j];
                }
            }
        }
        List<ScanInfo> eatAble = new ArrayList<>();
        while (!(scanEatAble(eatAble)).isEmpty()) {
            if (eatAble.size() == 1) {
                ScanInfo target = eatAble.get(0);
                eat(target);
            } else {
                ScanInfo target = eatAble.stream()
                        .sorted(Comparator.comparing(scanInfo -> ((ScanInfo) scanInfo).time)
                                .thenComparing(scanInfo -> ((ScanInfo) scanInfo).i)
                                .thenComparing(scanInfo -> ((ScanInfo) scanInfo).j)
                        )
                        .findFirst()
                        .get();
                eat(target);
            }
            eatAble.clear();
        }
        System.out.println(totalTime);
    }

    static void eat(ScanInfo target) {
        int i = target.i;
        int j = target.j;
        shark.i = i;
        shark.j = j;
        shark.exp++;
        if (shark.exp == shark.weight) {
            shark.weight++;
            shark.exp=0;
        }
        arr[i][j] = 0;
        totalTime += target.time;
    }

    static List<ScanInfo> scanEatAble(List<ScanInfo> list) {

        Queue<ScanInfo> scanInfos = new LinkedList<>();
        scanInfos.add(new ScanInfo(shark.i, shark.j, 0));
        while (!scanInfos.isEmpty()) {
            ScanInfo scan = scanInfos.poll();
            for (int k = 0; k < 4; k++) {
                int _i = scan.i + di[k];
                int _j = scan.j + dj[k];
                if (isBoundary(_i, _j) && !checked[_i][_j] && arr[_i][_j] <= shark.weight) {
                    checked[_i][_j] = true;
                    ScanInfo _scan = new ScanInfo(_i, _j, scan.time + 1);
                    scanInfos.add(_scan);
                    if (arr[_i][_j] != 0 && arr[_i][_j] < shark.weight) {
                        list.add(_scan);
                    }
                }
            }
        }
        initCheckArrays();
        return list;
    }

    static void initCheckArrays() {
        for (int i = 0; i < checked.length; i++) {
            for (int j = 0; j < checked[i].length; j++) {
                checked[i][j] = false;
            }
        }
    }

    static boolean isBoundary(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < N;
    }

    static class ScanInfo {
        int i;
        int j;
        int time;

        public ScanInfo(int i, int j, int time) {
            this.i = i;
            this.j = j;
            this.time = time;
        }
    }

    static class Shark {
        int i;
        int j;
        int weight = 2;
        int exp=0;
    }
}
