package power.poopsi.library.listener;

import android.view.View;

import power.poopsi.shoppinglist.ShopItemFragment;

/**
 * Created by ingos_000 on 07.12.2014.
 */
public class OnListItemClickListener implements View.OnClickListener {
    private int position;

    public OnListItemClickListener(int p) {
        position = p;
    }
    @Override
    public void onClick(View v){}

}
