package power.poopsi.shoppinglist.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import power.poopsi.shoppinglist.model.ShopItemEntity;
import power.poopsi.shoppinglist.model.ShopItemRepository;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<ShopItemEntity> ITEMS = new ShopItemRepository().getShopItemList();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, ShopItemEntity> ITEM_MAP = new HashMap<String, ShopItemEntity>();


    public static void addItem() {
        List<ShopItemEntity> list = ITEMS;
        Map<String,ShopItemEntity> map = ITEM_MAP;
//        ITEMS.add(item);
        for(int i = 0; i < list.size() ; i++){

            map.put(list.get(i).getDisplayName(), list.get(i));
        }
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
