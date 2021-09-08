package edu.qc.seclass.glm;

public class ListItem{
    private int id;
    private int groceryListID;
    private int itemID;
    private int itemTypeID;
    private boolean checkedOff;
    private String quantity;
    private String itemName;


    public ListItem(int id, int groceryListID, int itemID, boolean checkedOff, String quantity, String itemName, int itemTypeID) {
        this.id = id;
        this.groceryListID = groceryListID;
        this.itemID = itemID;
        this.checkedOff = checkedOff;
        this.quantity = quantity;
        this.itemName = itemName;
        this.itemTypeID = itemTypeID;
    }

    public int getItemTypeID() {
        return itemTypeID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrocerListID() {
        return groceryListID;
    }

    public void setGrocerListID(int grocerListID) {
        this.groceryListID = grocerListID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public boolean isCheckedOff() {
        return checkedOff;
    }

    public void setCheckedOff(boolean checkedOff) {
        this.checkedOff = checkedOff;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


}
