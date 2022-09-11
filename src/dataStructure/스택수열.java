package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//	1874 : 스택 수열
//	ref url : https://www.acmicpc.net/problem/1874

public class 스택수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final int MAX = Integer.parseInt(in.readLine());
		Stack<Integer> stack = new Stack<>();
		System.out.println(calculate(in, stack, MAX));
		in.close();
	}

	static StringBuilder calculate(BufferedReader in, Stack<Integer> stack, int MAX) throws IOException {
		StringBuilder sb = new StringBuilder();
		int pushCnt = 0;
		for (int i = 1; i <= MAX; i++) {
			int n = Integer.parseInt(in.readLine());
			if (stack.isEmpty()) {
				for (int j = pushCnt == 0 ? 1 : pushCnt + 1; j <= n; j++) {
					stack.push(j);
					sb.append("+\n");
					pushCnt++;
				}
				stack.pop();
				sb.append("-\n");
			} else {
				int peek = stack.peek();
				if (peek == n) {
					stack.pop();
					sb.append("-\n");
				} else {
					if (pushCnt > n) {
						return new StringBuilder("NO");
					} else {
						for (int j = pushCnt + 1; j <= n; j++) {
							stack.push(j);
							sb.append("+\n");
							pushCnt++;
						}
						stack.pop();
						sb.append("-\n");
					}
				}
			}
		}
		return sb;
	}
}
