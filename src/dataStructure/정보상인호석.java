package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//    22252 : 정보 상인 호석
//    ref url : https://www.acmicpc.net/problem/22252
public class 정보상인호석 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int GET = 1;
    static final int BUY = 2;
    static Map<String, Query> info = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(in.readLine());
        long result = 0;
        while(N-- > 0) {
            String[] input = in.readLine().split(" ");
            int type = Integer.parseInt(input[0]);
            Query query = info.get(input[1]);
            if (query == null) {
                query = new Query(input);
                info.put(query.name, query);
            }

            if(type == GET) {
                int infos = Integer.parseInt(input[2]);
                if(infos == 0) continue;
                for (int i = 3; i < input.length; i++) {
                    query.jewels.add(Integer.parseInt(input[i]));
                }
            }
            if(type == BUY) {
                int want = Integer.parseInt(input[2]);
                while(!query.jewels.isEmpty() && want-- > 0) {
                    result += query.jewels.poll();
                }
            }
        }
        System.out.println(result);
    }

    static class Query {
        String name;
        PriorityQueue<Integer> jewels;

        public Query(String[] input) {
            this.name = input[1];
            this.jewels = new PriorityQueue<>(Comparator.reverseOrder());
        }
    }
}
