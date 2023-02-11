package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    1149 : RGB 거리
//    ref url : https://www.acmicpc.net/problem/1149
public class RGB거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        House[][] houses = new House[Integer.parseInt(in.readLine())][3];
        for (int i = 0; i < houses.length; i++) {
            int[] info = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < info.length; j++) {
                houses[i][j] = new House(j == 0 ? 'R' : j == 1 ? 'G' : 'B', info[j]);
                if (i == 0) {
                    houses[i][j].minimumCost = houses[i][j].fee;
                } else {
                    // 마지막으로 칠한 색깔의 집에 대한 최소 비용 구하기
                    House[] prevHouseInfo = houses[i - 1];
                    // 마지막으로 칠하는 집이 R' 일 경우
                    // 전 집의 G 최소 비용과 B 최소 비용을 R' 과 더했을때 최소값
                    // G' B' 에 대해서도 동일한 기준 적용
                    int fee = houses[i][j].fee;
                    houses[i][j].minimumCost = j == 0 ?
                            Math.min(fee + prevHouseInfo[1].minimumCost, fee + prevHouseInfo[2].minimumCost) :
                            j == 1 ? Math.min(fee + prevHouseInfo[0].minimumCost, fee + prevHouseInfo[2].minimumCost) :
                                    Math.min(fee + prevHouseInfo[0].minimumCost, fee + prevHouseInfo[1].minimumCost);
                }
            }
        }
        System.out.println(Arrays.stream(houses[houses.length - 1]).mapToInt(e -> e.minimumCost).min().getAsInt());
        in.close();
    }

    static class House {
        char color;
        int fee;
        int minimumCost;

        public House(char color, int fee) {
            this.color = color;
            this.fee = fee;
        }
    }
}
