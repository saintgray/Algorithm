package test.source;

/**
 * @author 오종현
 * @version 1.0.0
 * @since "${date}"
 */
public class EnumClassTest {

    public static void main(String[] args) {

        // 이전 사용내역이 있으나 삭제하려고 하는 경우
        ItemCrudStatus itemCrudStatus =
                ItemCrudStatus.getItemCrudStatus("Y", true);
        System.out.println(itemCrudStatus);
        // 이전 사용내역이 있고, 수정하려고 하는 경우
        ItemCrudStatus itemCrudStatus1 =
                ItemCrudStatus.getItemCrudStatus(null, true);
        System.out.println(itemCrudStatus1);
        // 이전 사용내역이 없고, 삭제하려고 하는 경우
        ItemCrudStatus itemCrudStatus2 =
                ItemCrudStatus.getItemCrudStatus("Y", false);
        System.out.println(itemCrudStatus2);
        // 이전 사용 내역이 없고 등록하려고 하는 경우
        ItemCrudStatus itemCrudStatus3 =
                ItemCrudStatus.getItemCrudStatus(null, false);
        System.out.println(itemCrudStatus3);

        // Enum Class 의 field 는 Type 도 맞는지 검사한다
        System.out.println("CREATE".equals(ItemCrudStatus.CREATE)); // false
        // 타입의 name 과 맞는지 검사는 가능
        // 다만 이렇게 하지 말라고 Enum 클래스가 존재하는 이유이기도 하므로
        // 코드에서 name 으로 비교하는 Logic 은 적합하지 못하다.
        System.out.println("CREATE".equals(ItemCrudStatus.CREATE.name()));

        // Best
        System.out.println(itemCrudStatus3.equals(ItemCrudStatus.CREATE));

        // dev commmit 1
        // dev commit 2

        // dev commmit 1
        // dev commit 2

    }
}
