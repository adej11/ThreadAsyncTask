package com.addev.mythread.model;

import org.json.JSONObject;

public class Cast {
    private int id;
    private String name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private String photo;

    public Cast(JSONObject object) {
        try {
           int id = object.getInt("cast_id");
            String name = object.getString("name");
            String url_image = object.getString("profile_path");
            this.id = id;
            this.name = name;
            this.photo = url_image;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
