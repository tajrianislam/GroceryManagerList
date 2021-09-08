package edu.qc.seclass.glm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuItemView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListmanagerActivity extends AppCompatActivity {
    public static final String LIST_ID = "edu.qc.seclass.glm.LIST_ID";
    ListView groceryItems;
    TextView title;
    DataBaseHelper dbh;
    GroceryListItemAdapter groceryListAdapter;
    Button addItem, deleteList, addItemByName, selectAll, unselectAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmanagerpage);

        ImageButton Homepage = (ImageButton)findViewById(R.id.HB2);
        groceryItems = findViewById(R.id.itemList);
        title = findViewById(R.id.title);
        addItem = findViewById(R.id.addItem);
        deleteList = (Button) findViewById(R.id.deleteList);
        addItemByName = (Button) findViewById(R.id.addByName);
        selectAll = (Button)findViewById(R.id.selectAll);
        unselectAll = (Button)findViewById(R.id.unselectAll);
        final Intent intent = getIntent();
        String glName = intent.getStringExtra(MainActivity.LIST_NAME);
        title.setText(glName);
        final int glID = intent.getIntExtra(MainActivity.LIST_ID, 0);


        Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListmanagerActivity.this, "Back to HomePage", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(ListmanagerActivity.this, MainActivity.class);
                startActivity(home);
            }
        });


        try {
            dbh = new DataBaseHelper(ListmanagerActivity.this);
            groceryListAdapter = new GroceryListItemAdapter(ListmanagerActivity.this, dbh.getAllItemsFromGroceryList(glID));
            groceryItems.setAdapter(groceryListAdapter);
            dbh.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(ListmanagerActivity.this, "Empty list, please add an item", Toast.LENGTH_SHORT).show();
        }



        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddItemActivity(glID);
            }
        });
        deleteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder confirmDelete = new AlertDialog.Builder(ListmanagerActivity.this);
                confirmDelete.setTitle("Are you sure you would like to delete this list?");
                confirmDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbh = new DataBaseHelper(ListmanagerActivity.this);
                        dbh.deleteGroceryList(glID);
                        dbh.close();
                        Intent intent2 = new Intent(ListmanagerActivity.this, MainActivity.class);
                        startActivity(intent2);
                        Toast.makeText(ListmanagerActivity.this, "List Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                confirmDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                confirmDelete.show();
            }
        });

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh = new DataBaseHelper(ListmanagerActivity.this);
                List<ListItem> toChange = dbh.getAllItemsFromGroceryList(glID);
                for(int i = 0; i < toChange.size(); i++){
                    dbh.setCheckedON(toChange.get(i).getId());
                }
                dbh.close();
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        unselectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh = new DataBaseHelper(ListmanagerActivity.this);
                List<ListItem> toChange = dbh.getAllItemsFromGroceryList(glID);
                for(int i = 0; i < toChange.size(); i++){
                    dbh.setCheckedOFF(toChange.get(i).getId());
                }
                dbh.close();
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });


        addItemByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddItemByNameActivity(glID);
            }
        });

        groceryItems.setLongClickable(true);
        groceryItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final ListItem li = (ListItem)parent.getItemAtPosition(position);
                AlertDialog.Builder confirmDelete = new AlertDialog.Builder(ListmanagerActivity.this);
                confirmDelete.setTitle("Would you like to delete this item from the list?");
                confirmDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbh = new DataBaseHelper(ListmanagerActivity.this);
                        dbh.deleteGroceryListItem(li.getId());
                        dbh.close();
                        Toast.makeText(ListmanagerActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                });
                confirmDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                confirmDelete.show();

                return true;
            }

        });

    }
    public void openAddItemActivity(int id){
        Intent intent1 = new Intent(ListmanagerActivity.this, AdditempageActivity.class);
        intent1.putExtra(LIST_ID, id);
        startActivity(intent1);
    }

    public void openAddItemByNameActivity(int id){
        Intent intent3 = new Intent(ListmanagerActivity.this, AddItemByName.class);
        intent3.putExtra(LIST_ID, id);
        startActivity(intent3);
    }
}