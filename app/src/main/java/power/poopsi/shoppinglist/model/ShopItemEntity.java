package power.poopsi.shoppinglist.model;

/**
 * Created by ingos_000 on 05.12.2014.
 */
public class ShopItemEntity extends EntityBase
implements IEntity{

    private boolean _isChecked;

    public ShopItemEntity() {
        _isChecked = false;
    }
    public ShopItemEntity(String dn,boolean ic) {
        this.displayName = dn;
        this._isChecked = ic;
    }

    public boolean getIsChecked() {
        return _isChecked;
    }
    public void setIsChecked(boolean isChecked) {
        _isChecked = isChecked;
    }

}
