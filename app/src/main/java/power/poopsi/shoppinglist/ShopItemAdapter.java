package power.poopsi.shoppinglist;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.ArrayList;

import power.poopsi.library.listener.OnListItemClickListener;
import power.poopsi.shoppinglist.model.ShopItemEntity;
import power.poopsi.shoppinglist.model.ShopItemRepository;

/**
 * Created by ingos_000 on 06.12.2014.
 */
public class ShopItemAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<ShopItemEntity> list;

    public ShopItemAdapter(Context context) {
        list = (ArrayList<ShopItemEntity>) new ShopItemRepository().getShopItemList();
        this.context = context;
        MainActivity main = (MainActivity) context;
        SharedPreferences settings = main.getSharedPreferences("mySettings",0);
        for(int i = 0; i < list.size(); i++) {
            list.get(i).setIsChecked(settings.getBoolean(String.format("",i),false));
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MyViewHolder holder = null;
        final MainActivity main = (MainActivity) context;
        final EditText et = (EditText) main.findViewById(R.id.editText_ShopItemName);
        if (row == null) { // 1st time
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.shopitem_row_layout,parent,false);
            holder = new MyViewHolder(row);
            row.setTag(holder);
        }
        else{ // recycling
            holder = (MyViewHolder)row.getTag();
        }
//        OnListItemClickListener listener = new OnListItemClickListener(position){
//          @Override
//          public void onClick(View v) {
//
//          }
//        };

        holder.isCheckedCheckBox.setChecked(list.get(position).getIsChecked());
        holder.displayNameTextView.setText(list.get(position).getDisplayName());
        holder.removeItemButton.setOnClickListener(new OnListItemClickListener(position){
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        main.getAddShopItemButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(0,new ShopItemEntity(et.getText().toString(),false));
                et.setText("");
                notifyDataSetChanged();
            }
        });
        holder.isCheckedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked != buttonView.isChecked()) {
                    SharedPreferences settings = main.getSharedPreferences("mySettings", 0);
                    if (isChecked) {
                        settings.edit().putBoolean(String.format("", position), true).commit();
                    } else {
                        settings.edit().putBoolean(String.format("", position), false).commit();
                    }
                }
            }
        });

        return row;
    }

}
