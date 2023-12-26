package bruteForceAlgorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//    7568 : 덩치
//    ref url : https://www.acmicpc.net/problem/7568
public class 덩치 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(in.readLine());
        Peep[] peeps = new Peep[N];
        for (int i = 0; i < N; i++) {
            peeps[i] = new Peep(in.readLine());
        }

        for (int i = 0; i < N; i++) {
            Peep peep = peeps[i];
            int count = (int) Arrays.stream(peeps)
                    .filter(p -> p != peep)
                    .filter(p -> p.h > peep.h && p.w > peep.w)
                    .count();
            out.write(String.format("%s ", count + 1));
        }
        out.flush();
    }

    static class Peep {
        int h;
        int w;

        public Peep(String param) {
            int[] params = Arrays.stream(param.split(" ")).mapToInt(Integer::parseInt).toArray();
            this.w = params[0];
            this.h = params[1];
        }
    }
}
