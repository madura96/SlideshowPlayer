package com.humanbooster.slideshowplayer.model;

/**
 * Created by HumanBooster on 26/05/2014.
 */
public class SlideElementWithContentBase extends SlideElementBase {
    private String content;

    public  SlideElementWithContentBase(double x, double y, double width, double height) {
        super(x, y, width, height);
        //System.out.println("in " + getClass().getName());
    }
    public Object getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
