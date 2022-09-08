package implemention;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    17478 : 재귀함수가 뭔가요?
//    ref url : https://www.acmicpc.net/source/48922421

public class 재귀함수가뭔가요 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        runChatBot(sb, 0, N);
        System.out.println(sb);
    }
    static void runChatBot(StringBuilder sb, int depth, int N){
        underbar(sb,depth).append("\"재귀함수가 뭔가요?\"\n");
        if(depth == N){
            underbar(sb,depth).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
        }else{
            underbar(sb,depth).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
            underbar(sb,depth).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
            underbar(sb,depth).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
            runChatBot(sb, depth+1, N);
        }
        underbar(sb,depth).append("라고 답변하였지.\n");
    }

    static StringBuilder underbar(StringBuilder sb, int depth){
        for (int i = 0; i < depth; i++)
            sb.append("____");
        return sb;
    }
}
