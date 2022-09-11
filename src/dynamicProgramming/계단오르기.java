package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    2579 : 계단 오르기
//    ref url : https://www.acmicpc.net/problem/2579

public class 계단오르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int step = Integer.parseInt(in.readLine());
        Step[] steps = new Step[step];
        for (int i = 0; i < step; i++)
            steps[i] = new Step(Integer.parseInt(in.readLine()));
        steps[0].maxPointNotStepOnPrevStep = steps[0].point;
        steps[0].maxPointStepOnPrevStep = steps[0].point;
        steps[0].maxPoint = steps[0].point;
        for (int i = 1; i < step; i++) {
            steps[i].maxPointNotStepOnPrevStep = i-2 >= 0 ? steps[i - 2].maxPoint + steps[i].point : steps[i].point;
            steps[i].maxPointStepOnPrevStep = steps[i - 1].maxPointNotStepOnPrevStep + steps[i].point;
            steps[i].maxPoint = Math.max(steps[i].maxPointNotStepOnPrevStep, steps[i].maxPointStepOnPrevStep);
        }
        System.out.println(steps[steps.length - 1].maxPoint);
        in.close();
    }

    static class Step {
        int point;
        int maxPointNotStepOnPrevStep;
        int maxPointStepOnPrevStep;
        int maxPoint;

        public Step(int point) {
            this.point = point;
        }
    }
}
