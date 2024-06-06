package implemention;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    14891 : 톱니바퀴
//    ref url : https://www.acmicpc.net/source/14891
public class 톱니바퀴 {
    static int[][] gears;
    static int controlGear;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        gears = new int[4][];
        for (int i = 0; i < 4; i++) {
            gears[i] = Arrays.stream(in.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        int control = Integer.parseInt(in.readLine());
        while (control-- > 0) {
            int[] param = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            controlGear = param[0];
            int rotateDirection = param[1];
            rotate(controlGear, rotateDirection);
        }
        System.out.println((int) (
                (gears[0][0] == 0 ? 0 : Math.pow(2, 0)) +
                        (gears[1][0] == 0 ? 0 : Math.pow(2, 1)) +
                        (gears[2][0] == 0 ? 0 : Math.pow(2, 2)) +
                        (gears[3][0] == 0 ? 0 : Math.pow(2, 3)))
        );
    }

    static void rotate(int gearNo, int rotateDirection) {
        int[] gear = gears[gearNo - 1];
        int leftTooth = gear[6];
        int rightTooth = gear[2];

        if (controlGear >= gearNo && gearNo - 2 >= 0) {
            int[] leftGear = gears[gearNo - 2];
            if (leftGear[2] != leftTooth) {
                rotate(gearNo - 1, -1 * rotateDirection);
            }
        }
        if (controlGear <= gearNo && gearNo < 4) {
            int[] rightGear = gears[gearNo];
            if (rightGear[6] != rightTooth) {
                rotate(gearNo + 1, -1 * rotateDirection);
            }
        }
        // 시계방향
        if (rotateDirection == 1) {
            int temp = gear[7];
            for (int i = gear.length - 1; i > 0; i--) {
                gear[i] = gear[i - 1];
            }
            gear[0] = temp;
        }
        // 반시계방향
        else {
            int temp = gear[0];
            for (int i = 0; i < gear.length - 1; i++) {
                gear[i] = gear[i + 1];
            }
            gear[7] = temp;
        }
    }
}
