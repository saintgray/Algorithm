package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

//    1202 : 보석 도둑
//    ref url : https://www.acmicpc.net/problem/1202
//    * 우선순위 큐 (Priority Queue)
//    * Greedy, Sort
public class 보석도둑 {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] p = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = p[0];
        int K = p[1];
        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            jewels[i] = new Jewel(in.readLine().split(" "));
        }
        int[] capacities = new int[K];
        for (int i = 0; i < K; i++) {
            capacities[i] = Integer.parseInt(in.readLine());
        }
        Arrays.sort(capacities);
        Arrays.sort(jewels, Comparator.comparing(jewel -> jewel.weight));
        // 작은 가방부터 가방이 수용할 수 있는 한계 내의 보석 중에 가장 가치가 큰 보석을 훔친다
        // 즉 우선순위는
        // 1. 가방의 수용량보다 같거나 작은 보석일 것
        // 2. 가치가 제일 높을 것
        Jewel[] heap = new Jewel[N + 1];
        int idx = 0;
        int last = 1;
        long result = 0;
        for (int i = 0; i < K; i++) {
            // 가방 수보다 보석수가 더 적은 경우를 고려
            int capacity = capacities[i];
            // 훔치지 않는 보석들 중 보석이 수용할 수 있는 무게 한도 내에서 우선순위 큐에 삽입
            while (idx < N && jewels[idx].weight <= capacity) {
                push(heap, jewels[idx], last);
                last++;
                idx++;
            }
            if(heap[1] == null) continue;
            // 최우선 순위 보석을 훔친다
            result += heap[1].price;
            // 최후순위 노드를 최우선 순위로 가져와 재정렬한다.
            heap[1] = heap[last - 1];
            heap[last - 1] = null;
            sort(heap, 1, --last);
        }
        System.out.println(result);
    }

    static void push(Jewel[] heap, Jewel jewel, int last) {
        heap[last] = jewel;
        if (last == 1) return;
        Jewel add = heap[last];
        Jewel parent = heap[last / 2];
        if (add.price > parent.price) {
            heap[last] = parent;
            push(heap, add, last / 2);
        }
    }

    static void sort(Jewel[] heap, int index, int len) {
        Jewel jewel = heap[index];
        int left = 2 * index;
        int right = 2 * index + 1;
        Jewel leftJewel = left > len ? null : heap[left];
        Jewel rightJewel = right > len ? null : heap[right];
        if (leftJewel == null) {
            return;
        }
        // 자식 노드가 2개 있는 경우
        if (rightJewel != null) {
            Jewel priority = jewel.comparePriority(rightJewel.comparePriority(leftJewel));
            heap[index] = priority;
            if (priority == leftJewel) {
                heap[left] = jewel;
                sort(heap, left, len);
            }
            if (priority == rightJewel) {
                heap[right] = jewel;
                sort(heap, right, len);
            }
        } else {
            // 자식 노드가 왼쪽 하나밖에 없는 경우
            Jewel priority = jewel.comparePriority(leftJewel);
            heap[index] = priority;
            if (priority != jewel) {
                heap[left] = jewel;
                sort(heap, left, len);
            }
        }
    }

    public static class Jewel {
        int weight;
        int price;
        boolean stolen;

        public Jewel(String[] args) {
            this.weight = Integer.parseInt(args[0]);
            this.price = Integer.parseInt(args[1]);
        }

        Jewel comparePriority(Jewel jewel) {
            return this.price > jewel.price ? this : jewel;
        }
    }

}
