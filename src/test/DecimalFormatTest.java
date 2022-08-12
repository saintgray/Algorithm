package test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 오종현
 * @version 1.0.0
 * @since "${date}"
 */
public class DecimalFormatTest {
    public static void main(String[] args) {
        DecimalFormat numFormatter = new DecimalFormat("#,###.00");
        DecimalFormat numFormatterExcludeDecimalPoint = new DecimalFormat("#,###");

        List<BigDecimal> numList = new ArrayList<>();
        Random rand = new Random();
        for(int i=0; i<100; i++){
            int randomNumber = Math.abs(rand.nextInt());
            System.out.printf("%s | %s \n",
                    numFormatter.format(randomNumber),
                    numFormatterExcludeDecimalPoint.format(randomNumber));
        }

    }
}
