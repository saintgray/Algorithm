package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//    2573 : 빙산
//    ref url : https://www.acmicpc.net/problem/2573
public class 빙산 {
    static int X = 0;
    static int Y = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] param = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        X = param[1];
        Y = param[0];
        IceBerg[][] plane = new IceBerg[Y][X];
        for (int i = 0; i < Y; i++) {
            int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < X; j++)
                plane[i][j] = new IceBerg(j, i, params[j]);
        }
        int year = 0;
        boolean seperated = false;
        while (!allMelted(plane)) {
            runMelt(plane);
            year++;
            if (seperated(plane)) {
                seperated = true;
                break;
            }
            Arrays.stream(plane).forEach(e -> Arrays.stream(e).forEach(e2 -> e2.checked = false));
        }
        System.out.println(seperated ? year : 0);
        in.close();
    }

    private static boolean seperated(IceBerg[][] plane) {
        int regionCnt = 0;
        Queue<IceBerg> queue = new LinkedList<>();
        for (int i = 0; i < Y; i++)
            for (int j = 0; j < X; j++)
                if (plane[i][j].height > 0 && !plane[i][j].checked) {
                    plane[i][j].checked = true;
                    queue.add(plane[i][j]);
                    regionCnt++;
                    while (!queue.isEmpty()) {
                        IceBerg e = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            if (e.isBoundary(dx[k], dy[k]) && plane[e.y + dy[k]][e.x + dx[k]].height > 0 && !plane[e.y + dy[k]][e.x + dx[k]].checked) {
                                plane[e.y + dy[k]][e.x + dx[k]].checked = true;
                                queue.add(plane[e.y + dy[k]][e.x + dx[k]]);
                            }
                        }
                    }
                }
        return regionCnt > 1;
    }

    private static void runMelt(IceBerg[][] plane) {
        List<IceBerg> target = new ArrayList<>();
        for (int i = 0; i < Y; i++)
            for (int j = 0; j < X; j++)
                for (int k = 0; k < 4; k++)
                    if (plane[i][j].isBoundary(dx[k], dy[k]) && plane[i + dy[k]][j + dx[k]].height == 0)
                        target.add(plane[i][j]);
        if(target.size() > 0)
            target.forEach(iceBerg -> iceBerg.height -= iceBerg.height == 0 ? 0 : 1);
    }

    private static boolean allMelted(IceBerg[][] plane) {
        for (int i = 0; i < Y; i++)
            for (int j = 0; j < X; j++)
                if (plane[i][j].height != 0) return false;
        return true;
    }


    public static class IceBerg {
        int x;
        int y;
        int height;
        boolean checked;

        public IceBerg(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        boolean isBoundary(int dx, int dy) {
            return this.x + dx >= 0 && this.x + dx < X && this.y + dy >= 0 && this.y + dy < Y;
        }
    }
}
