package com.humanbooster.slideshowplayer.model;

/**
 * width et hight sont exprimés en pourcentage par rapport aux dimensions du slide parent
 * la taille 1 = 100% du slide mais elle peut être supérieure à 1
 */
public class SlideElementBase implements SlideElement {
    private double x; // slideElement's upper left x coordinate on the screen
    private double y; // slideElement's upper left y coordinate on the screen
    private double width; // percentage of the slide's width
    private double height; // percentage of the slide's height

    public SlideElementBase(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //System.out.println("in " + getClass().getName());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "(x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ')';
    }
}
