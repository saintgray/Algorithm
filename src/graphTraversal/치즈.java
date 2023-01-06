package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//    2636 : 치즈
//    ref url : https://www.acmicpc.net/problem/2636
public class 치즈 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int X = 0;
    static int Y = 0;
    static int hour = 0;
    static Set<Node> notHasAirHoleCheese = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        X = params[0];
        Y = params[1];
        Node[][] map = new Node[X][Y];

        for (int i = 0; i < X; i++) {
            int[] row = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < Y; j++) {
                map[i][j] = new Node(row[j], i, j);
                if(row[j] == 1)
                    notHasAirHoleCheese.add(map[i][j]);
            }
        }
        // 바깥공기 주입
        Queue<Node> queue = new LinkedList<>();
        queue.add(map[0][0]);
        injectAir(queue, map);
        runTime(map);
        System.out.println(String.format("%s\n%s", hour, notHasAirHoleCheese.size()));

    }


    static void runTime(Node[][] map) {

        hour++;
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (map[i][j] != null && map[i][j].hasAirHole) {
                    map[i][j].num = 2;
                    queue.add(map[i][j]);
                    notHasAirHoleCheese.remove(map[i][j]);
                }
            }
        }
        // 공기 주입
        injectAir(queue, map);
        // airHole
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (map[i][j] != null) {
                    for (int k = 0; k < 4; k++) {
                        int nextX = i + dx[k];
                        int nextY = j + dy[k];
                        // 3면 혹은 4면중 하나라도 바깥 공기와 맞닿아있을 경우
                        if (isBoundary(nextX, nextY) && map[nextX][nextY].num == 2) {
                            map[i][j].hasAirHole = true;
                            break;
                        }
                    }
                }
            }
        }
        if (!notHasAirHoleCheese.stream().allMatch(e -> e.hasAirHole))
            runTime(map);
    }


    static void injectAir(Queue<Node> queue, Node[][] map){
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nextX = poll.x + dx[k];
                int nextY = poll.y + dy[k];
                if (isBoundary(nextX, nextY) && map[nextX][nextY].num == 0) {
                    queue.add(map[nextX][nextY]);
                    map[nextX][nextY].num = 2;
                }
            }
        }
    }


//    static void printMap(Node[][] map) {
//        System.out.println("===================================");
//        for (Node[] row : map) {
//            for (Node node : row) {
//                System.out.printf(String.format("%s ", node.num == 2  ? "0" : node.hasAirHole ? "*" : node.num));
//            }
//            System.out.println();
//        }
//    }


    static class Node {

        int num;
        int x;
        int y;
        boolean hasAirHole;

        public Node(int num, int x, int y) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static boolean isBoundary(int x, int y) {
        return x >= 0 && y >= 0 && x < X && y < Y;
    }

}
