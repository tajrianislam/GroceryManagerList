package edu.qc.seclass.glm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdditempageActivity extends AppCompatActivity{
    public static final String LIST_ID = "edu.qc.seclass.glm.AdditempageActivity.LIST_ID";
    DataBaseHelper dbh;
    ItemTypeAdapter allItemTypes;
    ListView lvItemTypes;
    Button addNewItemType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additempage);
        final Intent intent = getIntent();
        final int glID = intent.getIntExtra(MainActivity.LIST_ID, 0);
        ImageButton Homepage = (ImageButton)findViewById(R.id.HB3);
        addNewItemType = (Button)findViewById(R.id.createItemType);
        lvItemTypes = (ListView)findViewById(R.id.addlistItemLists);
        dbh = new DataBaseHelper(AdditempageActivity.this);
        allItemTypes = new ItemTypeAdapter(AdditempageActivity.this, dbh.getAllItemType());
        lvItemTypes.setAdapter(allItemTypes);
        dbh.close();


        lvItemTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dbh = new DataBaseHelper(AdditempageActivity.this);
                ItemType itType = (ItemType)parent.getItemAtPosition(position);
                ItemAdapter itemOfItemType = new ItemAdapter(AdditempageActivity.this, dbh.getAllItemOfItemType(itType));
                lvItemTypes.setAdapter(itemOfItemType);
                dbh.close();
                lvItemTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final Item item = (Item)parent.getItemAtPosition(position);

                        AlertDialog.Builder quantityDialog = new AlertDialog.Builder(AdditempageActivity.this);
                        quantityDialog.setTitle("Enter Quantity (eg '2 gallons', '3 lbs', '1 quart'...)");
                        final EditText quant = new EditText(AdditempageActivity.this);
                        quant.setInputType(InputType.TYPE_CLASS_TEXT);
                        quantityDialog.setView(quant);
                        quantityDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(quant.getText().toString().length() == 0){
                                    Toast.makeText(AdditempageActivity.this, "Quantity cannot be empty", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                dbh = new DataBaseHelper(AdditempageActivity.this);
                                dbh.addItemToList(glID, quant.getText().toString() , item.getId());
                                dbh.close();
                                Toast.makeText(AdditempageActivity.this, item.getItem_name() + " added to list", Toast.LENGTH_SHORT).show();
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
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
            }
        });

        Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdditempageActivity.this, "Back to HomePage", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(AdditempageActivity.this, MainActivity.class);
                startActivity(home);
            }
        });

        addNewItemType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder typeDialog = new AlertDialog.Builder(AdditempageActivity.this);
                typeDialog.setTitle("Enter new \"Item Type\" (Eg. \"Electronics\", \"Candy\",...)");
                final EditText userInput = new EditText(AdditempageActivity.this);
                userInput.setInputType(InputType.TYPE_CLASS_TEXT);
                typeDialog.setView(userInput);
                typeDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(userInput.getText().toString().length() == 0 || userInput.getText() == null){
                            Toast.makeText(AdditempageActivity.this, "ItemType cannot be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            dbh = new DataBaseHelper(AdditempageActivity.this);
                            dbh.addItemTypeToDB(userInput.getText().toString());
                            dbh.close();
                            Toast.makeText(AdditempageActivity.this, "New ItemType added to DataBase", Toast.LENGTH_SHORT).show();
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                    }
                });
                typeDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                typeDialog.show();
            }
        });

        lvItemTypes.setLongClickable(true);
        lvItemTypes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final ItemType currentIT = (ItemType)parent.getItemAtPosition(position);
                AlertDialog.Builder confirmDelete = new AlertDialog.Builder(AdditempageActivity.this);
                confirmDelete.setTitle("Would you like to delete this itemtype from the Database?");
                confirmDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbh = new DataBaseHelper(AdditempageActivity.this);
                        dbh.deleteItemTypeFromDB(currentIT.getId());
                        dbh.close();
                        Toast.makeText(AdditempageActivity.this, "ItemType Deleted", Toast.LENGTH_SHORT).show();
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
                return false;
            }
        });
    }
}