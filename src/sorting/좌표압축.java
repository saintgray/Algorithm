package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//    18870 : 좌표압축
//    ref url : https://www.acmicpc.net/problem/18870
public class 좌표압축 {
    public static void main(String[] args) throws IOException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(in.readLine());
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] sortedArr = Arrays.stream(arr).distinct().sorted().toArray();
        Map<Integer, Integer> seqMap = new HashMap<>();
        for (int i = 0; i < sortedArr.length; i++) {
            int num = sortedArr[i];
            if(seqMap.get(num) == null) {
                seqMap.put(num, i);
            }
        }
        for (int i = 0; i < N; i++) {
            out.write(String.format("%d ",seqMap.get(arr[i])));
        }
        out.flush();
    }
}
