package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//    14502 : 연구소
//    ref url : https://www.acmicpc.net/problem/14502
public class 연구소 {
    static int safeZones = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        int N = params[0];
        int M = params[1];
        int[][] lab = new int[N + 1][M + 1];
        List<String> empty = new ArrayList<>();
        List<String> hostVirus = new ArrayList<>();
        Queue<String> viruses = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
            for (int j = 0; j < row.length; j++) {
                int stat = row[j];
                lab[i + 1][j + 1] = stat;
                if (stat == 0)
                    empty.add(String.format("%d,%d", i + 1, j + 1));
                if (stat == 2){
                    String coord = String.format("%d,%d", i + 1, j + 1);
                    viruses.add(coord);
                    hostVirus.add(coord);
                }
            }
        }
        for (int i = 0; i < empty.size(); i++) {
            for (int j = i + 1; j < empty.size(); j++) {
                for (int k = j + 1; k < empty.size(); k++) {
                    int[] firstBrickPosition = getCoordinate(empty.get(i));
                    int[] secondBrickPosition = getCoordinate(empty.get(j));
                    int[] thirdBrickPosition = getCoordinate(empty.get(k));
                    lab[firstBrickPosition[0]][firstBrickPosition[1]] = 1;
                    lab[secondBrickPosition[0]][secondBrickPosition[1]] = 1;
                    lab[thirdBrickPosition[0]][thirdBrickPosition[1]] = 1;
                    runInfested(lab, viruses, N,M);
                    safeZones = Math.max(safeZones, countSafeZone(lab, N, M));
                    clearLab(lab, hostVirus, viruses, empty.get(i), empty.get(j), empty.get(k));
                }
            }
        }
        System.out.println(safeZones);
        in.close();
    }

    static void runInfested(int[][] copy, Queue<String> q, int N, int M) {
        if (q.isEmpty())
            return;
        int[] coord = getCoordinate(q.poll());
        int i = coord[0];
        int j = coord[1];
        if (i - 1 >= 1 && copy[i - 1][j] == 0) {
            q.add(String.format("%d,%d", i - 1, j));
            copy[i - 1][j] = 2;
        }
        if (i + 1 <= N && copy[i + 1][j] == 0) {
            q.add(String.format("%d,%d", i + 1, j));
            copy[i + 1][j] = 2;
        }
        if (j - 1 >= 1 && copy[i][j - 1] == 0) {
            q.add(String.format("%d,%d", i, j - 1));
            copy[i][j - 1] = 2;
        }
        if (j + 1 <= M && copy[i][j + 1] == 0) {
            q.add(String.format("%d,%d", i, j + 1));
            copy[i][j + 1] = 2;
        }

        runInfested(copy, q, N, M);
    }

    static void clearLab(int[][] lab, List<String> hostVirus, Queue<String> viruses, String... bricks){
        for (int i = 1; i < lab.length; i++) {
            for (int j = 1; j < lab[i].length; j++) {
                if (lab[i][j] == 2)
                    lab[i][j] = 0;
            }
        }
        // reset host virus
        for(String coordStr : hostVirus){
            int[] coord = getCoordinate(coordStr);
            lab[coord[0]][coord[1]] = 2;
            viruses.add(coordStr);
        }
        // reset briks
        for(String coordStr : bricks){
            int[] coord = getCoordinate(coordStr);
            lab[coord[0]][coord[1]] = 0;
        }
    }

    static int[] getCoordinate(String str) {
        return Arrays.stream(str.split(",")).mapToInt(e -> Integer.parseInt(e)).toArray();
    }

    static int countSafeZone(int[][] copy, int N, int M) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (copy[i][j] == 0)
                    count++;
            }
        }
        return count;
    }
}

// 함께 보기
// 브루트포스 알고리즘
