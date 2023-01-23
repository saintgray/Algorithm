package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    2473 : 저울
//    ref url : https://www.acmicpc.net/problem/2473
public class 저울 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(in.readLine());
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int total = 1;

        for (int i = 0; i < size; i++) {
            int num = arr[i];
            if(num > total)
                break;
            total += num;
        }
        System.out.println(total);
    }
}

// arr (추의 무게 배열) sort
// 다음 무게추의 무게가 그 전까지 더한 추의 누적합 보다 크다면 누적합이 측정할 수 없는 무게의 최소값이다.
// Ex input
// 7
// 3 1 6 2 7 30 1

// 정렬 후 배열
// 1 1 2 3 6 7 30
// 1 -> 1 까지 측정 가능      ( 누적합(1) > 1 )
// 1 -> 1,2 까지 측정 가능    ( 누적합(2) > 1 ) 
// 2 -> 1,2,3,4 까지 측정 가능  ( 누적합(3) > 2 )
// 3 -> 1,2,3,4,5,6,7 까지 측정 가능 ( 누적합(5) > 3 )
// 6 -> 1 ~ 13 까지 측정 가능 ( 누적합 (8) > 6 )
// 7 -> 1 ~ 20 까지 측정 가능 ( 누적합 (14) > 7 )
// 30 -> (누적합 (21) < 30 ) --> 측정할 수 없는 무게의 최소값
// 30 으로 만들 수 있는 무게를 보면
// 1 ~ 20, 31~ 50 까지 측정 가능 -> 측정 할 수 없는 물체 무게의 양의 정수 최소값 = 21 ( = 그 전까지의 누적 합 )
