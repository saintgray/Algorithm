package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

//	26069 : 붙임성 좋은 총총이
//	ref url : https://www.acmicpc.net/problem/26069
public class 붙임성좋은총총이 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    // static Node[] nodes;
    static final String CHONG_CHONG = "ChongChong";

    public static void main(String[] args) throws IOException {
        Map<String, Boolean> infestedMap = new HashMap<>();
        infestedMap.put(CHONG_CHONG, true);
        N = Integer.parseInt(in.readLine());
        while (N-- > 0) {
            String[] input = in.readLine().split(" ");
            String n1 = input[0];
            String n2 = input[1];
            Boolean infested1 = infestedMap.get(n1);
            Boolean infested2 = infestedMap.get(n2);
            infested1 = infested1 == null ? false : true;
            infested2 = infested2 == null ? false : true;
            if(infested1 || infested2) {
                infestedMap.put((infested1 ? n2 : n1), true);
            }
        }
        System.out.println(infestedMap.keySet().stream().filter(infestedMap::get).count());
    }
}