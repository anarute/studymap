package hackathon.studymap.jdbc.model;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {

    private int postId;
    private String title;
    private String content;
    private Date instant;

    
    public int getPostId() {
        return this.postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
    public Date getInstant() {
        return this.instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }
}