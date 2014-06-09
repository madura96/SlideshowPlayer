package com.humanbooster.slideshowplayer.model;

/**
 * width et hight sont exprimés en pourcentage par rapport aux dimensions du slide parent
 * la taille 1 = 100% du slide mais elle peut être supérieure à 1
 */
public class TextSlideElement extends SlideElementWithContentBase {

    public TextSlideElement(double x, double y, double width, double height) {
        super(x, y, width, height);
        //System.out.println("in " + getClass().getName());
    }


    @Override
    public String getContent() { // covariance, String est un sous-type d'Object
        return (String) super.getContent();
    }

    @Override
    public void setContent(String text) {
        super.setContent(text);
    }

//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
