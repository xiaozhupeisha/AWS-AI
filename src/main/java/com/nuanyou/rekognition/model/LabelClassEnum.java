package com.nuanyou.rekognition.model;

/**
 * Created by mylon.sun on 2017/12/10.
 */
public enum  LabelClassEnum {
    level1(1, "label label-danger"),
    level2(2, "label label-warning"),
    level3(3, "label label-info"),
    level4(4, "label label-success"),
    level6(5, "label label-primary"),
    level5(6, "label label-default");

    private Integer key;

    private String value;

    LabelClassEnum() {
    }

    LabelClassEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static LabelClassEnum toEnum(int key) {
        LabelClassEnum[] values = LabelClassEnum.values();
        for (LabelClassEnum status : values)
            if (key == status.getKey()) {
                return status;
            }
        throw new IllegalArgumentException("Cannot create evalue from value: " + key + "!");
    }

    public static LabelClassEnum toEnums(String value) {
        for (LabelClassEnum type : LabelClassEnum.values()) {
            if (value.equals(type.getValue())) {
                return type;
            }
        }
        throw new IllegalArgumentException("Cannot create evalue from value: " + value + "!");
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
