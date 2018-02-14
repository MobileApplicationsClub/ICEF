package com.macbitsgoa.icef.Lists;

/**
 * Created by aayush on 16/1/18.
 */

@SuppressWarnings("ALL")
public class TimelineList {

    private String name;
    private long lat;
    private long lon;
    private String time;
    private String description;
    private String image;
    private String topic;
    private String type;
    private String location;


    public TimelineList() {

    }

    public TimelineList(String name, String location, long lat, long lon, String time, String topic, String description, String image, String type) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.location = location;
        this.lat = lat;
        this.lon = lon;
        this.time = time;
        this.type = type;
        this.topic = topic;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String Location) {
        this.location = location;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public long getLon() {
        return lon;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
