package com.example.pet_pet.Model;

public class Slideiten {
    //lay url

    private int image;
    private int width;  // thêm thuộc tính width
    private int height; // thêm thuộc tính height
    public Slideiten(int image){
        this.image=image;
    }
//    public Slideiten(int image, int width, int height) {
//        this.image = image;
//        this.width = width;
//        this.height = height;
//    }
    public int getImage(){
        return image;
    }
    public int getWidth() {
        return width;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getHeight() {
        return height;
    }
}
