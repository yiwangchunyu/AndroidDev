package wang.yiwangchunyu.androidfinal.bean;

import java.util.ArrayList;

public class StatusLostFoundItem extends BaseBean {
    private long publish_id;
    private String user_id;
    private String nickName;
    private String avatarUrl;
    private String type;
    private int image_exist;
    private String submission_time;
    private String msg;
    private ArrayList<String> image_url;

    public long getPublish_id() {
        return publish_id;
    }

    public void setPublish_id(long publish_id) {
        this.publish_id = publish_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImage_exist() {
        return image_exist;
    }

    public void setImage_exist(int image_exist) {
        this.image_exist = image_exist;
    }

    public String getSubmission_time() {
        return submission_time;
    }

    public void setSubmission_time(String submission_time) {
        this.submission_time = submission_time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<String> getImage_url() {
        return image_url;
    }

    public void setImage_url(ArrayList<String> image_url) {
        this.image_url = image_url;
    }
}
