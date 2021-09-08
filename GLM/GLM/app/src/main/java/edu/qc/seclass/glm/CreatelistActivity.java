package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatelistActivity extends AppCompatActivity {
    Button createList;
    EditText listName;
    String newGL;
    DataBaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createlist);

        createList = findViewById(R.id.createList);
        listName = findViewById(R.id.listName);

        createList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGL = listName.getText().toString();
                if (newGL == null || newGL.length() == 0) {
                    Toast.makeText(CreatelistActivity.this, "GroceryList name cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbh = new DataBaseHelper(CreatelistActivity.this);
                dbh.createNewList(newGL);
                dbh.close();
                Intent intent = new Intent(CreatelistActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}