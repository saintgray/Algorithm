package graphTraversal;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//    7567 : 토마토
//    ref url : https://www.acmicpc.net/problem/7576

public class 토마토 {
    static int days = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int box[] = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        int N = box[1];
        int M = box[0];

        Tomato[][] tomatoes = new Tomato[N + 1][M + 1];
        List<Tomato> anchors = new ArrayList<>();
        boolean allRipes = true;
        for (int i = 1; i < N + 1; i++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
            for (int j = 0; j < row.length; j++) {
                int stat = row[j];
                if (allRipes && stat == 0) {
                    allRipes = false;
                }
                if (stat != -1) {
                    tomatoes[i][j + 1] = new Tomato(i, j + 1, stat == 1 ? true : false);
                    if(tomatoes[i][j+1].ripe)
                        anchors.add(tomatoes[i][j+1]);
                }
            }
        }

        if(allRipes){
            System.out.println(0);
        }else{
            run(tomatoes, anchors.toArray(new Tomato[anchors.size()]), N, M);
            System.out.println(allTomatoRipes(tomatoes) ? days : -1);
        }

        in.close();
    }

    static void run(Tomato[][] tomatoes, Tomato[] anchors, int N, int M) {

        List<Tomato> nextAnchors = new ArrayList<>();
        for (Tomato tomato : anchors) {
            int i = tomato.i;
            int j = tomato.j;
            if (i - 1 >= 1 && tomatoes[i - 1][j] != null && !tomatoes[i - 1][j].ripe) {
                tomatoes[i - 1][j].ripe = true;
                nextAnchors.add(tomatoes[i - 1][j]);
            }
            if (i + 1 <= N && tomatoes[i + 1][j] != null && !tomatoes[i + 1][j].ripe) {
                tomatoes[i + 1][j].ripe = true;
                nextAnchors.add(tomatoes[i + 1][j]);
            }
            if (j - 1 >= 1 && tomatoes[i][j - 1] != null && !tomatoes[i][j - 1].ripe) {
                tomatoes[i][j - 1].ripe = true;
                nextAnchors.add(tomatoes[i][j - 1]);
            }
            if (j + 1 <= M && tomatoes[i][j + 1] != null && !tomatoes[i][j + 1].ripe) {
                tomatoes[i][j + 1].ripe = true;
                nextAnchors.add(tomatoes[i][j +1]);
            }
        }
        if (!nextAnchors.isEmpty()) {
            days++;
            run(tomatoes, nextAnchors.toArray(new Tomato[nextAnchors.size()]), N, M);
        }
    }

    static boolean allTomatoRipes(Tomato[][] tomatoes) {
        boolean result = true;
        for (int i = 1; i < tomatoes.length; i++) {
            for (int j = 1; j < tomatoes[i].length; j++) {
                Tomato tomato = tomatoes[i][j];
                if (tomato != null && !tomato.ripe) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    static class Tomato {
        int i;
        int j;
        boolean ripe;

        public Tomato(int i, int j, boolean ripe) {
            this.i = i;
            this.j = j;
            this.ripe = ripe;
        }
    }
}
