package admin_jyb.news.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Admin-JYB on 2016/8/27.
 */

public class News {



    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    @Override
    public String toString() {
        return "News{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", newslist=" + newslist +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean implements Parcelable {
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        @Override
        public String toString() {
            return "NewslistBean{" +
                    "ctime='" + ctime + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.ctime);
            dest.writeString(this.title);
            dest.writeString(this.description);
            dest.writeString(this.picUrl);
            dest.writeString(this.url);
        }

        public NewslistBean() {
        }

        protected NewslistBean(Parcel in) {
            this.ctime = in.readString();
            this.title = in.readString();
            this.description = in.readString();
            this.picUrl = in.readString();
            this.url = in.readString();
        }

        public static final Parcelable.Creator<NewslistBean> CREATOR = new Parcelable.Creator<NewslistBean>() {
            @Override
            public NewslistBean createFromParcel(Parcel source) {
                return new NewslistBean(source);
            }

            @Override
            public NewslistBean[] newArray(int size) {
                return new NewslistBean[size];
            }
        };
    }
}
