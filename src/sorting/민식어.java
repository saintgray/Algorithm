package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//    1599 : 민식어
//    ref url : https://www.acmicpc.net/problem/1599
public class 민식어 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static Map<String, Integer> dict = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String[] str = "a b k d e g h i l m n X o p r s t u w y".split(" ");
        for (int i = 0; i < str.length; i++) {
            dict.put(str[i], i);
        }

        List<MinsikLang> words = new ArrayList<>();
        int N = Integer.parseInt(in.readLine());
        while(N-- > 0) {
            words.add(new MinsikLang(in.readLine().replaceAll("ng", "X")));
        }

        LangComparator comparator = new LangComparator();
        words.stream().sorted(comparator::compare)
                .peek(minsiklang -> minsiklang.word = minsiklang.word.replaceAll("X", "ng"))
                .forEach(minsikLang -> {
                    try {
                        out.write(minsikLang.word);
                        out.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        out.flush();
    }

    static class LangComparator implements Comparator<MinsikLang> {
        @Override
        public int compare(MinsikLang o1, MinsikLang o2) {
            String word = o1.word;
            String _word = o2.word;
            int min = Math.min(word.length(), _word.length());
            for (int i = 0; i < min; i++) {
                int order1 = dict.get(String.valueOf(word.charAt(i)));
                int order2 = dict.get(String.valueOf(_word.charAt(i)));
                if(order1 == order2) continue;
                if(order1 < order2) return -1;
                else return 1;
            }
            return Integer.compare(word.length(), _word.length());
        }
    }

    static class MinsikLang {
        String word;

        public MinsikLang(String word) {
            this.word = word;
        }

    }
}