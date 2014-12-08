package power.poopsi.shoppinglist.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ingos_000 on 06.12.2014.
 */
public class ShopItemRepository {
    private List<ShopItemEntity> _shopItemList;

    public List<ShopItemEntity> getShopItemList(){
        if(_shopItemList == null) {
            _shopItemList = new ArrayList<ShopItemEntity>();
            makeBulkData();
        }
        return _shopItemList;
    }

    private void makeBulkData() {
        String[] arr = {"Mandarinen",
                "SoBluKe",
                "Kaffee",
                "Kinderriegel",
                "Orangensaft",
                "Krustenbraten"
        };

        for(int i = 0; i < arr.length ; i++) {
            ShopItemEntity peter = new ShopItemEntity(arr[i].toString(),false);
            _shopItemList.add(peter);
        }
    }
}
