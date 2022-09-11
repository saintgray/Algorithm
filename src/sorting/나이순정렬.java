package sorting;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

//    10814 : 나이순 정렬
//    ref url : https://www.acmicpc.net/problem/10814

public class 나이순정렬 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int userCnt = Integer.parseInt(in.readLine());
        User[] users = new User[userCnt];
        for (int i = 0; i < userCnt; i++)
            users[i] = new User(in.readLine(), i);
        users = Arrays.stream(users)
                .sorted(Comparator.comparing(User::getAge)
                        .thenComparing(User::getRegister))
                .toArray(User[]::new);
        for(User user : users)
            out.write(user.toString());
        out.flush();
        in.close();
        out.close();
    }

    static class User {
        int register;
        int age;
        String name;


        public User(String info, int register) {
            String[] infos = info.split(" ");
            this.register = register;
            this.age = Integer.parseInt(infos[0]);
            this.name = infos[1];
        }

        public int getRegister() {
            return register;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return String.format("%d %s\n", this.age, this.name);
        }
    }
}
