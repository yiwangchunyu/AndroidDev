package wang.yiwangchunyu.androidfinal.bean;

import java.util.ArrayList;

public class StatusDemo extends BaseBean{
    private int id;
    private String user_id;
    private String content;
    private int like;
    private int comment;
    private int post;
    private int img_count;
    private String ctime;
    private String mtime;
    private UserDemo user;
    private ArrayList<PicUrlsDemo> pic_urls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getImg_count() {
        return img_count;
    }

    public void setImg_count(int img_count) {
        this.img_count = img_count;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public UserDemo getUser() {
        return user;
    }

    public void setUser(UserDemo user) {
        this.user = user;
    }

    public ArrayList<PicUrlsDemo> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(ArrayList<PicUrlsDemo> pic_urls) {
        this.pic_urls = pic_urls;
    }
}
