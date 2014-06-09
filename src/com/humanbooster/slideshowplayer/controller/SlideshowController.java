package com.humanbooster.slideshowplayer.controller;

import com.humanbooster.slideshowplayer.model.Slide;
import com.humanbooster.slideshowplayer.model.Slideshow;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.*;


/**
 * Gère la logique de l'application, càd :
 * crée des actions en réaction à l'interface utilisateur
 * interagit avec le modèle de données representé par Slideshow, ses sides, files, etc
 * lors du chargement du slideshow, le slide courant est le 1er slide du slideshow
 */
public class SlideshowController {

    public static enum STATUS {PLAYING, PAUSED, STOPPED}
    private STATUS status = STATUS.STOPPED;

    private final int transitionTime = 1000;
    private Slideshow slideShow = null;
    private IntegerProperty currentSlideIndex = new SimpleIntegerProperty(-1); // TODO reset before each test

    private TimerTask currentTask;

    private List<CurrentSlideChangedListener> currentSlideChangedListeners = new ArrayList<>();

    public STATUS getStatus() {
        return status;
    }

    public int getCurrentSlideIndex() {
        return currentSlideIndex.get();
    }
//    public IntegerProperty currentSlideIndexProperty() {
//        return currentSlideIndex;
//    }
    public boolean isFirstSlide() {
        return (currentSlideIndex.get() == 0 ?  true : false);
    }
    public boolean isLastSlide() {
        return (currentSlideIndex.get() == slideShow.getNumberOfSlides()-1 ?  true : false);
    }

    public Slideshow getSlideShow() {
        return slideShow;
    }

    public void setSlideShow(Slideshow slideShow) {
        this.slideShow = slideShow;
        currentSlideIndex.set(0); //currentSlideIndex = 0;
    }


    /**
     * Retourne le slide suivant
     * La position du slide courant du slideshow courant est incrementée
     *
     * Si nextSlide est appelé alors qu'il n'y a pas de slideshow, on jette une exception
     * Si nextSlide est appelé alors qu'il slideshow est vide, on jette une exception
     * [ raisonnement :
     * Si pas de slideshow que fait-on ?
     * solution 1 : renvoyer null et le gérer de l'extérieur
     * solution 2 : se caler sur le comportement standard; envoyer une exception de type
     *  "peut pas faire next car il n'y a pas de slideshow"
     *  ce qui est une solution plus précise de l solution
     *  ]
     *
     * @return le slide à la nouvelle position
     */
     public Slide getCurrentSlide() throws Exception {

        if (slideShow == null) {
            // TODO jeter une exception plus spécifique
            throw new Exception("Cannot call getCurrentSlide if no slideShow has been loaded!");
        }
        if (slideShow.getNumberOfSlides() == 0) {
            // TODO jeter une exception plus spécifique
            throw new Exception("Cannot call getCurrentSlide if slideShow is empty!");
        }
        return slideShow.getSlideAtIndex(currentSlideIndex.intValue());
    }

    //miu
    /**
     * Set current side on given index
     */
    public void setCurrentSlide(int index) throws Exception {

        if (slideShow == null) {
            // TODO jeter une exception plus spécifique
            throw new Exception("Cannot call setCurrentSlide if no slideShow has been loaded!");
        }
        if (slideShow.getNumberOfSlides() == 0) {
            // TODO jeter une exception plus spécifique
            throw new Exception("Cannot call setCurrentSlide if slideShow is empty!");
        }
        if (index <0 || index >= slideShow.getNumberOfSlides()) {
            // TODO jeter une exception plus spécifique
            throw new Exception("Cannot call setCurrentSlide if index is out of range!");
        }

        Slide oldSlide = slideShow.getSlideAtIndex(currentSlideIndex.intValue());
        currentSlideIndex.set(index); //currentSlideIndex = index;
        Slide newSlide = slideShow.getSlideAtIndex(currentSlideIndex.intValue());

        // notify listeners
        for (CurrentSlideChangedListener currentSlideChangedListener : currentSlideChangedListeners) {
            currentSlideChangedListener.currentSlideChanged(this, oldSlide, newSlide);
        }
    }


    /**
     * Retourne le {@link Slide} suivant
     * La position du slide courant du slideshow courant est incrementée
     *
     * [ raisonnement :
     * Si pas de slideshow que fait-on ?
     * solution 1 : renvoyer null et le gérer de l'extérieur
     * solution 2 : se caler sur le comportement standard; envoyer une exception de type
     *  "peut pas faire next car il n'y a pas de slideshow"
     *  ce qui est une solution plus précise de l solution
     *  ]
     *
     * @return le slide à la nouvelle position
     *
     * @throws java.lang.NullPointerException if called when slideshow is null
     * @throws java.lang.NullPointerException if called when slideshow is empty
     * @throws java.lang.Exception if called when the current slide is the last one
     */
    public Slide nextSlide() throws Exception {
        // TODO verify if slideshow is != null and ! isEmpty()
        Objects.requireNonNull(slideShow, "Cannot call next() if sideshow is null");
        requireNotEmpty("Cannot call next() if sideshow is empty");
        if (currentSlideIndex.intValue() == slideShow.getNumberOfSlides() -1) { //(currentSlideIndex == slideShow.getNumberOfSlides()-1) {
            // TODO jeter une exception plus spécifique
            throw new Exception("Cannot call nextSlide if the current slide is the last one!");
        }

        Slide oldSlide = slideShow.getSlideAtIndex(currentSlideIndex.intValue());
        currentSlideIndex.set(currentSlideIndex.intValue()+1); // currentSlideIndex++; increment current slide index
        Slide newSlide = slideShow.getSlideAtIndex(currentSlideIndex.intValue());

        // notify listeners
        for (CurrentSlideChangedListener currentSlideChangedListener : currentSlideChangedListeners) {
            currentSlideChangedListener.currentSlideChanged(this, oldSlide, newSlide);
        }

        return slideShow.getSlideAtIndex(currentSlideIndex.intValue()); // return the new position
    }

    /**
     * Retourne le {@link Slide} précédent
     * La position du slide courant du slideshow courant est décrementée
     *
     * @return le slide à la nouvelle position
     *
     * @throws java.lang.NullPointerException if called when slideshow is null
     * @throws java.lang.NullPointerException if called when slideshow is empty
     * @throws java.lang.Exception if called when the current slide is the last one
     */
    public Slide previousSlide() throws Exception {
        // TODO verify if slideshow is != null and ! isEmpty()
        Objects.requireNonNull(slideShow, "Cannot call previous() if sideshow is null");
        requireNotEmpty("Cannot call previous() if sideshow is empty");
        if (currentSlideIndex.intValue() == 0) {
            // TODO jeter une exception plus spécifique
            throw new Exception("Cannot call previousSlide if the current slide is the first one!");
        }

        Slide oldSlide = slideShow.getSlideAtIndex(currentSlideIndex.intValue());
        currentSlideIndex.set(currentSlideIndex.intValue() - 1); // currentSlideIndex--; decrement current slide index
        Slide newSlide = slideShow.getSlideAtIndex(currentSlideIndex.intValue());

        // notify listeners
        for (CurrentSlideChangedListener currentSlideChangedListener : currentSlideChangedListeners) {
            currentSlideChangedListener.currentSlideChanged(this, oldSlide, newSlide);
        }

       return slideShow.getSlideAtIndex(currentSlideIndex.intValue());
    }

    public void addCurrentSlideChangedListener(CurrentSlideChangedListener listener) {
        currentSlideChangedListeners.add(listener);
    }

    public void removeCurrentSlideChangedListener(CurrentSlideChangedListener listener) {
        currentSlideChangedListeners.remove(listener);
    }

    private void requireNotEmpty(String message) throws Exception {
        if(slideShow.getNumberOfSlides() == 0) {
            throw new Exception(message);
        }
    }

    /**
     *  Change current slide every x seconds
     *  Notify listeners on each change
     *  @throws java.lang.NullPointerException if play is called while slideshow is null
     *  @throws java.lang.Exception if play is called while slideshow is empty
     */
    public void play() throws Exception {
        // TODO verify if slideshow is != null and ! isEmpty()
        Objects.requireNonNull(slideShow, "Cannot call play() if sideshow is null");
        requireNotEmpty("Cannot call play() if sideshow is empty");

        Timer t = new Timer();
        currentTask = new TimerTask() {
            @Override
            public void run() {
                if (SlideshowController.this.getSlideShow().getNumberOfSlides() - 1
                        == SlideshowController.this.currentSlideIndex.intValue()) {
                    currentTask.cancel();
                    return;
                }
                try {
                    SlideshowController.this.nextSlide(); // TODO modify nextSlide() to notify listners

                } catch (Exception e) {
                    currentTask.cancel();
                    e.printStackTrace(); // TODO log th exception if happened in the future
                }
            }
        };

        this.status = STATUS.PLAYING; // better enter mode playing before launching task
        t.schedule(currentTask, 0, transitionTime);
    }


    /**
     *  pause on current slide
     *  must be in playing mode
     *  @throws com.humanbooster.slideshowplayer.controller.SlideshowControllerStatusException
     */
    public void pause() throws SlideshowControllerStatusException {
        if (status != STATUS.PLAYING) {
            throw new SlideshowControllerStatusException("Cannot call pause if not in mode play");
        }
      currentTask.cancel();
      this.status = STATUS.PAUSED;
    }

    /**
     *  stop playing
     *  same as firstSlide
     *  @throws java.lang.NullPointerException if play is called while slideshow is null
     *  @throws java.lang.Exception if play is called while slideshow is empty
     */
    public void stop() throws Exception {
        Objects.requireNonNull(slideShow, "Cannot call stop() if sideshow is null");
        requireNotEmpty("Cannot call stop() if sideshow is empty");

        if (currentTask != null) { // if we ever did play, so task hasn't been created
            currentTask.cancel();
        }

        this.status = STATUS.STOPPED;
        this.currentSlideIndex.set(0); // = 0;
    }

}
