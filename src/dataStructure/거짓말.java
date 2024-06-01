package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    1043 : 거짓말
//    ref url : https://www.acmicpc.net/problem/1043
public class 거짓말 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] param = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = param[0];
        int M = param[1];
        int[] param2 = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Party[] parties = new Party[M+1];
        Person[] people = new Person[N+1];
        for (int i = 0; i < N; i++) {
            people[i + 1] = new Person();
        }
        for (int i = 1; i < param2.length; i++) {
            people[param2[i]].knowTruth = true;
        }
        for (int i = 0; i < M; i++) {
            int[] param3 = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(param3[0] == 0) continue;
            parties[i+1] = new Party();
            parties[i+1].people = new Person[param3[0]];
            for (int j = 1; j < param3.length; j++) {
                // i+1 : party no
                people[param3[j]].party.add(i+1);
                parties[i+1].people[j-1] = people[param3[j]];
            }
        }

        for (int i = 1; i < parties.length; i++) {
            for (int j = 0; j < parties[i].people.length; j++) {
                Person person = parties[i].people[j];
                if (person.knowTruth && !person.checked) {
                    for (int k = 0; k < person.party.size(); k++) {
                        grouping(parties, person.party.get(k));
                    }
                }
            }
        }
        // 진실을 알지 못하는 party 만 counting
        // grouping 하는 과정에서 진실을 아는 사람과 만났을 경우 모든 사람의 knowTruth 값이 true 가 되므로
        // 한명의 knowTruth 여부만 보면 된다.
        int talkAbleCnt = 0;
        for (int i = 1; i < parties.length; i++) {
            talkAbleCnt += (parties[i].people.length > 0 && parties[i].people[0].knowTruth) ? 0 : 1;
        }
        System.out.println(talkAbleCnt);
    }

    static void grouping(Party[] parties, int partyNo) {
        for (int i = 0; i < parties[partyNo].people.length; i++) {
            Person person = parties[partyNo].people[i];
            if(person.checked) continue;
            person.checked = true;
            person.knowTruth = true;
            for (int j = 0; j < person.party.size(); j++) {
                grouping(parties, person.party.get(j));
            }
        }
    }

    static class Person {
        boolean knowTruth;  // 진실을 아는지 여부
        boolean checked;    // 그룹핑 완료 여부
        List<Integer> party = new ArrayList<>();    // 참여한 파티 번호
    }

    static class Party {
        Person[] people;    // party 에 참여한 사람들
    }
}
