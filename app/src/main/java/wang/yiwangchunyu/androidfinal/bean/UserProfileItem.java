package wang.yiwangchunyu.androidfinal.bean;

public class UserProfileItem {


    private boolean isShowTopDivider;
    private String leftString;
    private String subString;


    public UserProfileItem(boolean isShowTopDivider, String leftString, String subString) {
        this.isShowTopDivider = isShowTopDivider;
        this.leftString = leftString;
        this.subString = subString;
    }

    public boolean isShowTopDivider() {
        return isShowTopDivider;
    }

    public void setShowTopDivider(boolean showTopDivider) {
        isShowTopDivider = showTopDivider;
    }

    public String getLeftString() {
        return leftString;
    }

    public void setLeftString(String leftString) {
        this.leftString = leftString;
    }

    public String getSubString() {
        return subString;
    }

    public void setSubString(String subString) {
        this.subString = subString;
    }
}
