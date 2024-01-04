package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//    9184 : 신나는 함수 실행
//    ref url : https://www.acmicpc.net/problem/9184
public class 신나는함수실행 {
    static final String EOF = "-1 -1 -1";
    static Map<String, Node> nodeMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = null;
        while (!(input = in.readLine()).equals(EOF)) {
            out.write(String.format("w(%s) = %s\n" ,input.replaceAll(" ", ", "), w(input)));
        }
        out.flush();
    }

    static int w(String input) {
        Node node = nodeMap.get(input);
        if (node != null) {
            return node.result;
        }
        int[] abc = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        int a = abc[0];
        int b = abc[1];
        int c = abc[2];
        if (a <= 0 || b <= 0 || c <= 0) {
            String hash = String.format("%s %s %s", a, b, c);
            if (nodeMap.get(hash) == null) {
                Node dpNode = new Node(hash);
                dpNode.result = 1;
                nodeMap.put(hash, dpNode);
            }
            return nodeMap.get(hash).result;
        } else if (a > 20 | b > 20 || c > 20) {
            String hash = String.format("%s %s %s", 20, 20, 20);
            if (nodeMap.get(hash) == null) {
                Node dpNode = new Node(hash);
                dpNode.result = w(hash);
                nodeMap.put(hash, dpNode);
            }
            return nodeMap.get(hash).result;
        } else if (a < b && b < c) {
            String hash1 = String.format("%s %s %s", a, b, c - 1);
            String hash2 = String.format("%s %s %s", a, b - 1, c - 1);
            String hash3 = String.format("%s %s %s", a, b - 1, c);
            String[] arr = {hash1, hash2, hash3};
            for (String hash : arr) {
                if (nodeMap.get(hash) == null) {
                    Node dpNode = new Node(hash);
                    dpNode.result = w(hash);
                    nodeMap.put(hash, dpNode);
                }
            }
            return nodeMap.get(hash1).result + nodeMap.get(hash2).result - nodeMap.get(hash3).result;
        } else {
            String hash1 = String.format("%s %s %s", a - 1, b, c);
            String hash2 = String.format("%s %s %s", a - 1, b - 1, c);
            String hash3 = String.format("%s %s %s", a - 1, b, c - 1);
            String hash4 = String.format("%s %s %s", a - 1, b - 1, c - 1);
            String[] arr = {hash1, hash2, hash3, hash4};
            for (String hash : arr) {
                if (nodeMap.get(hash) == null) {
                    Node dpNode = new Node(hash);
                    dpNode.result = w(hash);
                    nodeMap.put(hash, dpNode);
                }
            }
            return nodeMap.get(hash1).result + nodeMap.get(hash2).result + nodeMap.get(hash3).result - nodeMap.get(hash4).result;
        }
    }

    static class Node {
        int a;
        int b;
        int c;
        int result;

        public Node(String input) {
            int[] abc = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            this.a = abc[0];
            this.b = abc[1];
            this.c = abc[2];
        }
    }
}
