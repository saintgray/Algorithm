package sorting;

import java.util.Optional;

/**
 * @author 오종현
 * @version 1.0.0
 * @since "${date}"
 */
public class TestClassImpl implements TestInterface {
    @Override
    public void printNumber(Optional<?> num) {
        System.out.println(num.isPresent());
        System.out.println("num = " + num.get());
    }
}
