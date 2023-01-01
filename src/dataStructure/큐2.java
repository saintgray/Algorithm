package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;

//    18258 : 큐2
//    ref url : https://www.acmicpc.net/problem/18258
public class 큐2 {

	static final String CASE1 = "push";
	static final String CASE2 = "front";
	static final String CASE3 = "back";
	static final String CASE4 = "size";
	static final String CASE5 = "empty";
	static final String CASE6 = "pop";

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		Deque<Integer> queue = new LinkedList<Integer>();

		int commands = Integer.parseInt(in.readLine());

		for (int i = 0; i < commands; i++) {
			String command = in.readLine();
			if (command.contains(CASE1)) {
				queue.add(Integer.parseInt(command.split(" ")[1]));
			} else {
				switch (command) {
					case CASE2: {
						out.write(String.valueOf(queue.isEmpty() ? -1 : queue.peek()));
						break;
					}
					case CASE3: {
						out.write(String.valueOf(queue.isEmpty() ? -1 : queue.peekLast()));
						break;
					}
					case CASE4: {
						out.write(String.valueOf(queue.size()));
						break;
					}
					case CASE5: {
						out.write(String.valueOf(queue.isEmpty() ? 1 : 0));
						break;
					}
					case CASE6: {
						out.write(String.valueOf(queue.isEmpty() ? -1 : queue.poll()));
						break;
					}
				}
				out.newLine();
			}
		}
		out.flush();
	}
}
