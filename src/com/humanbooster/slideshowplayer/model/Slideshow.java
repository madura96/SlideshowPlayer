package com.humanbooster.slideshowplayer.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by HumanBooster on 20/05/2014.
 */
public class Slideshow {
    private double width; // in mm
    private double  height; // in mm

    private ArrayList<Slide> slides;

    /**
     * Constructor by default in predefined dimensions
     */
    public Slideshow() {
        this(297,210);
    }

    public Slideshow(int width, int height) {
        slides = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    /**
     * Ajouter un slide dans le Slideshow à la fin
     * @param slide à ajouter dans le Slideshow
     * @exception NullPointerException if the added slide is {@code null}
     */
    public void addSlide(Slide slide) {
        Objects.requireNonNull(slide, "Cannot add a null Slide"); //***** important: Objects facilitate some exceptions since Java8
//       if(slide == null){ // TODO java objects
//            throw new SlideshowNullPointerException("Slide cannot be null");
//        }
        slides.add(slide);
    }

    /**
     * Retourne le nombre de slides dans le Slideshow
     * @return int number of slides
     */
    public int getNumberOfSlides() {
        // return slides.length; // ce n'est pas la taille du tableau mais le nb de vrais slides dedans
        return  slides.size();
    }

    /**
     * Return le slide à la position index dans le Slideshow
     * @param index position du slide
     * @return Slide le slide à l'index donné
     * @exception SlideshowIndexOutOfBoundsException if the index is out of range
     */
    public Slide getSlideAtIndex(int index) {//throws SlideshowIndexOutOfBoundsException {
     // 1st solution to manage exception
        if (index<0) {
            throw new SlideshowIndexOutOfBoundsException("The index is out of range (index < 0)");
        }
        if (index>=slides.size()) {
            throw new SlideshowIndexOutOfBoundsException("The index is out of range (index > size of the list)");
        }
        return  slides.get(index);

        // 2nd solution to manage exception, using java.lang exceptions
        /*
        try {
            return  slides.get(index);
         }
        catch (IndexOutOfBoundsException e) {
            throw new SlideshowOutOfBoundsException("The index is out of range");
        }
        */
    }

 }
