package backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//    1174 : 줄어드는 수
//    ref url : https://www.acmicpc.net/problem/1174
public class 줄어드는수 {

    static List<Integer> decNums = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(in.readLine());
        // 가장 큰 줄어드는 수 = 9876543210 (10 자리수)
        // 1~9 자리수까지 줄어드는 수를 tracking (10 자리수는 int 범위를 넘어서므로 제외, 최종 결과 판단에서 보정)
        for (int digit = 1; digit <= 9; digit++) {
            int[] arr = new int[digit];
            boolean[] checked = new boolean[digit];
            // digit 자리수의 최대 자리수의 최소값은 digit - 1
            for (int i = digit-1; i <= 9; i++) {
                track(arr,checked, i, 1);
            }
        }
        // 총 1022개 + 1개 (10 자리수의 줄어드는 수)
        // System.out.println(decNums.size());
        System.out.println(N > 1023 ? -1 : N == 1023 ? 9876543210L : decNums.get(N - 1));
    }

    static void track(int[] arr, boolean[] checked, int num, int depth) {
        arr[depth-1] = num;
        if(depth == arr.length) {
            decNums.add(Integer.parseInt(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(""))));
            return;
        }
        for (int i = 0; i < num; i++) {
            if(!checked[depth]) {
                checked[depth] = true;
                track(arr, checked, i, depth+1);
                checked[depth] = false;
            }
        }
    }
}
