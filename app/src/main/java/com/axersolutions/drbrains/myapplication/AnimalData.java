package com.axersolutions.drbrains.myapplication;

public class AnimalData {
    private String animal_name,animal_location;
    private String camera_name,camera_location;
    private String current_status;
    private int animal_image;

    public AnimalData(String animal_name, String animal_location, String camera_name, String camera_location, String current_status, int animal_image) {
        this.animal_name = animal_name;
        this.animal_location = animal_location;
        this.camera_name = camera_name;
        this.camera_location = camera_location;
        this.current_status = current_status;
        this.animal_image = animal_image;
    }

    public int getAnimal_image() {
        return animal_image;
    }

    public void setAnimal_image(int animal_image) {
        this.animal_image = animal_image;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    public String getAnimal_location() {
        return animal_location;
    }

    public void setAnimal_location(String animal_location) {
        this.animal_location = animal_location;
    }

    public String getCamera_name() {
        return camera_name;
    }

    public void setCamera_name(String camera_name) {
        this.camera_name = camera_name;
    }

    public String getCamera_location() {
        return camera_location;
    }

    public void setCamera_location(String camera_location) {
        this.camera_location = camera_location;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }


}
