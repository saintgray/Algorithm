package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
public class MainHold {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine().toLowerCase();

        Character[] wordsArr =
                input.chars()
                        .mapToObj(c -> (char) c)
                        .toArray(Character[]::new);
        List<Character> list = new ArrayList<>(Arrays.asList(wordsArr));

        Map<Character, Integer> countMap = new HashMap<>();
        for(int i =0; i< list.size(); i++){
            Character alphabet = list.get(i);
            Integer alphabetUsed = countMap.get(alphabet);
            if(alphabetUsed == null){
                countMap.put(alphabet, 1);
            }else{
                countMap.put(alphabet, alphabetUsed + 1);
            }
        }

        Character result = '?';
        Integer mostUsedCount = 0;

        Iterator<Character> itr = countMap.keySet().iterator();
        while(itr.hasNext()){
            Character alphabet = itr.next();
            Integer alphabetCount = countMap.get(alphabet);
            if(mostUsedCount == 0){
                mostUsedCount = alphabetCount;
                result = alphabet;
            }else{
                result = mostUsedCount == alphabetCount
                        ? '?'
                        : mostUsedCount < alphabetCount
                        ? alphabet
                        : result;
                if(mostUsedCount < alphabetCount)
                    mostUsedCount = alphabetCount;
            }
        }
        System.out.println(String.valueOf(result).toUpperCase());
        in.close();
    }
}
