package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//    17413 : 단어뒤집기2
//    ref url : https://www.acmicpc.net/problem/17413

public class 단어뒤집기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = in.readLine().toCharArray();
        StringBuilder result = new StringBuilder();
        for(int i =0; i< chars.length; i++){
            char c = chars[i];
            if(c == '<'){
                while(i < chars.length){
                    result.append(chars[i]);
                    if(chars[i] =='>')
                        break;
                    i++;
                }
            }else{
                Stack<Character> stack = new Stack<>();
                while(i< chars.length){
                    if(chars[i] == ' ' || chars[i] == '<'){
                        if(chars[i] == '<')
                            i--;
                        while(!stack.isEmpty())
                            result.append(stack.pop());
                        if(chars[i] == ' ')
                            result.append(' ');
                        break;
                    }else {
                        stack.push(chars[i]);
                        i++;
                    }
                }
                while(!stack.isEmpty())
                    result.append(stack.pop());
            }
        }
        System.out.println(result);
        in.close();
    }
}
