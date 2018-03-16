package bits.mac.icef_2018.Lists;

import android.os.Parcel;
import android.os.Parcelable;

public class GalleryFormat implements Parcelable {

    public static final Creator<GalleryFormat> CREATOR = new Creator<GalleryFormat>() {
        @Override
        public GalleryFormat createFromParcel(Parcel in) {
            return new GalleryFormat(in);
        }

        @Override
        public GalleryFormat[] newArray(int size) {
            return new GalleryFormat[size];
        }
    };
    String imageurl, storageFileName, name, key, eventKey;

    public GalleryFormat() {

    }

    public GalleryFormat(String imageurl, String storageFileName, String name, String key, String eventKey) {
        this.imageurl = imageurl;
        this.storageFileName = storageFileName;
        this.name = name;
        this.key = key;
        this.eventKey = eventKey;
    }

    public GalleryFormat(Parcel in) {
        imageurl = in.readString();
        storageFileName = in.readString();
        name = in.readString();
        key = in.readString();
        eventKey = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageurl);
        dest.writeString(storageFileName);
        dest.writeString(name);
        dest.writeString(key);
        dest.writeString(eventKey);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getStorageFileName() {
        return storageFileName;
    }

    public void setStorageFileName(String storageFileName) {
        this.storageFileName = storageFileName;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
