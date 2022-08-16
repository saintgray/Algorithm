package numberTheory;


import java.io.*;
import java.util.Arrays;

//    문제
//    두 개의 자연수를 입력받아
//    최대 공약수와 최소 공배수를 출력하는 프로그램을 작성하시오.
//
//    입력
//    첫째 줄에는 두 개의 자연수가 주어진다.
//    이 둘은 10,000이하의 자연수이며 사이에 한 칸의 공백이 주어진다.
//
//    출력
//    첫째 줄에는 입력으로 주어진 두 수의 최대공약수를,
//    둘째 줄에는 입력으로 주어진 두 수의 최소 공배수를 출력한다.

public class 최대공약수최소공배수 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(e-> Integer.parseInt(e)).toArray();
        int A = params[0];
        int B = params[1];

        int gcd = gcd(A,B);
        out.write(String.valueOf(gcd).concat("\n"));
        out.write(String.valueOf((A*B)/gcd));

        out.flush();

        in.close();
        out.close();
    }


    public static int gcd(int a, int b){
        return b!=0 ? gcd(b,a%b):a;
    }
}
