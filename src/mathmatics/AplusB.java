package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AplusB {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int testCases=Integer.parseInt(in.readLine());
		for(int i =0; i<testCases; i++) {
			String[] params= in.readLine().split(" ");
			System.out.println(Integer.parseInt(params[0])+Integer.parseInt(params[1]));
		}
		in.close();
	}
}
