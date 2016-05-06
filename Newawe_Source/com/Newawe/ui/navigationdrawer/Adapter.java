package com.Newawe.ui.navigationdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.Newawe.C0186R;
import com.Newawe.ui.menu.MenuStructure;
import java.util.ArrayList;

public class Adapter extends ArrayAdapter<ListItem> {
    private final Context context;
    private final ArrayList<ListItem> itemsArrayList;

    public Adapter(Context context, ArrayList<ListItem> itemsArrayList) {
        super(context, C0186R.layout.list_item, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(C0186R.layout.list_item, parent, false);
        ImageView imageView = (ImageView) rowView.findViewById(C0186R.id.list_item_image);
        ((TextView) rowView.findViewById(C0186R.id.list_item_label)).setText(((ListItem) this.itemsArrayList.get(position)).getTitle());
        if (((ListItem) this.itemsArrayList.get(position)).getIconId() != MenuStructure.INVALID_RESOURCE_ID) {
            imageView.setImageResource(((ListItem) this.itemsArrayList.get(position)).getIconId());
        }
        return rowView;
    }
}
