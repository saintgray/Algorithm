package string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//    11098 : 첼시를 도와줘!
//    ref url : https://www.acmicpc.net/problem/11098
public class 첼시를도와줘 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(in.readLine());

        List<String> list = new ArrayList<>();
        while(tc-- >0) {
            int N = Integer.parseInt(in.readLine());
            while (N-- > 0)
                list.add(in.readLine());
            list.stream()
                    .max(Comparator.comparing(info -> Integer.parseInt(info.split(" ")[0])))
                    .ifPresent(result -> {
                        try {
                            out.write(result.split(" ")[1]);
                            out.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            list.clear();
        }
        out.flush();
    }
}
