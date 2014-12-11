package power.poopsi.shoppinglist.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import power.poopsi.shoppinglist.model.ShopItemEntity;

/**
 * Created by ingos_000 on 08.12.2014.
 */

    public class ShoppingListDatabaseAdapter {
    /*
        TODO
        Remove logging statements
     */
    private static long bla = 0;
    private DatabaseHelper helper;
    public ShoppingListDatabaseAdapter(Context c) {
        helper = new DatabaseHelper(c);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.ITEM_NAME, "Mandarinen");
        cv.put(DatabaseHelper.IS_CHECKED, true);
        bla = helper.getWritableDatabase().insert(DatabaseHelper.TABLE_NAME,null,cv);

    }

    public long insertListItem(String itemName, boolean isChecked) {
        long result = -1;
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.ITEM_NAME, itemName);
        cv.put(DatabaseHelper.IS_CHECKED, isChecked);
        result = helper.getWritableDatabase().insert(DatabaseHelper.TABLE_NAME,null,cv);
        Log.d("Database","INSERT "+itemName+" STATE "+Boolean.toString(isChecked)+" RESULT "+Long.toString(result));
        return result;
    }

    public List<ShopItemEntity> getAllShopItems() {
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,null,null,null,null,null,null);
        if(cursor != null) {
            List<ShopItemEntity> list = new ArrayList<ShopItemEntity>();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                boolean isChecked = cursor.getInt(2) > 0;
                list.add(new ShopItemEntity(id,name,isChecked));
                Log.d("Database", "ROW GET: " + Integer.toString(id)+" "+name+" "+Boolean.toString(isChecked));
            }
            return list;
        }
        Log.d("Database","QUERY FAILED. CURSOR IS NULL");
        return null;
    }
    public int updateIsChecked(long id, boolean isChecked) {
        int result = -1;
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.IS_CHECKED,isChecked);
        result = helper.getWritableDatabase().update(DatabaseHelper.TABLE_NAME,cv,DatabaseHelper.UID + " =?",new String[] {Long.toString(id)});
        return result;
    }

    public int deleteItem(long id) {
        int result = -1;
        result = helper.getWritableDatabase().delete(DatabaseHelper.TABLE_NAME,DatabaseHelper.UID+" =?", new String[] {Long.toString(id)});
        return result;
    }

        static class DatabaseHelper extends SQLiteOpenHelper  {
        //region Constants
        private static final String DATABASE_NAME = "ShoppingList";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_NAME = "ShoppingList";
        private static final String UID = "_id";
        private static final String IS_CHECKED = "IsChecked";
        private static final String ITEM_NAME = "ItemName";

        private static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " ("
                + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ITEM_NAME + " VARCHAR(255), "
                + IS_CHECKED + " BOOLEAN DEFAULT FALSE)";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        //endregion

        private Context context;

        public DatabaseHelper(Context c) {
            super(c, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = c;
            Log.d("Database", "DatabaseHelper constructed");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                Log.d("Database", "CREATING TABLE");
            } catch (Exception e) {
                Log.d("Database", "Error CREATING TABLE: " + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion != newVersion) {
                db.execSQL(DROP_TABLE);
                Log.d("Database", "DROPPING TABLE");
                onCreate(db);
            } else {
                Log.d("Database", "Wrong version number!");
            }
        }
    }
}
