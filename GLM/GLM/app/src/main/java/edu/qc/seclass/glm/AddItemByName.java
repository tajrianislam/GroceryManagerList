package edu.qc.seclass.glm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddItemByName extends AppCompatActivity {
    Button addNewItem;
    ListView allDBItems;
    TextInputEditText searchTerm;
    DataBaseHelper dbh;
    ItemAdapter itemAdapter;
    List<Item> listToFilter;
    ImageButton HB4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_by_name);

        final Intent intent = getIntent();
        final int glID = intent.getIntExtra(MainActivity.LIST_ID, 0);

        addNewItem = (Button)findViewById(R.id.addNewItemToDB);
        allDBItems = (ListView)findViewById(R.id.lvProducts);
        searchTerm = (TextInputEditText) findViewById(R.id.etSearch);
        ImageButton Homepage = (ImageButton)findViewById(R.id.HomeButton4);
        dbh = new DataBaseHelper(AddItemByName.this);
        listToFilter = dbh.getAllItemsFromDB();
        itemAdapter = new ItemAdapter(AddItemByName.this, listToFilter);
        allDBItems.setAdapter(itemAdapter);

        searchTerm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int textLength = s.length();
                List<Item> tempArrayList = new ArrayList<>();
                for(Item it : listToFilter){
                    if(textLength <= it.getItem_name().length()){
                        if(it.getItem_name().toLowerCase().contains(s.toString().toLowerCase())){
                            tempArrayList.add(it);
                        }
                    }
                }
                itemAdapter = new ItemAdapter(AddItemByName.this, tempArrayList);
                allDBItems.setAdapter(itemAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        allDBItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Item item = (Item)parent.getItemAtPosition(position);

                AlertDialog.Builder quantityDialog = new AlertDialog.Builder(AddItemByName.this);
                quantityDialog.setTitle("Enter Quantity (eg '2 gallons', '3 lbs', '1 quart'...)");
                final EditText quant = new EditText(AddItemByName.this);
                quant.setInputType(InputType.TYPE_CLASS_TEXT);
                quantityDialog.setView(quant);
                quantityDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbh = new DataBaseHelper(AddItemByName.this);
                        dbh.addItemToList(glID, quant.getText().toString() , item.getId());
                        dbh.close();
                        Toast.makeText(AddItemByName.this, item.getItem_name() + " added to list", Toast.LENGTH_SHORT).show();
                    }
                });
                quantityDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                quantityDialog.show();
            }
        });


        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AddItemDialog addItemDialog = new AddItemDialog();
//                addItemDialog.show(getSupportFragmentManager(), "New Item Dialog");
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddItemByName.this);
                View mView = getLayoutInflater().inflate(R.layout.layout_dialog, null);
                mBuilder.setTitle("Add New Item to Database");
                final Spinner mSpinner = (Spinner)mView.findViewById(R.id.itemTypeSpinner);
                final TextView newName = (TextView)mView.findViewById(R.id.nameOfNewItem);
                dbh = new DataBaseHelper(AddItemByName.this);

                List<ItemType> tempArlst = dbh.getAllItemType();
                dbh.close();
                ArrayList<String> addToSpinner = new ArrayList<>();
                for(ItemType it:tempArlst){
                    StringBuilder sb = new StringBuilder();
                    sb.append(it.getId() + ":"+it.getName());
                    addToSpinner.add(sb.toString());
                    sb.delete(0, sb.length());
                }
                ArrayAdapter<String> itemTypeAdapter = new ArrayAdapter<>(AddItemByName.this, android.R.layout.simple_spinner_dropdown_item, addToSpinner);
                mSpinner.setAdapter(itemTypeAdapter);
                mBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String itType = (String) mSpinner.getSelectedItem();
                        if(!itType.equalsIgnoreCase("Select itemtype")){
                            itType = (String)mSpinner.getSelectedItem();
                            String nameToAdd = newName.getText().toString();
                            if(nameToAdd == null || nameToAdd.length() == 0){
                                Toast.makeText(AddItemByName.this, "Empty Name, please add an item", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else if(itType.equalsIgnoreCase("-1:Select itemtype")){
                                Toast.makeText(AddItemByName.this, "Please choose an ItemType", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            int indexOf = itType.indexOf(":");
                            dbh = new DataBaseHelper(AddItemByName.this);
                            dbh.addNewItemToDB(nameToAdd, Integer.parseInt(itType.substring(0,indexOf)));
                            dbh.close();
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });


        allDBItems.setLongClickable(true);
        allDBItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Item it = (Item)parent.getItemAtPosition(position);
                AlertDialog.Builder confirmDelete = new AlertDialog.Builder(AddItemByName.this);
                confirmDelete.setTitle("Would you like to delete this item from the Database?");
                confirmDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbh = new DataBaseHelper(AddItemByName.this);
                        dbh.delteItemFromDB(it.getId());
                        dbh.close();
                        Toast.makeText(AddItemByName.this, "Item Deleted", Toast.LENGTH_SHORT).show();
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

        Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddItemByName.this, "Back to HomePage", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(AddItemByName.this, MainActivity.class);
                startActivity(home);
            }
        });
    }


}