package test.source;

/**
 * @author 오종현
 * @version 1.0.0
 * @since "${date}"
 */
public enum ItemCrudStatus {

    CREATE("생성", "C"),
    SKIP("대상아님", "K"),
    DELETE("삭제", "D"),
    UPDATE("수정" ,"U");

    private String description;
    private String code;

    ItemCrudStatus(String description, String code){
        this.description = description;
        this.code = code;
    }

    public static ItemCrudStatus getItemCrudStatus(String deleteFlag, boolean hasUsedHistory){
        ItemCrudStatus itemCrudStatus = null;
        boolean isdeletedTarget = "Y".equals(deleteFlag) || false;
        if(isdeletedTarget && hasUsedHistory){
            itemCrudStatus = DELETE;
        }else if(isdeletedTarget && !hasUsedHistory){
            itemCrudStatus = SKIP;
        }else if(!isdeletedTarget && !hasUsedHistory){
            itemCrudStatus = CREATE;
        }else if(!isdeletedTarget && hasUsedHistory){
            itemCrudStatus = UPDATE;
        }
        return itemCrudStatus;
    }

}
