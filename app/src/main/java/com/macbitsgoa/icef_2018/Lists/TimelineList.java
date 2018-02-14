package com.macbitsgoa.icef_2018.Lists;

/**
 * Created by aayush on 16/1/18.
 */

public class TimelineList {

    private String name;
    private String location;
    private String time;
    private String description;
    private String image;
    private String topic;
    private String type;

    public TimelineList(){

    }

    public TimelineList(String name,String location,String time,String topic,String description,String image,String type){
        this.name=name;
        this.description=description;
        this.image=image;
        this.location=location;
        this.time=time;
        this.type=type;
        this.topic=topic;

    }



    public String getName(){
        return name;
    }

    public String getTopic(){
        return topic;
    }


    public String getDescription(){
        return description;
    }

    public String getImage(){
        return image;
    }

    public String getLocation(){
        return location;
    }

    public String getTime(){
        return time;
    }

    public String getType(){
        return type;
    }

    public void setName(String name){
        this.name=name;
    }


    public void setDescription(String description){
        this.description=description;
    }

    public void setTime(String time){
        this.time=time;
    }

    public void setTopic(String topic){
        this.topic=topic;
    }

    public void setLocation(String location){
        this.location=location;
    }

    public void setType(String type){
        this.type=type;
    }

    public void setImage(String image){
        this.image=image;
    }

}
