package test;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 오종현
 * @version 1.0.0
 * @since "${date}"
 */
public class StreamTest {


    public static void main(String[] args) {
//      List<Person> persons = new ArrayList<>();
        List<BigDecimal> ages = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ages.add(new BigDecimal(i));
        }

        List<Person> persons = ages.stream()
                .map(e -> new Person("이름".concat(e.toString()),
                        "주소".concat(e.toString()),
                        new BigDecimal(e.toString())))
                .collect(Collectors.toList());

        for (Person person : persons) {
            System.out.println(person.toString());
        }


        ages = ages.stream()
                .map(e -> e.multiply(new BigDecimal(100)))
                .collect(Collectors.toList());
        for (BigDecimal decimal : ages) {
            System.out.println(decimal);
        }

        persons.forEach(e -> {
            e.setAge(e.getAge().multiply(new BigDecimal(10)));
        });
        for (Person person : persons) {
            System.out.println(person.toString());
        }
    }
}
