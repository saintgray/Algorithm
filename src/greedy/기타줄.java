package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//    1049 : 기타줄
//    ref url : https://www.acmicpc.net/problem/1049
public class 기타줄 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = params[0];
        int M = params[1];
        List<Maker> makers = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            makers.add(new Maker(in.readLine()));
        }

        Maker minPackagePriceMaker = makers.stream()
                .min(Comparator.comparing((Maker e) -> e.packagePrice))
                .get();
        Maker minSinglePriceMaker = makers.stream()
                .min(Comparator.comparing((Maker e) -> e.singlePrice))
                .get();

        int bundleCnt = N / 6;
        int remains = N % 6;
        int minPackagePrice = Math.min(minPackagePriceMaker.packagePrice * bundleCnt, minPackagePriceMaker.singlePrice * 6 * bundleCnt);
        minPackagePrice = Math.min(minPackagePrice, minSinglePriceMaker.singlePrice * 6 * bundleCnt);
        int minRemainPrice = Math.min(minPackagePriceMaker.packagePrice, minPackagePriceMaker.singlePrice * remains);
        minRemainPrice = Math.min(minRemainPrice, minSinglePriceMaker.singlePrice * remains);
        System.out.println(minPackagePrice + minRemainPrice);

    }


    static class Maker {
        int packagePrice;
        int singlePrice;

        public Maker(String priceInfo) {
            int[] info = Arrays.stream(priceInfo.split(" ")).mapToInt(Integer::parseInt).toArray();
            this.packagePrice = info[0];
            this.singlePrice = info[1];
        }
    }
}
