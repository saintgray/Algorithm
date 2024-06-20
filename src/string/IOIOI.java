package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 5525 : IOIOI
// ref url : https://www.acmicpc.net/problem/5525
public class IOIOI {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static final String FRAG = "IOI";
    public static void main(String[] args) throws IOException {
        long N = Integer.parseInt(in.readLine());
        long M = Integer.parseInt(in.readLine());
        String str = in.readLine();
        int i =0;
        int _p = 0;
        int count = 0;
        while (i < M - 2) {
            if (str.substring(i, i + 3).equals(FRAG)) {
                i += 2;
                _p++;
                if(_p == N) {
                    count++;
                    _p--; // 이전 FRAGMENT 를 제외해야 하므로..
                }
            } else {
                i++;
                _p = 0;
            }
        }
        System.out.println(count);
    }
}
