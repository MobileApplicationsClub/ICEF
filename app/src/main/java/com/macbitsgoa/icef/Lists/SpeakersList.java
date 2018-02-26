package com.macbitsgoa.icef.Lists;

/**
 * Created by aayush on 1/1/18.
 */

@SuppressWarnings("ALL")
public class SpeakersList {
    private String name, venue, timings, description, imageurl,url;
    private double Lat, Long;

    public SpeakersList() {
    }

    public SpeakersList(String name,String url, String venue, String timings, String description, String imageurl, double Lat, double Long) {
        this.name = name;
        this.venue = venue;
        this.timings = timings;
        this.description = description;
        this.imageurl = imageurl;
        this.Lat = Lat;
        this.url=url;
        this.Long = Long;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url=url;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double Lat) {
        this.Lat = Lat;
    }

    public double getLong() {
        return Long;
    }

    public void setLong(double Long) {
        this.Long = Long;
    }

}
