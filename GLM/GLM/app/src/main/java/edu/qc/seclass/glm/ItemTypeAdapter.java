package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

public class ItemTypeAdapter extends ArrayAdapter<ItemType> {
    public static final String LIST_NAME = "edu.qc.seclass.glm.LIST_NAME";
    public static final String LIST_ID = "edu.qc.seclass.glm.LIST_ID";
    private Context context;
    private List<ItemType> iTypeList;

    @Override
    public int getCount() {
        return iTypeList.size();
    }

    @Nullable
    @Override
    public ItemType getItem(int position) {
        return iTypeList.get(position);
    }

    public ItemTypeAdapter(@NonNull Context context, List<ItemType> list){
        super(context, 0, list);
        this.context = context;
        this.iTypeList = list;
    }

    public ItemTypeAdapter(Context context, int textViewID,List<ItemType> list){
        super(context, textViewID, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(this.context).inflate(R.layout.addlistitemtype, parent, false);
        final ItemType currentItemType = iTypeList.get(position);
        TextView itemTypeName = (TextView)listItem.findViewById(R.id.listItemType);
        itemTypeName.setText(currentItemType.getName());
        return listItem;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemType itemType = getItem(position);
        TextView txtTitle = (TextView)this.getView(position, convertView, parent);
        if(txtTitle == null){
            txtTitle = new TextView(context);
        }
        txtTitle.setText(itemType.getName());
        return txtTitle;
    }
}
