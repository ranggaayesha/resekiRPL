package android.dapoerkami.com.dapoerkami;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Afif on 14/04/2018.
 */

public class Post {
    String postId;
    private String username;
    private String photo;
    private String photoTitle;
    private String photoDesc;
    public String star;
    private int starCount = 0;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    public String getPhotoDesc() {
        return photoDesc;
    }

    public void setPhotoDesc(String photoDesc) { this.photoDesc = photoDesc; }

    public String getStar() { return star; }

    public void setStar(String star) { this.star = star; }

    public int getStarCount() { return starCount; }

    public void setStarCount(int startCount) { this.starCount = startCount; }
}