package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    14889 : 스타트와 링크
//    ref url : https://www.acmicpc.net/problem/14889
//    tag : bruteforce
//    * 삼성 SW 역량 테스트 기출 문제 2
public class 스타트와링크 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] player;
    static int[][] ability;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        player = new int[N + 1];
        ability = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        for (int n = 1; n <= N; n++) {
            player[n] = n;
        }
        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                ability[i + 1][j + 1] = row[j];
            }
        }
        track(0, 1, new int[N / 2]);
        System.out.println(min);
    }

    static void track(int players, int no, int[] team) {
        if (players == N / 2) {
            calculate(team);
            return;
        }
        for (int n = no; n <= N; n++) {
            if (visited[n]) continue;
            visited[n] = true;
            team[players] = n;
            track(players + 1, n, team);
            visited[n] = false;
            team[players] = 0;
        }
    }

    static void calculate(int[] team) {
        int[] team2 = new int[N / 2];
        int idx2 = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) team2[idx2++] = i;
        }
        int team1Ability = 0;
        int team2Ability = 0;
        for (int i = 0; i < N / 2; i++) {
            for (int j = i + 1; j < N / 2; j++) {
                team1Ability += (ability[team[i]][team[j]] + ability[team[j]][team[i]]);
                team2Ability += (ability[team2[i]][team2[j]] + ability[team2[j]][team2[i]]);
            }
        }
        min = Math.min(min, Math.abs(team1Ability - team2Ability));
    }
}
