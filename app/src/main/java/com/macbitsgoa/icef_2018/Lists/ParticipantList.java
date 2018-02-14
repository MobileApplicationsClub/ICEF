package com.macbitsgoa.icef_2018.Lists;

/**
 * Created by aayush on 10/2/18.
 */

@SuppressWarnings("ALL")
public class ParticipantList {
    private String name;
    private String room;
    private String id;
    private String college;

    public ParticipantList(){}

    public ParticipantList(String name,String college,String room,String id){
        this.id=id;
        this.room=room;
        this.college=college;
        this.name=name;
    }


    public String getName(){
        return name;
    }

    public String getRoom(){
        return room;
    }

    public String getID(){
        return id;
    }

    public String getCollege(){
        return college;
    }

    public void setName(String name){
        this.name=name;
    }


    public void setRoom(String room){
        this.room=room;
    }
    public void setCollege(String college){
        this.college=college;
    }
    public void setID(String id){
        this.id=id;
    }

}
