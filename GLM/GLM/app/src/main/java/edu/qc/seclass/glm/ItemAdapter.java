package edu.qc.seclass.glm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ItemAdapter extends ArrayAdapter {
    public static final String LIST_NAME = "edu.qc.seclass.glm.LIST_NAME";
    public static final String LIST_ID = "edu.qc.seclass.glm.LIST_ID";
    private Context context;
    private List<Item> itemList;
    private List<Item> filteredItemListArray;

    public ItemAdapter(@NonNull Context context, List<Item> list){
        super(context, 0, list);
        this.context = context;
        this.itemList = list;
        this.filteredItemListArray = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(this.context).inflate(R.layout.addlistitemname, parent, false);
        final Item currentItemType = itemList.get(position);
        TextView itemTypeName = (TextView)listItem.findViewById(R.id.addListItemName);
        itemTypeName.setText(currentItemType.getItem_name());
        return listItem;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        itemList.clear();
        if(charText.length() == 0) itemList.addAll(filteredItemListArray);
        else{
            for(Item it : filteredItemListArray){
                if(it.getItem_name().contains(charText)) itemList.add(it);
            }
        }
        notifyDataSetChanged();
    }
}
