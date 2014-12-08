package power.poopsi.shoppinglist;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import power.poopsi.library.listener.OnListItemClickListener;
import power.poopsi.shoppinglist.helper.ShoppingListDatabaseAdapter;
import power.poopsi.shoppinglist.model.ShopItemEntity;

/**
 * Created by ingos_000 on 06.12.2014.
 */
public class ShopItemAdapter extends BaseAdapter{
    /*
        TODO
        + remove log statements
     */
    private Context context;
    private ArrayList<ShopItemEntity> list;
    private ShoppingListDatabaseAdapter adapter;

    public ShopItemAdapter(Context context) {
        adapter = new ShoppingListDatabaseAdapter(context);
        if(adapter != null) {
            list = (ArrayList<ShopItemEntity>) adapter.getAllShopItems();
        } else{
            Log.w("Runtime","ShopItemAdapter: adapter is null");
        }
        this.context = context;
        MainActivity main = (MainActivity) context;
        SharedPreferences settings = main.getSharedPreferences("mySettings",0);
        for(int i = 0; i < list.size(); i++) {
            Log.d("SharedPreferences","isChecked set to " + Boolean.toString(settings.getBoolean(Integer.toString(i), false)));
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
        MyViewHolder holder;
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
                ShopItemEntity item = list.get(position);
                if(0<adapter.deleteItem(item.get_id())){
                    list.remove(position);
                }
                else{
                    Log.e("Database","DELETE RETURNED COUNT 0");
                }
                notifyDataSetChanged();
            }
        });
        main.getAddShopItemButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(0<adapter.insertListItem(et.getText().toString(),false)){
                    list.add(0,new ShopItemEntity(-1,et.getText().toString(),false));
                }
                else {
                    Toast.makeText(context,R.string.errorAddingShopItem,Toast.LENGTH_SHORT).show();
                }
                et.setText("");
                notifyDataSetChanged();
            }
        });
        holder.isCheckedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("CheckedChanged", "onCheckedChanged called");
//                    SharedPreferences settings = main.getSharedPreferences("mySettings", 0);
                ShopItemEntity item = list.get(position);
                if(0<adapter.updateIsChecked(item.get_id(),isChecked)){
                    item.setIsChecked(isChecked);
                    Log.d("SharedPreferences", "set to "+Boolean.toString(isChecked)+" at " + Integer.toString(position));
                } else {
                    Log.e("Database","UPDATE RETURNED COUNT 0");
                }
            }
        });

        return row;
    }

}
