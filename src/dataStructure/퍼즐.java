package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//    1525 : 퍼즐
//    ref url : https://www.acmicpc.net/problem/1525
public class 퍼즐 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    // 퍼즐의 상태로부터 정리된 상태를 만들기까지 최소 이동횟수
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        char[] puzzle = new char[9];
        char[] row = new char[3];
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) row = in.readLine().replaceAll(" ", "").toCharArray();
            puzzle[i] = row[i % 3];
        }
        map.put(String.valueOf(puzzle), 0);
        int blankIndex = 0;
        for (int i = 0; i < puzzle.length; i++) {
            if(puzzle[i] == '0') blankIndex = i;
        }
        Queue<Stat> queue = new LinkedList<>();
        queue.add(new Stat(puzzle, blankIndex, 0));
        while(!queue.isEmpty()) {
            Stat poll = queue.poll();
            char[] stat = poll.puzzle;
            int zeroIndex = poll.zeroIndex;
            int moved = poll.moved;
            Stat left = getTarget(stat, zeroIndex, zeroIndex - 1, moved, true); // 좌
            Stat right = getTarget(stat, zeroIndex, zeroIndex + 1, moved, true); // 우
            Stat top =  getTarget(stat, zeroIndex, zeroIndex - 3, moved, false); // 상
            Stat bottom = getTarget(stat, zeroIndex, zeroIndex + 3, moved, false); // 하
            if (left != null) queue.add(left);
            if (right != null) queue.add(right);
            if (top != null) queue.add(top);
            if (bottom != null) queue.add(bottom);
        }
        // track(puzzle, blankIndex, 0);
        Integer min = map.get("123456780");
        System.out.println(min == null ? -1 : min);
    }

    static Stat getTarget(char[] stat, int blankIndex, int toIndex, int moved, boolean rowCheck) {
        int row = (blankIndex / 3) + 1;
        if (isBoundary(toIndex) && (!rowCheck || ((toIndex) / 3) + 1 == row)) {
            // 숫자와 빈칸 교환
            char num = stat[toIndex];
            char[] next = String.valueOf(stat).toCharArray();
            next[blankIndex] = num;
            next[toIndex] = '0';
            Integer count = map.get(String.valueOf(next));
            if (count == null || count > moved + 1) {
                map.put(String.valueOf(next), moved + 1);
                return new Stat(next, toIndex, moved + 1);
            }
        }
        return null;
    }

    static class Stat {
        char[] puzzle;
        int zeroIndex;
        int moved;

        public Stat(char[] puzzle, int zeroIndex, int moved) {
            this.puzzle = puzzle;
            this.zeroIndex = zeroIndex;
            this.moved = moved;
        }
    }

    static boolean isBoundary(int index) {
        return index >= 0 && index < 9;
    }
}