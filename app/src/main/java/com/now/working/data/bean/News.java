package com.now.working.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Cyj on 17/12/18.
 */
@Entity
public class News implements Parcelable{
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

    protected News(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        content = in.readString();
        imgUrl = in.readString();
        videoUrl = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(content);
        dest.writeString(imgUrl);
        dest.writeString(videoUrl);
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}
