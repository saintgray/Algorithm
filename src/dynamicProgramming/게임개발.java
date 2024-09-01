package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//    1516 : 게임 개발
//    ref url : https://www.acmicpc.net/problem/1516
//    ref alg : topology sort
public class 게임개발 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static Struct[] structs;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        structs = new Struct[N];
        for (int s = 0; s < N; s++) {
            structs[s] = new Struct(s);
        }

        for (int i = 0; i < N; i++) {
            String[] param = in.readLine().split(" ");
            Struct s = structs[i];
            s.buildTime = Integer.parseInt(param[0]);
            for (int j = 1; j < param.length - 1; j++) {
                int no = Integer.parseInt(param[j]) - 1;
                structs[no].out_Degrees.add(s);
                s.in_degrees++;
            }
        }

        Queue<Struct> q = new LinkedList<>();
        for (Struct struct : structs) {
            if (struct.in_degrees == 0) {
                struct.fastest = struct.buildTime;
                q.add(struct);
            }
        }

        while (!q.isEmpty()) {
            Struct s = q.poll();
            for (int i = 0; i < s.out_Degrees.size(); i++) {
                Struct to = s.out_Degrees.get(i);
                if (to.fastest < s.fastest + to.buildTime) {
                    to.fastest = s.fastest + to.buildTime;
                    q.add(to);
                }
            }
        }

        for (Struct struct : structs) {
            out.write(String.valueOf(struct.fastest));
            out.newLine();
        }
        out.flush();
    }

    static class Struct {
        int no;
        int buildTime;
        int fastest;
        List<Struct> out_Degrees;
        int in_degrees;

        public Struct(int no) {
            this.no = no;
            this.out_Degrees = new ArrayList<>();
            this.fastest = Integer.MIN_VALUE;
        }
    }
}
