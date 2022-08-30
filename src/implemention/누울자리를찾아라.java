package implemention;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1652 : 누울 자리를 찾아라
// ref url : https://www.acmicpc.net/problem/1652

public class 누울자리를찾아라 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int roomSize = Integer.parseInt(in.readLine());
        char[][] room = new char[roomSize][];
        for (int i = 0; i < roomSize; i++)
            room[i] = in.readLine().toCharArray();

        int widthVacancy = 0;
        for (int i = 0; i < roomSize; i++) {
            for (int j = 0; j < room[i].length; j++) {
                if (room[i][j] == '.') {
                    int firstIndex = j;
                    while (j < room[i].length && room[i][j] == '.')
                        j++;
                    if (j - firstIndex >= 2)
                        widthVacancy++;
                }
            }
        }
        int heightVacancy = 0;
        for (int i = 0; i < roomSize; i++) {
            for (int j = 0; j < roomSize; j++) {
                if (room[j][i] == '.') {
                    int firstIndex = j;
                    while (j < roomSize && room[j][i] == '.')
                        j++;
                    if (j - firstIndex >= 2)
                        heightVacancy++;
                }
            }
        }
        System.out.println(String.format("%d %d", widthVacancy, heightVacancy));
        in.close();
    }
}
