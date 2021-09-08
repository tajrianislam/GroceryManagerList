package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    public static final String LIST_NAME = "edu.qc.seclass.glm.LIST_NAME";
    public static final String LIST_ID = "edu.qc.seclass.glm.LIST_ID";

    Button createNewList, deleteList;
    ListView allGroceryLists;
    ArrayAdapter groceryListAdapter;
    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        createNewList = findViewById(R.id.createList);
        deleteList = findViewById(R.id.deleteList);
        ImageButton Homepage = (ImageButton)findViewById(R.id.HB);
        allGroceryLists = findViewById(R.id.listsListView);

        dbh = new DataBaseHelper(MainActivity.this);
        groceryListAdapter = new GroceryListAdapter(MainActivity.this, dbh.getAllGroceryLists());
        allGroceryLists.setAdapter(groceryListAdapter);
        dbh.close();


        createNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Add button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CreatelistActivity.class);
                startActivity(intent);
            }
        });

        Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Back to Home Page", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(MainActivity.this, MainActivity.class);
                startActivity(home);

            }
        });

        allGroceryLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GroceryList selectedGL = (GroceryList)parent.getItemAtPosition(position);
                openNewListManager(selectedGL.getName(), selectedGL.getId());
            }
        });
    }

    public void openNewListManager(String name, int id){
        Intent intent = new Intent(MainActivity.this, ListmanagerActivity.class);
        intent.putExtra(LIST_NAME, name);
        intent.putExtra(LIST_ID, id);
        startActivity(intent);
    }
    
}
