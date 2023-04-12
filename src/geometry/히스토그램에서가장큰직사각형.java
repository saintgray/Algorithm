package geometry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//    6549 : 히스토그램에서 가장 큰 직사각형
//    ref url : https://www.acmicpc.net/problem/6549
public class 히스토그램에서가장큰직사각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String input;
        while (!"0".equals(input = in.readLine())) {
            String[] params = input.split(" ");
            int N = Integer.parseInt(params[0]);
            long[] bricks = new long[N];
            for (int i = 1; i < params.length; i++)
                bricks[i - 1] = Long.parseLong(params[i]);
            out.write(String.format("%s\n", solve(bricks, 0, N - 1)));
        }
        out.flush();
    }


    static long solve(long[] bricks, int low, int high) {
        if (low == high) {
            return bricks[low];
        } else {
            int mid = (low + high) / 2;
            return Math.max(mergedArea(low, mid, high, bricks), Math.max(solve(bricks, low, mid), solve(bricks, mid + 1, high)));
        }
    }

    static long mergedArea(int low, int mid, int high, long[] bricks) {
        int leftIdx = mid;
        int rightIdx = mid;
        long area = bricks[mid];
        // 현재 최대 넓이 영역의 높이
        long height = bricks[mid];
        while (leftIdx > low && rightIdx < high) {
            // 이전, 이후 막대 높이
            long prevHeight = bricks[leftIdx - 1];
            long nextHeight = bricks[rightIdx + 1];
            // 갱신될 높이
            if (prevHeight >= nextHeight) {
                height = Math.min(height, prevHeight);
                leftIdx--;
            } else {
                height = Math.min(height, nextHeight);
                rightIdx++;
            }
            area = Math.max(area, ((long) (rightIdx - leftIdx + 1)) * height);
        }

        // 탐색되지 않은 나머지 영역 탐색
        while (rightIdx < high) {
            rightIdx++;
            height = Math.min(height, bricks[rightIdx]);
            area = Math.max(area, ((long) (rightIdx - low + 1)) * height); // 넓이 갱신
        }
        while (leftIdx > low) {
            leftIdx--;
            height = Math.min(height, bricks[leftIdx]);
            area = Math.max(area, ((long) (high - leftIdx + 1)) * height);
        }
        return area;
    }
}
