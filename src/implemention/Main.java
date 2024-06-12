package implemention;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    static int[] gear1;
    static int[] gear2;
    static int[] gear3;
    static int[] gear4;
    static int[][] gears;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        gears = new int[4][];
        for (int i = 0; i < 4; i++) {
            gears[i] = Arrays.stream(in.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        int control = Integer.parseInt(in.readLine());
        while(control-- > 0) {
            int[] param = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int gearNum = param[0];
            int rotateDirection = param[1];
            rotate(gearNum, rotateDirection);
        }
        System.out.println(Arrays.stream(gears).map(gear -> gear[0]).reduce(Integer::sum).get());
    }

    static void rotate(int gearNum, int rotateDirection) {
        int[] gear = gears[gearNum-1];
        int leftTooth = gear[6];
        int rightTooth = gear[2];
        if(gearNum > 1) {
            int[] leftGear = gears[gearNum-2];
            if(leftGear[2] != leftTooth) {
                rotate(gearNum-2, rotateDirection == 1 ? -1 : 1);
            }
        }
        if(gearNum < 4) {
            int[] rightGear = gears[gearNum];
            if(rightGear[2] != rightTooth) {
                rotate(gearNum, rotateDirection == 1 ? -1 : 1);
            }
        }
        // 시계방향
        if(rotateDirection == 1) {
            int temp = gear[7];
            for (int i = 1; i < gear.length; i++) {
                gear[i] = gear[i-1];
                gear[0] = temp;
            }
        } else {
            int temp = gear[0];
            for (int i = 0; i < gear.length - 1; i++) {
                gear[i] = gear[i+1];
                gear[7] = temp;
            }
        }

        System.out.println();
        for (int i = 0; i < gear.length; i++) {
            System.out.print(gear[i]);
        }
        System.out.println();
    }
}
