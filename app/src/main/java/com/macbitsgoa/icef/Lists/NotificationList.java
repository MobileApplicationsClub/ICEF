package com.macbitsgoa.icef.Lists;

/**
 * Created by aayush on 11/2/18.
 */

@SuppressWarnings("ALL")
public class NotificationList {
    private String message;
    private String dt;


    public NotificationList() {

    }

    public NotificationList(String message, String dt) {
        this.message = message;
        this.dt = dt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

}
