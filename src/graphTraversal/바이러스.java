package graphTraversal;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    2606 : 바이러스
//    ref url : https://www.acmicpc.net/problem/2606

public class 바이러스 {

    public static int infested = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Computer[] computers = new Computer[Integer.parseInt(in.readLine()) + 1];
        int connections = Integer.parseInt(in.readLine());
        for (int i = 0; i < connections; i++) {
            int[] connection = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
            int no1 = connection[0];
            int no2 = connection[1];

            if(computers[no1] == null)
                computers[no1] = new Computer();
            if(computers[no2] == null)
                computers[no2] = new Computer();
            computers[no1].connection.add(no2);
            computers[no2].connection.add(no1);
        }
        if(computers[1] != null){
            run(1, computers);
            System.out.println(infested -1);
        }else{
            System.out.println(0);
        }

        in.close();
    }

    static void run(int N, Computer[] computers){
        Computer target = computers[N];
        infested++;
        target.infested = true;
        List<Integer> connection = target.connection;
        if(!connection.isEmpty()){
            connection.stream().forEach(n -> {
                if(!computers[n].infested)
                    run(n, computers);
            });
        }
    }

    static class Computer {
        boolean infested;
        List<Integer> connection;

        public Computer() {
            connection = new ArrayList<>();
        }
    }
}
