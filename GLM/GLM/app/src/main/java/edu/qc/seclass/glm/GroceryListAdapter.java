package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GroceryListAdapter extends ArrayAdapter<GroceryList> {
    public static final String LIST_NAME = "edu.qc.seclass.glm.LIST_NAME";
    public static final String LIST_ID = "edu.qc.seclass.glm.LIST_ID";
    private Context context;
    private List<GroceryList> gList;
    private ImageButton changeName;

    public GroceryListAdapter(@NonNull Context context, List<GroceryList> list){
        super(context, 0, list);
        this.context = context;
        this.gList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(this.context).inflate(R.layout.list, parent, false);
        final GroceryList currentList = gList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.listLabel);
        name.setText(currentList.getName());

        changeName = (ImageButton)listItem.findViewById(R.id.listRenameBtn);
        changeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRenameList(currentList.getName(), currentList.getId());
            }
        });
        return listItem;
    }

    public void openRenameList(String name, int id){
        Intent intent = new Intent(context, RenamelistsActivity.class);
        intent.putExtra(LIST_NAME, name);
        intent.putExtra(LIST_ID, id);
        context.startActivity(intent);
    }
}