package edu.qc.seclass.glm;

public class Item {
    private int id;
    private Integer item_type_id;
    private String item_name;

    public Item(int id, Integer item_type_id, String item_name) {
        this.id = id;
        this.item_type_id = item_type_id;
        this.item_name = item_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getItem_type_id() {
        return item_type_id;
    }

    public void setItem_type_id(Integer item_type_id) {
        this.item_type_id = item_type_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    @Override
    public String toString() {
        return item_name;
    }
}
