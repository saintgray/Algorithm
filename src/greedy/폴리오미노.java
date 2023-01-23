package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//    1343 : 폴리오미노
//    ref url : https://www.acmicpc.net/problem/1343
public class 폴리오미노 {
    static final Character A = 'A';
    static final Character B = 'B';

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] board = in.readLine().toCharArray();

        boolean cantFilled = false;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '.')
                continue;
            int firstIndex = i;
            int lastIndex = i;
            while (lastIndex + 1 < board.length && board[lastIndex + 1] != '.')
                lastIndex++;
            int size = lastIndex - firstIndex + 1;
            if (size % 2 != 0) {
                cantFilled = true;
                break;
            } else {
                for (int j = firstIndex; j <= firstIndex + ((size / 4) * 4) - 1; j++)
                    board[j] = A;
                for (int j = firstIndex + (size / 4) * 4; j <= lastIndex; j++)
                    board[j] = B;
            }
            i = lastIndex;
        }
        if (cantFilled) {
            System.out.println("-1");
        } else {
            StringBuilder builder = new StringBuilder();
            for (char c : board)
                builder.append(c);
            System.out.println(builder);
        }
    }
}
