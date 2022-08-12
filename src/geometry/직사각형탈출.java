package geometry;

//    문제
//    한수는 지금 (x, y)에 있다.
//    직사각형은 각 변이 좌표축에 평행하고,
//    왼쪽 아래 꼭짓점은 (0, 0),
//    오른쪽 위 꼭짓점은 (w, h)에 있다.
//    직사각형의 경계선까지 가는 거리의 최솟값을 구하는 프로그램을 작성하시오.
//
//    입력
//    첫째 줄에 x, y, w, h가 주어진다.
//
//    출력
//    첫째 줄에 문제의 정답을 출력한다.
//
//    제한
//    1 ≤ w, h ≤ 1,000
//    1 ≤ x ≤ w-1
//    1 ≤ y ≤ h-1
//    x, y, w, h는 정수


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 직사각형탈출 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = in.readLine().split(" ");
        Integer hyunsooX = Integer.parseInt(input[0]);
        Integer hyunsooY = Integer.parseInt(input[1]);
        Integer targetX = Integer.parseInt(input[2]);
        Integer targetY = Integer.parseInt(input[3]);

        int result = 0;
        if (hyunsooX > (double) targetX / 2) {
            result = Math.abs(targetX - hyunsooX);
        } else {
            result = hyunsooX;
        }
        Integer shortCutY = Math.min(
                Math.abs(hyunsooY - targetY)
                , Math.abs(hyunsooY));
        result = shortCutY < result ? shortCutY : result;
        System.out.println(result);
        in.close();
    }
}
