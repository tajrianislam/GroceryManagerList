package edu.qc.seclass.glm;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class GroceryListItemAdapter extends ArrayAdapter {
    private Context context;
    private List<ListItem> itemList;
    public TextView itemName;
    public TextView itemQuantity;
    public  TextView gliType;
    public CheckBox checkBox;
    private ImageButton changeQuantity;
    DataBaseHelper dbh;

    public GroceryListItemAdapter(@NonNull Context context, List<ListItem> list){
        super(context, 0, list);
        this.context = context;
        this.itemList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(this.context).inflate(R.layout.listitem, parent, false);
        final ListItem currentItemType = itemList.get(position);
        itemName = (TextView)listItem.findViewById(R.id.listItemName);
        itemQuantity = (TextView)listItem.findViewById(R.id.itemQuantity);
        gliType = (TextView)listItem.findViewById(R.id.gliType);
        checkBox = (CheckBox)listItem.findViewById(R.id.itemCheckBox);
        changeQuantity = (ImageButton)listItem.findViewById(R.id.changeQuantity);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dbh = new DataBaseHelper(context);
                    dbh.setCheckedON(currentItemType.getId());
                    dbh.close();
                }
                else{
                    dbh = new DataBaseHelper(context);
                    dbh.setCheckedOFF(currentItemType.getId());
                    dbh.close();
                }
            }

        });
        changeQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(context);
                final AlertDialog.Builder updateQuantity = new AlertDialog.Builder(context);
                updateQuantity.setTitle("Enter New Item Quantity");
                updateQuantity.setView(editText);
                updateQuantity.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(editText.getText() == null || editText.getText().toString().length() == 0){
                            Toast.makeText(context, "Quantity cannot be empty", Toast.LENGTH_SHORT).show();
                            return;

                        }
                        else {
                            dbh = new DataBaseHelper(context);
                            dbh.editQuantity(currentItemType.getId(), editText.getText().toString());
                            dbh.close();
                            Toast.makeText(context, "Quantity Updated to " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                            ((ListmanagerActivity)getContext()).finish();
                            ((ListmanagerActivity)getContext()).overridePendingTransition(0, 0);
                            ((ListmanagerActivity)getContext()).startActivity(((ListmanagerActivity)getContext()).getIntent());
                            ((ListmanagerActivity)getContext()).overridePendingTransition(0, 0);
                        }
                    }
                });
                updateQuantity.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                updateQuantity.show();

            }
        });
        dbh = new DataBaseHelper(context);
        gliType.setText(dbh.getItemTypeName(currentItemType.getItemID()));
        dbh.close();
        itemName.setText(currentItemType.getItemName());
        itemQuantity.setText(currentItemType.getQuantity());
        checkBox.setChecked(currentItemType.isCheckedOff());
        return listItem;
    }
}
