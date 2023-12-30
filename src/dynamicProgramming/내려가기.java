package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class 내려가기 {
    static int N = 0;
    static int[] dx = {-1, -1, -1};
    static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        Node[][] dp = new Node[N][3];
        for (int i = 0; i < N; i++) {
            int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < arr.length; j++) {
                dp[i][j] = new Node(i, j, arr[j]);
            }
        }
        for (int i = 0; i < 3; i++) {
            Node node = dp[0][i];
            int score = node.score;
            node.minScore = score;
            node.maxScore = score;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                Node node = dp[i][j];
                for (int k = 0; k < 3; k++) {
                    if (isBoundary(i + dx[k], j + dy[k])) {
                        Node upper = dp[i + dx[k]][j + dy[k]];
                        node.maxScore = Math.max(upper.maxScore + node.score, node.maxScore);
                        node.minScore = Math.min(upper.minScore + node.score, node.minScore);
                    }
                }
            }
        }
        Node[] lastRow = dp[N - 1];
        Optional<Node> maxNode = Arrays.stream(lastRow).max(Comparator.comparing(node -> node.maxScore));
        Optional<Node> minNode = Arrays.stream(lastRow).min(Comparator.comparing(node -> node.minScore));
        maxNode.ifPresent(node -> System.out.printf("%d %d%n", node.maxScore, minNode.get().minScore));
    }

    static class Node {
        int x;
        int y;
        int score;
        int maxScore;
        int minScore;

        public Node(int x, int y, int score) {
            this.x = x;
            this.y = y;
            this.score = score;
            this.maxScore = Integer.MIN_VALUE;
            this.minScore = Integer.MAX_VALUE;
        }
    }

    static boolean isBoundary(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < 3;
    }

}
