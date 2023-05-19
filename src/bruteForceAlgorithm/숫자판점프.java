package bruteForceAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 숫자판점프 {

    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[][] b = new String[5][5];
        for (int i = 0; i < 5; i++)
            b[i] = in.readLine().split(" ");
        List<String> list = new ArrayList<>();
        Set<String> resultSet = new HashSet<>();
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                run(i,j,b,list, resultSet);
            }
        }
        System.out.println(resultSet.size());
    }

    static void run(int x, int y, String[][] b, List<String> list, Set<String> resultSet) {
        if (list != null && list.size() == 6) {
            resultSet.add(String.join("", list));
        } else {
            for (int i = 0; i < 4; i++) {
                if (isBoundary(x + dx[i], y + dy[i])) {
                    list.add(b[x + dx[i]][y + dy[i]]);
                    run(x + dx[i], y + dy[i], b, list, resultSet);
                    list.remove(list.size() - 1);
                }
            }
        }
    }


    static boolean isBoundary(int x, int y) {
        return x >= 0 && y >= 0 && x < 5 && y < 5;
    }
}
