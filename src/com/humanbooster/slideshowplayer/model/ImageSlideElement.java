package com.humanbooster.slideshowplayer.model;

/**
 * Created by HumanBooster on 26/05/2014.
 */
public class ImageSlideElement extends SlideElementWithContentBase {
    public ImageSlideElement(double x, double y, double width, double height) {
        super(x, y, width, height);
        //System.out.println("in " + getClass().getName());
    }

    @Override
    public String getContent() { // covariance, String est un sous-type d'Object
        return (String) super.getContent();
    }

    //@Override
    public void setContent(String url) {
        super.setContent(url);
    }

//    @Override
//    public String toString() {
//        return super.toString();
//    }
}

