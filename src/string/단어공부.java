package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

//    1157 : 단어공부
//
//    문제
//    알파벳 대소문자로 된 단어가 주어지면,
//    이 단어에서 가장 많이 사용된 알파벳이 무엇인지 알아내는 프로그램을 작성하시오.
//    단, 대문자와 소문자를 구분하지 않는다.
//
//    입력
//    첫째 줄에 알파벳 대소문자로 이루어진 단어가 주어진다. 주어지는 단어의 길이는 1,000,000을 넘지 않는다.
//
//    출력
//    첫째 줄에 이 단어에서 가장 많이 사용된 알파벳을 대문자로 출력한다.
//    단, 가장 많이 사용된 알파벳이 여러 개 존재하는 경우에는 ?를 출력한다.
public class 단어공부 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();

        char[] wordsArr = input.toCharArray();

        Map<Character, Integer> countMap = new HashMap<>();

        for (int i = 0; i < wordsArr.length; i++) {
            char c = Character.toLowerCase(wordsArr[i]);
            if (countMap.get(c) == null) {
                countMap.put(c, 1);
            } else {
                countMap.put(c, countMap.get(c)+ 1);
            }
        }
        int mostUsedCount = 0;
        StringBuilder result = new StringBuilder();
        for(char c : countMap.keySet()){
            int count = countMap.get(c);
            if(mostUsedCount == 0){
                result.append(Character.toUpperCase(c));
                mostUsedCount =count;
            }else{
                if(mostUsedCount < count){
                    mostUsedCount = count;
                    result.delete(0,1);
                    result.append(Character.toUpperCase(c));
                }else if(mostUsedCount == count){
                    result.delete(0,1);
                    result.append("?");
                }
            }
        }

        System.out.println(result);
        in.close();
    }
}
