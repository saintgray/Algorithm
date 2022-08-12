package test.source;

// 간단한 문제에 대한 제출용 Main Class 입니다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        System.out.println(Math.pow(2,((double)1/2)) % 2);
        System.out.println(0 == 0.00000);
    }
}
