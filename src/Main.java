// 간단한 문제에 대한 제출용 Main Class 입니다

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCases = Integer.parseInt(in.readLine());


        for(int i =0; i<testCases ; i++){
            int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(e-> Integer.parseInt(e)).toArray();
            int A = params[0];
            int B = params[1];


            int gcd = gcd(A,B);
            out.write(String.valueOf((A*B)/gcd).concat("\n"));
        }
        out.flush();

        in.close();
        out.close();
    }


    public static int gcd(int a, int b){
        return b!=0 ? gcd(b,a%b):a;
    }
}
