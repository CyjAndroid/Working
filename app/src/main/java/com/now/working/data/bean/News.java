package com.now.working.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Cyj on 17/12/18.
 */
@Entity
public class News {
    @Id
    private Long id;
    @Property(nameInDb = "NEWS_CONTENT")
    private String content;
    @Property(nameInDb = "NEWS_IMG_URL")
    private String imgUrl;
    @Property(nameInDb = "NEWS_VIDEO_URL")
    private String videoUrl;

    @Generated(hash = 802800153)
    public News(Long id, String content, String imgUrl, String videoUrl) {
        this.id = id;
        this.content = content;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
    }

    @Generated(hash = 1579685679)
    public News() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
