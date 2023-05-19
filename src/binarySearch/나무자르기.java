package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 나무자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        int[] trees = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(trees);
        int index = N - 1;
        int set = trees[index];
        int sum = 0;
        while (true) {
            if (sum >= M)
                break;
            // 현재 위치의 나무 높이
            int height = trees[index];
            if (height < set) {
                // 같은 높이의 나무까지 탐색
                while (index - 1 > 0 && trees[index - 1] == height)
                    index--;
                set--;
                // 벌목기 높이를 낮춘 뒤 총 잘린 나무 길이 총합
                sum += (N - index);
            } else {
                if ((index == 0 && sum < M) || set != trees[index - 1]) {
                    sum += (N - index);
                    set--;
                } else {
                    index--;
                }
            }
        }
        System.out.println(set);
    }
}
