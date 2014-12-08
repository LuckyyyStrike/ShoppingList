package power.poopsi.shoppinglist;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by ingos_000 on 06.12.2014.
 */
public class MyViewHolder {
    public CheckBox isCheckedCheckBox;
    public TextView displayNameTextView;
    public Button removeItemButton;

    public MyViewHolder(View v) {
        isCheckedCheckBox = (CheckBox)v.findViewById(R.id.isChecked);
        displayNameTextView = (TextView)v.findViewById(R.id.displayName);
        removeItemButton = (Button) v.findViewById(R.id.btnRemoveItem);
    }
}
