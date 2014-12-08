package power.poopsi.shoppinglist.model;

/**
 * Created by ingos_000 on 05.12.2014.
 */
public abstract class EntityBase
    implements IEntity{

    protected String displayName;
//    private boolean _isChecked;

    public EntityBase(){}

    public EntityBase(String dn){
        this.displayName = dn;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        displayName = displayName;
    }

//    @Override
//    public boolean getIsChecked() {
//        return _isChecked;
//    }
//
//    @Override
//    public void setIsChecked(boolean isChecked) {
//        _isChecked = isChecked;
//    }
}
