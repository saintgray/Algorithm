package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//	10799 : 쇠막대가
//	ref url : https://www.acmicpc.net/problem/10799

public class 쇠막대기 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		Stack<Character> stack = new Stack<Character>();
		char[] arr = in.readLine().toCharArray();
		int pieces = 0;
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if (c == '(') {
				stack.push(c);
			} else {    // ')'
				stack.pop();
				if (arr[i - 1] == '(') {
					pieces += stack.size();
				} else {    // ')'
					pieces++;
				}
			}
		}
		System.out.println(pieces);
	}
}
