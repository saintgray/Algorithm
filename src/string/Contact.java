package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Contact {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int testCode = Integer.parseInt(in.readLine());
		String[] result = new String[testCode];

		for (int tried = 0; tried < result.length; tried++) {

			String test = in.readLine();

			// (100+1+) : "반드시" 10나오고 "반드시" 0이 한번 이상 나오고 "반드시"1이 한번 이상 나오는 규칙
			// (01) : "반드시" 01이 단 한번 나올것

			// (100+1+ | 01)+ : (100+1+)패턴 또는 (01) 패턴이 최소 한번이상 나오는 패턴
			if (test.matches("^((10)(0)+(1)+|(01))+$")) {
				result[tried] = "YES";
			} else {
				result[tried] = "NO";
			}
		}

		for (String arg : result) {
			System.out.println(arg);
		}
	}
}
