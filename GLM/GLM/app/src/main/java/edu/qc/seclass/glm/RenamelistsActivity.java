package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RenamelistsActivity extends AppCompatActivity {

    EditText renameList;
    Button confirmChangeName;
    String newName;
    int id;
    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.renamelists);

        renameList = findViewById(R.id.renameList);
        confirmChangeName = (Button)findViewById(R.id.changeName);

        Intent intent = getIntent();
        newName = intent.getStringExtra(GroceryListAdapter.LIST_NAME);
        id = intent.getIntExtra(GroceryListAdapter.LIST_ID, 0);

        confirmChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newName = renameList.getText().toString();
                if (newName == null || newName.length() == 0) {
                    Toast.makeText(RenamelistsActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbh = new DataBaseHelper(RenamelistsActivity.this);
                dbh.renameGroceryList(id, newName);
                dbh.close();
                Intent intent = new Intent(RenamelistsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}