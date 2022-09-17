package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

//    1946 : 신입사원
//    ref url : https://www.acmicpc.net/problem/1946

public class 신입사원 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());
        for (int i = 0; i < cases; i++) {
            int applicantsCnt = Integer.parseInt(in.readLine());
            Applicant[] applicants = new Applicant[applicantsCnt];

            for (int j = 0; j < applicantsCnt; j++)
                applicants[j] = new Applicant(j, in.readLine().split(" "));
            applicants = Arrays.stream(applicants)
                    .sorted(Comparator.comparing(Applicant::getS1, Comparator.naturalOrder()))
                    .toArray(Applicant[]::new);

            Applicant s1Top = applicants[0];
            int s2Compare = s1Top.s2;
            int cnt = 0;
            for (int j = 0; j < applicantsCnt; j++) {
                if(applicants[j].s2 <= s2Compare){
                    s2Compare = applicants[j].s2;
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
        in.close();
    }

    static class Applicant {
        int seq;
        int s1;
        int s2;

        public Applicant(int seq, String[] params) {
            this.seq = seq;
            this.s1 = Integer.parseInt(params[0]);
            this.s2 = Integer.parseInt(params[1]);
        }

        public int getSeq() {
            return seq;
        }

        public int getS1() {
            return s1;
        }

        public int getS2() {
            return s2;
        }
    }
}
