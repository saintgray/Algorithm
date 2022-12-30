package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 1013 : Contact
// ref url : https://www.acmicpc.net/problem/1013
public class Contact {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc = Integer.parseInt(in.readLine());
		for (int tried = 0; tried < tc; tried++) {
			// (100+1+) : "반드시" 10나오고 "반드시" 0이 한번 이상 나오고 "반드시"1이 한번 이상 나오는 규칙
			// (01) : "반드시" 01이 단 한번 나올것
			// (100+1+ | 01)+ : (100+1+)패턴 또는 (01) 패턴이 최소 한번이상 나오는 패턴
			out.write(in.readLine().matches("^((10)(0)+(1)+|(01))+$") ? "YES\n" : "NO\n");
		}
		out.flush();
	}
}
