package graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//    2667 : 단지번호 붙이기
//    ref url : https://www.acmicpc.net/problem/2667
public class 단지번호붙이기 {
    public static int count = 0;
    public static List<Integer> complexInfo = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        House[][] houses = new House[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            int[] row = Arrays.stream(in.readLine().split("")).mapToInt(e -> Integer.parseInt(e)).toArray();
            for (int j = 0; j < row.length; j++) {
                if (row[j] != 0)
                    houses[i][j+1] = new House(i, j + 1);
            }
        }

        for (int i = 1; i < houses.length; i++) {
            for (int j = 1; j < houses[i].length; j++) {
                if (houses[i][j] != null && !houses[i][j].counted){
                    run(houses, houses[i][j], N);
                    complexInfo.add(count);
                    count = 0;
                }
            }
        }

        System.out.println(complexInfo.size());
        if(!complexInfo.isEmpty()){
            complexInfo.stream().sorted(Comparator.naturalOrder()).forEach(e -> {
                System.out.println(e);
            });
        }
        in.close();
    }

    static void run(House[][] houses, House house, int size) {
        house.counted = true;
        count++;
        int i = house.i;
        int j = house.j;

        House next = null;
        if (i - 1 >= 1 && houses[i - 1][j] != null && !houses[i - 1][j].counted) {
            next = houses[i - 1][j];
            run(houses, next, size);
        }
        if (i + 1 <= size && houses[i + 1][j] != null && !houses[i + 1][j].counted) {
            next = houses[i + 1][j];
            run(houses, next, size);
        }
        if (j - 1 >= 1 && houses[i][j - 1] != null && !houses[i][j - 1].counted) {
            next = houses[i][j - 1];
            run(houses, next, size);
        }
        if (j + 1 <= size && houses[i][j + 1] != null && !houses[i][j + 1].counted){
            next = houses[i][j + 1];
            run(houses, next, size);
        }
    }

    static class House {
        int i;
        int j;
        int code;
        boolean counted;

        public House(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
