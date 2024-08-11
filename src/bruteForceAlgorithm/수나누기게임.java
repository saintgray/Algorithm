package bruteForceAlgorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

//    27112 : 수 나누기 게임
//    ref url : https://www.acmicpc.net/problem/27112
public class 수나누기게임 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int players;
    static Gamer[] gamers;

    public static void main(String[] args) throws IOException {
        players = Integer.parseInt(in.readLine());
        gamers = new Gamer[1000001];
        int[] playerCards = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxNumCard = Integer.MIN_VALUE;
        for (int i = 0; i < players; i++) {
            Gamer gamer = new Gamer();
            gamer.idx = i;
            gamer.card = playerCards[i];
            gamers[gamer.card] = gamer;
            maxNumCard = Math.max(maxNumCard, playerCards[i]);
        }
        fight(maxNumCard, playerCards);
        Gamer[] result = Arrays.stream(gamers)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(g -> g.idx, Comparator.naturalOrder()))
                .toArray(Gamer[]::new);
        for (Gamer gamer : result) {
            out.write(gamer.score + " ");
        }
        out.flush();
    }

    static void fight(int N, int[] playerCards) {
        for (int i = 0; i < playerCards.length; i++) {
            int num = playerCards[i];
            Gamer me = gamers[num];
            if (me == null) continue;
            for (int _n = 2 * num; _n <= N; _n += num) {
                Gamer challenger = gamers[_n];
                if(challenger == null) continue;
                challenger.score--;
                me.score++;
            }
        }
    }

    static class Gamer {
        int idx;
        int card;
        int score;
    }
}
