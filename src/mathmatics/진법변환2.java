package mathmatics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

//    11005 : 진법변환2
//    ref url : https://www.acmicpc.net/problem/11005
public class 진법변환2 {
    static Map<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            map.put(i, String.valueOf(i));
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            map.put(((int) i) - 55, String.valueOf(i));
        }
        String[] params = in.readLine().split(" ");
        int num = Integer.parseInt(params[0]);
        int format = Integer.parseInt(params[1]);
        StringBuffer result = new StringBuffer();
        while (num != 0) {
            int quotient = num / format;
            int rest = num % format;
            result.append(map.get(rest));
            num = quotient;
        }
        System.out.println(result.reverse());
    }
}
