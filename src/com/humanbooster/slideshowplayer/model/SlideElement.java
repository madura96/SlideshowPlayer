package com.humanbooster.slideshowplayer.model;

/**
 * width et hight sont exprimés en pourcentage par rapport aux dimensions du slide parent
 * la taille 1 = 100% du slide mais elle peut être supérieure à 1
 */
public interface SlideElement {

    public double getX();

    public void setX(double x);

    public double getY();

    public void setY(double y);

    public double getWidth();

    public void setWidth(double width);

    public double getHeight();

    public void setHeight(double height);

    //public Object getContent();
    //public void setContent(Object content);
 }
