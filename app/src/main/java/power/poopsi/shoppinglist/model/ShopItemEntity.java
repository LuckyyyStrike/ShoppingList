package power.poopsi.shoppinglist.model;

/**
 * Created by ingos_000 on 05.12.2014.
 */
public class ShopItemEntity extends EntityBase
implements IEntity{

    private boolean isChecked;
    private long _id;

    public ShopItemEntity() {
        this.isChecked = false;
    }
    public ShopItemEntity( long id ,String dn,boolean ic){
        this.displayName = dn;
        this.isChecked = ic;
        this._id = id;
    }

    public boolean getIsChecked() {
        return isChecked;
    }
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public long get_id(){
        return _id;
    }

    private void set_id(long id){
        _id = id;
    }

}
