package geometry;

//    문제
//    세 점이 주어졌을 때,
//    축에 평행한 직사각형을 만들기 위해서
//    필요한 네 번째 점을 찾는 프로그램을 작성하시오.
//
//    입력
//    세 점의 좌표가 한 줄에 하나씩 주어진다.
//    좌표는 1보다 크거나 같고, 1000보다 작거나 같은 정수이다.
//
//    출력
//    직사각형의 네 번째 점의 좌표를 출력한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 네번째점 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> xSpotMap = new HashMap<>();
        Map<Integer, Integer> ySpotMap = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            String[] input = in.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            if(xSpotMap.isEmpty()){
                xSpotMap.put(x,x);
            }else{
                if(xSpotMap.get(x)!=null){
                    xSpotMap.remove(x);
                }else{
                    xSpotMap.put(x,x);
                }
            }
            if(ySpotMap.isEmpty()){
                ySpotMap.put(y,y);
            }else{
                if(ySpotMap.get(y)!=null){
                    ySpotMap.remove(y);
                }else{
                    ySpotMap.put(y,y);
                }
            }
        }

        System.out.printf("%d %d",xSpotMap.entrySet().stream().findFirst().get().getKey(),
                ySpotMap.entrySet().stream().findFirst().get().getKey());

    }
}
