package test.source;

import com.sun.corba.se.spi.orb.Operation;
import test.OperationResult;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @author 오종현
 * @version 1.0.0
 * @since "${date}"
 */
public class ReflectionTest {

    public static void main(String[] args) {

        OperationResult result = new OperationResult();
        for(int i=0; i<12; i++){
//            System.out.println(result.getClass().getFields());    // only public scoped field
            System.out.println(result.getClass().getDeclaredFields().length);
            for(Field field : result.getClass().getDeclaredFields()){
                System.out.println(field.getName());
                if(field.getName().equals("measure".concat(String.valueOf(i+1)))){
                    field.setAccessible(true);
                    try{
                        field.set(result, new BigDecimal(i*123));
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        System.out.println(result);


    }
}
