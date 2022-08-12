package test;

import java.math.BigDecimal;

/**
 * @author 오종현
 * @version 1.0.0
 * @since "${date}"
 */
public class Person {

    private String name;
    private String address;
    private BigDecimal age;

    public Person(String name, String address, BigDecimal age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name :".concat(this.name)
                .concat("\taddress :".concat(this.address))
                .concat("\tage :".concat(String.valueOf(this.age)));
    }
}
