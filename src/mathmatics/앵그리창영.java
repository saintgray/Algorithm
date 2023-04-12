package mathmatics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


//    3034 : 앵그리 창영
//    ref url : https://www.acmicpc.net/problem/
public class 앵그리창영 {
    public static final String PUT_ABLE = "DA\n";
    public static final String PUT_DISABLE = "NE\n";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] NWH = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = NWH[0];
        int W = NWH[1];
        int H = NWH[2];
        int diagonal = (int) (Math.pow(W, 2) + Math.pow(H, 2));
        while (N-- > 0) {
            int len = Integer.parseInt(in.readLine());
            out.write(len <= W || len <= H || (int) Math.pow(len,2) <= diagonal ? PUT_ABLE : PUT_DISABLE);
        }
        out.flush();
    }
}
