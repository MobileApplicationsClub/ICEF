package bits.mac.icef_2018.Lists;

import bits.mac.icef_2018.Timeline;

/**
 * Created by aayush on 16/1/18.
 */

public class TimelineList {

    private String name;
    private String location;
    private String time;
    private String description;
    private String image;

    public TimelineList(){

    }

    public TimelineList(String name,String location,String time,String description,String image){
        this.name=name;
        this.description=description;
        this.image=image;
        this.location=location;
        this.time=time;

    }



    public String getName(){
        return name;
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


    public void setName(String name){
        this.name=name;
    }


    public void setDescription(String description){
        this.description=description;
    }

    public void setTime(String time){
        this.time=time;
    }

    public void setLocation(String location){
        this.location=location;
    }


    public void setImage(String image){
        this.image=image;
    }

}
