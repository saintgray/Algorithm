package bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 2098 : 외판원 순회
// ref url : https://www.acmicpc.net/problem/2098
public class 외판원순회 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int N;   // 정점 개수
    static int[][] cost;    // i+1 정점 ~ j+1 정점 까지의 비용
    // 정점, 방문한 Bit 값 별 남은 정점을 방문하기 위한 최소 비용
    // 1. 정점 별로 방문한 Bit 값의 최대 조합은 2^16 -1개
    // 2. 최악의 경우 (N=16) 16 * 65,535 = 1,048,560 메모리 필요
    // Bit 값에 대한 index : Bit 값 - 1
    static int[][] memory;
    static int INFINITE = Integer.MAX_VALUE;
    static int bitValueOfAllVisit;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        memory = new int[N][(int)Math.pow(2, N)];
        cost = new int[N][N];
        for (int r = 0; r < N; r++) {
            int[] _cost = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int c = 0; c < N; c++) {
                cost[r][c] = _cost[c] == 0 ? INFINITE : _cost[c];
            }
        }
        // 항상 순회할 수 있다는 말은 어느 정점을 시작점으로 잡아도
        // 최소 비용은 동일한 값을 가질 것이므로 1번 정점을 시작점으로 정한다. (2 ≤ N ≤ 16)

        // 1. 모든 정점을 방문한 Bit 수 초기화
        // 2. 1번 정점을 방문하고 탐색을 시작하므로 Bit 값 = 1
        bitValueOfAllVisit = (1 << N) - 1; // expected 65,535 when N is 16
        findOptimizedCost(1, 1);
        System.out.println(memory[0][1]);
    }

    /**
     * findOptimizedCost
     * 정점 N 에서 출발하여 방문하지 않은 모든 정점을 방문하기까지의
     * 최소 비용을 반환한다
     * @param n               정점 번호
     * @param bitValueOfVisit 방문한 정점의 Bit 값
     * @return 최소비용
     */
    static int findOptimizedCost(int n, int bitValueOfVisit) {
        int v = n - 1; // 정점 번호에 대한 memory, cost index
        // 모든 정점을 방문시 마지막 정점에서 최초 시작 정점 (=1) 까지 비용 반환
        if (bitValueOfVisit == bitValueOfAllVisit) {
            return (memory[v][bitValueOfVisit] = cost[v][0]);
        }
        // 최적 비용이 memory 에 있으면 반환
        if (memory[v][bitValueOfVisit] != 0) {
            return memory[v][bitValueOfVisit];
        }
        // 없으면 정점별 최소비용을 memory 에 저장 하는데
        // 이미 방문한 정점은 제외한다.
        for (int _n = 2; _n <= N; _n++) {
            // 다음 정점 번호에 대한 memory, cost index
            int _v = _n - 1;
            if ((bitValueOfVisit & (1 << _v)) == (1 << _v)) continue;
            // 다음 최적화 비용
            int nextOptimizedCost = findOptimizedCost(_n, bitValueOfVisit | (1 << _v));
            // 현재 정점의 최적화 비용 = 다음 최적화 비용 + 현재 정점에서 다음 정점까지의 비용
            int optimizedCost = (nextOptimizedCost == INFINITE || cost[v][_v] == INFINITE) ? INFINITE : nextOptimizedCost + cost[v][_v];
            // 1. 현재 정점 ~ 다음 정점까지 가는 데 드는 비용 + 다음 정점에서 출발하여 방문하지 않은 모든 정점을 방문하는 데 드는 최소 비용
            // 2. memory 에 저장된 최소비용
            // 초기값이 없으면 최적화 비용을 그냥 저장하고 있으면 둘 중 최소값으로 갱신한다.
            // 1, 2 중 최소값을 memory 에 저장
            if (memory[v][bitValueOfVisit] == 0) {
                memory[v][bitValueOfVisit] = optimizedCost;
            } else {
                memory[v][bitValueOfVisit] = Math.min(memory[v][bitValueOfVisit], optimizedCost);
            }
        }
        return memory[v][bitValueOfVisit];
    }
}

