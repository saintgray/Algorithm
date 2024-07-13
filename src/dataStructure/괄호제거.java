package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

//    2800 : 괄호 제거
//    ref url : https://www.acmicpc.net/problem/2800
public class 괄호제거 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static char[] input;

    public static void main(String[] args) throws IOException {
        input = in.readLine().toCharArray();
        Stack<Bracket> stack = new Stack<>();
        List<Bracket> brackets = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            char c = input[i];
            if (c == '(') {
                Bracket bracket = new Bracket(i);
                stack.push(bracket);
            } else if (c == ')') {
                Bracket bracket = stack.pop();
                bracket.ed = i;
                brackets.add(bracket);
            }
        }

        List<String> result = new ArrayList<>();
        for (int count = 1; count <= brackets.size(); count++) {
            track(count, 0, 0, result, new HashSet<>(), brackets);
        }

        result.sort(Comparator.naturalOrder());
        for (int i = 0; i < result.size(); i++) {
            if(i == 0 || !result.get(i).equals(result.get(i-1))) {
                out.write(result.get(i));
                out.newLine();
            }
        }
        out.flush();
    }

    static void track(int maxDepth,
                      int depth,
                      int idx,
                      List<String> result,
                      Set<Integer> ignoreIdx,
                      List<Bracket> brackets) {
        if (maxDepth == depth) {
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < input.length; i++) {
                if (!ignoreIdx.contains(i)) buf.append(input[i]);
            }
            result.add(buf.toString());
        }

        for (int i = idx; i < brackets.size(); i++) {
            Bracket b = brackets.get(i);
            ignoreIdx.add(b.st);
            ignoreIdx.add(b.ed);
            track(maxDepth, depth + 1, i+1, result, ignoreIdx, brackets);
            ignoreIdx.remove(b.st);
            ignoreIdx.remove(b.ed);
        }
    }

    static class Bracket {
        int st;
        int ed;

        public Bracket(int st) {
            this.st = st;
        }
    }
}