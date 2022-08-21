package string;

//    10820 : 문자열 분석
//
//    문제
//    문자열 N개가 주어진다.
//    이때, 문자열에 포함되어 있는
//    소문자, 대문자, 숫자, 공백의 개수
//    를 구하는 프로그램을 작성하시오.
//    각 문자열은 알파벳 소문자, 대문자, 숫자, 공백으로만 이루어져 있다.
//
//    입력
//    첫째 줄부터 N번째 줄까지 문자열이 주어진다.
//    (1 ≤ N ≤ 100) 문자열의 길이는 100을 넘지 않는다.
//
//    출력
//    첫째 줄부터 N번째 줄까지 각각의 문자열에 대해서
//    소문자, 대문자, 숫자, 공백의 개수를 공백으로 구분해 출력한다.

import java.io.*;

public class 문자열분석 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = null;
        while((input = in.readLine()) != null && !input.isEmpty()){
            char[] chars = input.toCharArray();
            int capital = 0;    // ASCII 65 ~ 90
            int nonCapital = 0; // ASCII 97  ~ 122
            int empty = 0; // ASCII 64
            int num = 0; // ASCII 48 ~ 57
            for(char c : chars){
                int ascCd = (int)c;
                if(ascCd ==32){
                    empty++;
                }else if(ascCd <= 57){
                    num++;
                }else if(ascCd <= 90){
                    capital++;
                }else{
                    nonCapital++;
                }
            }
            out.write(String.format("%d %d %d %d\n",nonCapital,capital,num,empty));
        }
        out.flush();
        in.close(); out.close();
    }
}
