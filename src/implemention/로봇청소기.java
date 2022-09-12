package implemention;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//    14053 : 로봇 청소기
//    ref url : https://www.acmicpc.net/problem/14503

public class 로봇청소기 {
    static int R;
    static int C;
    // 상하좌우
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // 0: 북 1: 동 2: 남 3: 서
        int[] param = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        R = param[0];
        C = param[1];
        int[][] room = new int[R][C];
        int[] robotInfo = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        for (int i = 0; i < room.length; i++)
            room[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        Robot robot = new Robot(robotInfo[0], robotInfo[1], robotInfo[2]);

        do {
            robot.clean(room);
            boolean moved = false;
            for (int i = 0; i < 4; i++) {
                robot.rotate();
                if (robot.moveToBeCleaned(room)) {
                    moved = true;
                    break;
                }
            }
            if (!moved && !robot.backward(room))
                break;
        } while (true);
        System.out.println(robot.cleaned);
        in.close();
    }

    static class Robot {
        int r;
        int c;
        int lookingDirection;
        int cleaned;

        public Robot(int r, int c, int lookingDirection) {
            this.r = r;
            this.c = c;
            this.lookingDirection = lookingDirection;
        }
        public void rotate() {
            this.lookingDirection = this.lookingDirection == 0 ? 3 :
                    this.lookingDirection == 3 ? 2 :
                            this.lookingDirection == 2 ? 1 : 0;
        }

        public void clean(int[][] room) {
            if (room[this.r][this.c] == 0) {
                room[this.r][this.c] = 3; // 청소완료
                this.cleaned++;
            }
        }

        public boolean moveToBeCleaned(int[][] room) {
            boolean moved = false;
            int r = this.r + dr[this.lookingDirection];
            int c = this.c + dc[this.lookingDirection];
            if (isBoundary(r, c) && room[r][c] == 0) {
                moved = true;
                this.r = r;
                this.c = c;
            }
            return moved;
        }

        public boolean backward(int[][] room) {
            boolean result = false;
            int r = this.r + (dr[(this.lookingDirection + 2) % 4]);
            int c = this.c + (dc[(this.lookingDirection + 2) % 4]);
            if (isBoundary(r, c) && room[r][c] != 1) {
                result = true;
                this.r = r;
                this.c = c;
            }
            return result;
        }
    }

    static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
