package com.example.firebaseretrieve;

public class data {
    private String title;
    private String Description;
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public data(String title, String description, String image) {
        this.title = title;
        Description = description;
        this.image = image;
    }


    public data()
    {

    }
}
