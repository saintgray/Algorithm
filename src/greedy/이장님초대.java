package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

//    9237 : 이장님 초대
//    ref url : https://www.acmicpc.net/problem/9237
public class 이장님초대 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int trees = Integer.parseInt(in.readLine());
        Integer[] arr = Arrays.stream(
                    Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()
                ).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Comparator.reverseOrder());

        int expectedDay = arr[0];
        int remainDays = arr[0];
        for (Integer expectedDays : arr) {
            remainDays--;
            // 심으려는 나무의 자라는 일자가 남은 일자보다 길다면 예상 기간에 더한다.
            boolean needUpdate = expectedDays > remainDays;
            if (needUpdate) {
                expectedDay += expectedDays - remainDays;
                remainDays = expectedDays;
            }
        }
        // 예상 초대일 : 묘목구입일 + 예상 기대 일수
        System.out.println(1 + expectedDay);
    }
}
