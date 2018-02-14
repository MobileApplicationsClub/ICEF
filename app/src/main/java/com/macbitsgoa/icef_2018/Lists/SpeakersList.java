package com.macbitsgoa.icef_2018.Lists;

/**
 * Created by aayush on 1/1/18.
 */

@SuppressWarnings("ALL")
public class SpeakersList {
    private String name, venue, timings, description,imageurl;
    private double Lat,Long;

    public SpeakersList(){}

    public SpeakersList(String name, String venue, String timings, String description, String imageurl, double Lat, double Long){
        this.name=name;
        this.venue=venue;
        this.timings=timings;
        this.description=description;
        this.imageurl=imageurl;
        this.Lat=Lat;
        this.Long=Long;

    }

    public String getName(){
        return name;
    }


    public String getVenue(){
        return venue;
    }

    public String getTimings(){
        return timings;
    }

    public String getDescription(){
        return description;
    }

    public String getImageurl(){
        return imageurl;
    }

    public double getLat(){
        return Lat;
    }

    public double getLong(){
        return Long;
    }

    public void setName(String name){
        this.name= name;
    }


    public void setVenue(String venue){
        this.venue= venue;
    }

    public void setTimings(String timings){
        this.timings= timings;
    }

    public void setDescription(String description){
        this.description= description;
    }

    public void setImageurl(String imageurl){
        this.imageurl= imageurl;
    }

    public void setLat(double Lat){
        this.Lat= Lat;
    }

    public void setLong(double Long){
        this.Long=Long; }

}
