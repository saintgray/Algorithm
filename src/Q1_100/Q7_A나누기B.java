package Q1_100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q7_A나누기B {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(in.readLine(), " ");
		
	    System.out.println(((double)Integer.parseInt(tk.nextToken())/Integer.parseInt(tk.nextToken())));
	}

}