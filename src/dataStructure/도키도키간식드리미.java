package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

//    12789 : 도키도키 간식드리미
//    ref url : https://www.acmicpc.net/problem/12789
public class 도키도키간식드리미 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] seq = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Stack<Integer> line = new Stack<>();
        Stack<Integer> tempLine = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            line.push(seq[i]);
        }
        int lastPotluckSeq = 0;
        while(true) {
            if (!line.isEmpty()) {
                if (line.peek() == lastPotluckSeq + 1) {
                    line.pop();
                    lastPotluckSeq++;
                } else if (!tempLine.isEmpty() && tempLine.peek() == lastPotluckSeq + 1) {
                    tempLine.pop();
                    lastPotluckSeq++;
                } else {
                    while(!line.isEmpty() && line.peek() != lastPotluckSeq + 1) {
                        tempLine.push(line.pop());
                    }
                }
            } else {
                while(!tempLine.isEmpty() && tempLine.peek() == lastPotluckSeq + 1) {
                    lastPotluckSeq++;
                    tempLine.pop();
                }
                break;
            }
        }
        System.out.println(line.isEmpty() && tempLine.isEmpty() ? "Nice" : "Sad");
    }

}
