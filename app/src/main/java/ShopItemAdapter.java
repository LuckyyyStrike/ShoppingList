import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import power.poopsi.shoppinglist.MyViewHolder;
import power.poopsi.shoppinglist.R;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MyViewHolder holder = null;
        if (row == null) { // 1st time
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.shopitem_row_layout,parent,false);
            holder = new MyViewHolder(row);
            row.setTag(holder);
        }
        else{ // recycling
            holder = (MyViewHolder)row.getTag();
        }
        holder.isCheckedCheckBox.setChecked(list.get(position).getIsChecked());
        holder.displayNameTextView.setText(list.get(position).getDisplayName());

        return row;
    }
}
