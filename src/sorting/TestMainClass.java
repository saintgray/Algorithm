package sorting;

import java.util.Optional;

/**
 * @author 오종현
 * @version 1.0.0
 * @since "${date}"
 */
public class TestMainClass {

    public static void main(String[] args) {
        TestInterface ti = new TestClassImpl();
        Optional<?> testData= Optional.of(1);
        ti.printNumber(testData);
        testData=null;
        ti.printNumber(testData);


    }
}
