package com.axersolutions.drbrains.myapplication;

public class AnimalsCamera {

    public String animalname;
    public String cameraname;

    public AnimalsCamera(String animalname, String cameraname) {
        this.animalname = animalname;
        this.cameraname = cameraname;
    }

    public String getAnimalname() {
        return animalname;
    }

    public String getCameraname() {
        return cameraname;
    }
}
