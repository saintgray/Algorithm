package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RGB거리2 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int[][] cost;
    static char[] colors = new char[]{'R', 'G', 'B'};
    static int min = Integer.MAX_VALUE;
    static int INF = 1000001;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            cost[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int ci = 0; ci < 3; ci++) {
            char color = colors[ci];
            Optimizer[] optimizers = new Optimizer[N + 1];
            optimizers[1] = new Optimizer();
            if(color == 'R') optimizers[1].onRed = cost[0][0];
            if(color == 'G') optimizers[1].onGreen = cost[0][1];
            if(color == 'B') optimizers[1].onBlue = cost[0][2];
            for (int i = 2; i <= N; i++) {
                int[] _cost = cost[i - 1];
                int red = _cost[0];
                int green = _cost[1];
                int blue = _cost[2];
                Optimizer cur = new Optimizer(_cost[0], _cost[1], _cost[2]);
                Optimizer prev = optimizers[i - 1];
                cur.onRed = Math.min(prev.onGreen >= INF ? INF : prev.onGreen + red, prev.onBlue >= INF ? INF : prev.onBlue + red);
                cur.onGreen = Math.min(prev.onRed >= INF ? INF : prev.onRed + green, prev.onBlue >= INF ? INF : prev.onBlue + green);
                cur.onBlue = Math.min(prev.onGreen >= INF ? INF : prev.onGreen + blue, prev.onRed >= INF ? INF : prev.onRed + blue);
                optimizers[i] = cur;
            }
            // N 번째 집까지 칠했을 때 최소 비용 정보
            Optimizer result = optimizers[N];
            for (int i = 0; i < 3; i++) {
                if (colors[i] == color) continue;
                min = Math.min(min, result.getOptimizedCostOf(i));
            }
        }
        System.out.println(min);
    }


    static class Optimizer {
        int onRed;
        int onGreen;
        int onBlue;

        int getOptimizedCostOf(int ci) {
            return ci == 0 ? this.onRed : ci ==1 ? this.onGreen : this.onBlue;
        }

        public Optimizer(int onRed, int onGreen, int onBlue) {
            this.onRed = onRed;
            this.onGreen = onGreen;
            this.onBlue = onBlue;
        }

        public Optimizer() {
            this.onRed = INF;
            this.onGreen = INF;
            this.onBlue = INF;
        }
    }
}
