package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String CHECKED_OFF = "CHECKED_OFF";
    public static final String QUANTITY = "QUANTITY";
    public static final String GL_ITEM_ID = "GL_ITEM_ID";
    private static Context context;


    private static final String ITEM = "ITEM";
    private static final String ITEM_TYPE_NAME = "ITEM_TYPE_NAME";
    private static final String ITEM_TYPE = "ITEM_TYPE";
    private static final String ITEM_ID = "ITEM_ID";
    private static final String ITEM_TYPE_ID = "ITEM_TYPE_ID";
    private static final String ITEM_NAME = "ITEM_NAME";
    private static final String GROCERY_LIST = "GROCERY_LIST";
    private static final String GL_ITEM = "GL_ITEM";
    private static final String GL_ID = "GL_ID";
    private static final String GL_NAME = "GL_NAME";

    private static final String [] itemTypes = {"Beer, Wine & Spirits", "Dairy, Eggs, & Cheese", "Frozen Foods",
            "Fruits", "Vegetables", "Meat",
            "Seafood", "Miscellaneous", "Paper Products",
            "Cleaning Supplies", "Health & Beauty, Personal Care",
            "Pharmacy", "Breakfast Cereals", "Spices",
            "Condiments", "Baking", "Grains & Pasta"};
    private static final Item [] items = {new Item(1, 1, "Budweiser Pale Lager"),
                                            new Item(1,1, "Anheuser-Busch Pale Ale"),
                                            new Item(1,1, "Glenlivet 18"),
                                            new Item(1,2, "Yoplait Yogurt"),
                                            new Item(1,2,"Skim Milk"),
                                            new Item(1,2,"Whole Milk"),
                                            new Item(1,2,"Cheddar Cheese"),
                                            new Item(1,3,"Frozen Pizza"),
                                            new Item(1,3,"Hot Pockets"),
                                            new Item(1,4,"Apple"),
                                            new Item(1,4,"Orange"),
                                            new Item(1,5,"Potato"),
                                            new Item(1,5,"Onion"),
                                            new Item(1,6,"Chicken"),
                                            new Item(1,6,"London Broil"),
                                            new Item(1,7,"Tuna"),
                                            new Item(1,7,"Crab"),
                                            new Item(1,8,"Flashlight"),
                                            new Item(1,8,"AA Batteries"),
                                            new Item(1,9,"Paper Plates"),
                                            new Item(1,9,"Napkins"),
                                            new Item(1,10,"Windex"),
                                            new Item(1,10,"Bleach"),
                                            new Item(1,11,"Deodorant"),
                                            new Item(1,11,"Toothpaste"),
                                            new Item(1,12,"Tylenol"),
                                            new Item(1,12,"Advil"),
                                            new Item(1,13,"Cinnamon Toast Crunch"),
                                            new Item(1,13,"Cheerios"),
                                            new Item(1,14,"Salt"),
                                            new Item(1,14,"Garlic"),
                                            new Item(1,15,"Ketchup"),
                                            new Item(1,15,"Sriracha"),
                                            new Item(1,16,"All Purpose Flour"),
                                            new Item(1,16,"Baking Soda"),
                                            new Item(1,17,"Spaghetti"),
                                            new Item(1,17,"Rice")};

    public DataBaseHelper(@Nullable Context context) {
        super(context, "GLM.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String firstSQLStatement = "CREATE TABLE " + ITEM_TYPE + " (" + ITEM_TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ITEM_TYPE_NAME + " TEXT) ";
        db.execSQL(firstSQLStatement);
        String secondSQLStatement = "CREATE TABLE " + ITEM + " (" + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ITEM_TYPE_ID + " INTEGER, " + ITEM_NAME + " TEXT, FOREIGN KEY(" + ITEM_TYPE_ID + ") REFERENCES " + ITEM_TYPE + "(" + ITEM_TYPE_ID +") )";
        db.execSQL(secondSQLStatement);
//        // Create Grocery List Table and List Item Table
        String thirdSQLStatement = "CREATE TABLE " + GROCERY_LIST + "(" + GL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GL_NAME + " TEXT)";
        db.execSQL(thirdSQLStatement);
        String fourthSQLStatement = "CREATE TABLE " + GL_ITEM + "(" + GL_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CHECKED_OFF + " BOOLEAN, " + QUANTITY + " TEXT, " + GL_ID+ " INTEGER, " + ITEM_ID + " INTEGER, FOREIGN KEY(" + GL_ID + ") REFERENCES " + GROCERY_LIST + "(" + GL_ID + "), FOREIGN KEY(" + ITEM_ID + ") REFERENCES " +ITEM+"("+ITEM_ID+"))";
        db.execSQL(fourthSQLStatement);

        for(int i = 0; i < itemTypes.length; i++){
            ContentValues cv = new ContentValues();
            cv.put(ITEM_TYPE_NAME, itemTypes[i]);
            db.insert(ITEM_TYPE, null, cv);
        }

        for(int i = 0; i < items.length; i++){
            ContentValues cv = new ContentValues();
            cv.put(ITEM_TYPE_ID, items[i].getItem_type_id());
            cv.put(ITEM_NAME, items[i].getItem_name());
            db.insert(ITEM, null, cv);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Method not required for this Project

    }



    public boolean addNewItemToDB(String name, int itID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ITEM_NAME, name);
        cv.put(ITEM_TYPE_ID, itID);
        long insert = db.insert(ITEM, null, cv);
        db.close();
        if(insert == -1) return false;
        return true;
    }

    public List<Item> getAllItemsFromDB(){
        List<Item> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + ITEM;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                int tyepID = cursor.getInt(1);
                String name = cursor.getString(2);
                Item it = new Item(id, tyepID, name);
                returnList.add(it);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean addItemToList(int listID, String quantity, int itemID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GL_ID, listID);
        cv.put(CHECKED_OFF, false);
        cv.put(ITEM_ID, itemID);
        cv.put(QUANTITY, quantity);
        long insert = db.insert(GL_ITEM, null, cv);
        db.close();
        if(insert == -1) return false;
        return true;
    }

    public boolean createNewList(String gl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GL_NAME, gl);
        long insert = db.insert(GROCERY_LIST, null, cv);
        db.close();
        if(insert == -1) return false;
        return true;
    }

    public List<GroceryList> getAllGroceryLists(){
        List<GroceryList> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String sqlQuery = "SELECT * FROM " + GROCERY_LIST;
            Cursor cursor = db.rawQuery(sqlQuery, null);


            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);

                    GroceryList newGL = new GroceryList(id, name);
                    returnList.add(newGL);
                } while (cursor.moveToNext());
            } //else {
//                GroceryList newGL = new GroceryList(-1, "Made it to TRY");
//                returnList.add(newGL);
//            }
            cursor.close();
            db.close();
            return returnList;
        }
        catch (Exception e){
            createNewList("First List");
            String sqlQuery = "SELECT * FROM " + GROCERY_LIST;
            Cursor cursor = db.rawQuery(sqlQuery, null);


            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);

                    GroceryList newGL = new GroceryList(id, name);
                    returnList.add(newGL);
                } while (cursor.moveToNext());
                db.close();
            }
        }
        return returnList;
    }

    public List<ListItem> getAllItemsFromGroceryList(int groceryListID){
        List<ListItem> allListItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + GL_ITEM + "," + ITEM +  " WHERE " + GL_ITEM + "." + ITEM_ID +" = " + ITEM + "." + ITEM_ID + " AND " + GL_ITEM + "." + GL_ID + " = " + groceryListID + ";" ;
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do{
                int listItemID = cursor.getInt(0);
                boolean checkedOff = cursor.getInt(1) == 1 ? true : false;
                String quantity = cursor.getString(2);
                int groceryID = cursor.getInt(3);
                int itemID = cursor.getInt(4);
                int itemTypeID = cursor.getInt(6);
                String name = cursor.getString(7);
                ListItem li = new ListItem(listItemID, groceryID, itemID, checkedOff, quantity, name, itemTypeID);
                allListItems.add(li);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        Collections.sort(allListItems, new Comparator<ListItem>() {
            @Override
            public int compare(ListItem o1, ListItem o2) {
                return o1.getItemTypeID() < (o2.getItemTypeID()) ? -1: 1;
            }
        });
        return allListItems;
    }

    public boolean renameGroceryList(int groceryListID, String newName){
        //TODO
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GL_NAME, newName);
        int update = db.update(GROCERY_LIST, cv, GL_ID + " = " + groceryListID, null);
        db.close();
        if(update < 0) return false;
        return true;
    }

    public List<ItemType> getAllItemType(){
        List<ItemType> returnList = new ArrayList<ItemType>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + ITEM_TYPE;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do{
                int ItemTypeID = cursor.getInt(0);
                String name = cursor.getString(1);
                ItemType itemType = new ItemType(ItemTypeID, name);
                returnList.add(itemType);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        returnList.add(0, new ItemType(-1, "Select ItemType"));
        return returnList;

    }

    public List<Item> getAllItemOfItemType(ItemType it){
        int id = it.getId();
        List<Item> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + ITEM + " WHERE " + ITEM_TYPE_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do{
                int itemID = cursor.getInt(0);
                int typeID = cursor.getInt(1);
                String name = cursor.getString(2);
                Item item = new Item(itemID, typeID, name);
                returnList.add(item);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean addItemTypeToDB(String itemTypeName){
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        cv.put(ITEM_TYPE_NAME, itemTypeName);
        long insert = db.insert(ITEM_TYPE, null, cv);
        db.close();
        if(insert < 0) return false;
        return true;
    }

    public boolean deleteGroceryList(int glID){
        SQLiteDatabase db = getWritableDatabase();
        if(db.delete(GROCERY_LIST, GL_ID + " = " + glID, null) > 0) {
            db.delete(GL_ITEM, GL_ID + " = " + glID, null);
            db.close();
            return true;
        }
        else {
            db.close();
            return false;
        }
    }

    public boolean deleteGroceryListItem(int gliID){
        SQLiteDatabase db = getWritableDatabase();
        int delete = db.delete(GL_ITEM, GL_ITEM_ID + " = " + gliID, null);
        db.close();
        if(delete < 0) return false;
        return true;
    }

    //TODO
    public boolean setCheckedON(int gliID){
        ContentValues cv = new ContentValues();
        cv.put(CHECKED_OFF, 1);
        SQLiteDatabase db = getWritableDatabase();
        int update = db.update(GL_ITEM, cv, GL_ITEM_ID + " = " + gliID, null);
        db.close();
        if(update < 0) return false;
        return true;
    }
    public boolean setCheckedOFF(int gliID){
        ContentValues cv = new ContentValues();
        cv.put(CHECKED_OFF, 0);
        SQLiteDatabase db = getWritableDatabase();
        int update = db.update(GL_ITEM, cv, GL_ITEM_ID + " = " + gliID, null);
        db.close();
        if(update < 0) return false;
        return true;
    }

    public boolean editQuantity(int gliID, String quantity){
        ContentValues cv = new ContentValues();
        cv.put(QUANTITY,quantity);
        SQLiteDatabase db = getWritableDatabase();
        int update = db.update(GL_ITEM, cv, GL_ITEM_ID + " = " + gliID, null);
        db.close();
        if(update < 0) return false;
        return true;
    }

    public boolean delteItemFromDB(int itemID){
        SQLiteDatabase db = getWritableDatabase();
        int delete = db.delete(ITEM, ITEM_ID + " = " + itemID, null);
        db.close();
        if(delete < 0) return false;
        return true;
    }

    public boolean deleteItemTypeFromDB(int itemTypeID){
        SQLiteDatabase db = getWritableDatabase();
        int delete = db.delete(ITEM_TYPE, ITEM_TYPE_ID + " = " + itemTypeID, null);
        db.close();
        if(delete < 0) return false;
        return true;
    }

    public String getItemTypeName(int itemID){
        String name;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + ITEM_TYPE + "." + ITEM_TYPE_NAME + " FROM " + ITEM + ", " + ITEM_TYPE + " WHERE " +
                ITEM + "." + ITEM_TYPE_ID + " = " + ITEM_TYPE + "." + ITEM_TYPE_ID + " AND " + ITEM + "." + ITEM_ID + " = " + itemID;
        Cursor c = db.rawQuery(query,null);
        if(c.getCount() != 1) name = "Undefined";
        else{
            c.moveToFirst();
            name = c.getString(0);
        }
        return name;
    }

}
