package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//    4195 : 친구 네트워크
//    ref url : https://www.acmicpc.net/problem/4195
//    tag : union find, hash table
public class 친구네트워크 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static Map<String, Integer> group_size = new HashMap<>();
    static Map<String, String> parent = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(in.readLine());
        while (T-- > 0) {
            group_size.clear();
            parent.clear();
            int F = Integer.parseInt(in.readLine());
            while (F-- > 0) {
                String[] friends = in.readLine().split(" ");
                if (!find(friends[0]).equals(find(friends[1]))) {
                    union(parent.get(friends[0]), parent.get(friends[1]));
                }
                out.write(String.valueOf(group_size.get(parent.get(friends[0]))));
                out.newLine();
            }
        }
        out.flush();
    }

    static String find(String name) {
        if (parent.get(name) == null) {
            parent.put(name, name);
            return name;
        }
        if (name.equals(parent.get(name))) return name;
        parent.put(name, find(parent.get(name)));
        return parent.get(name);
    }

    static void union(String p1, String p2) {
        int p1GroupSize = Optional.ofNullable(group_size.get(p1)).orElse(1);
        int p2GroupSize = Optional.ofNullable(group_size.get(p2)).orElse(1);
        if (p1.compareTo(p2) < 0) parent.put(p2, p1);
        else parent.put(p1, p2);
        group_size.put(p1, p1GroupSize + p2GroupSize);
        group_size.put(p2, p1GroupSize + p2GroupSize);
    }
}

