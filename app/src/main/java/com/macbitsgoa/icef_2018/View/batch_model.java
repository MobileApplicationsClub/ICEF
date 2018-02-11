package com.macbitsgoa.icef_2018.View;


public class batch_model {

    private String key, image, name, number,description,Email;

    public batch_model(String image, String name, String number, String description, String key,String Email) {
        this.image = image;
        this.name = name;
       this.number= number;
       this.description = description;
       this.key= key;
        this.Email=Email;
    }

    public batch_model() {
    }


    public String getKey() {
        return key;
    }


    public String getName() {return name;    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }


    public String getNumber(){ return number;}


    public String getEmail(){ return Email;}

}
