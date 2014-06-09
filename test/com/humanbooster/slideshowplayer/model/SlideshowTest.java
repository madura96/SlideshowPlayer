package com.humanbooster.slideshowplayer.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class SlideshowTest {

    @Test
    public void oneSlideTest() throws Exception {
        // given (data initialize)
        Slideshow ss = new Slideshow();
        // when (on action)
        Slide s = new Slide();
        ss.addSlide(s);
        // then (test)
        assertTrue("Il doit y avoir un slide dans le slideshow", ss.getNumberOfSlides() == 1);
        assertTrue("L'élément présent doit être le même que l'on a ajouté",
                ss.getSlideAtIndex(0).equals(s));
    }

    @Test
    public void twoSlideTest() throws Exception {
        // given (data initialize)
        Slideshow ss = new Slideshow();
        // when (on action)
        Slide s0 = new Slide();
        ss.addSlide(s0);
        Slide s1 = new Slide();
        ss.addSlide(s1);
        // then (test)
        assertTrue("Il doit y avoir 2 slides dans le slideshow", ss.getNumberOfSlides() == 2);
        assertTrue("L'élément présent doit être le même que l'on a ajouté",
                ss.getSlideAtIndex(0).equals(s0));
        assertTrue("L'élément présent doit être le même que l'on a ajouté",
                ss.getSlideAtIndex(1).equals(s1));
    }

    @Test //(expected = SlideshowOutOfBoundsException.class) // we can do more specific SlideshowOutOfBoundException) but gonna add lot
    public void getSlideAtIndexWithNegativeIndexTest() {//throws SlideshowIndexOutOfBoundsException {
        // given (data initialize)
        Slideshow ss = new Slideshow();
        // when (on action)
        ss.getSlideAtIndex(-1);
        // then (test)
        //assertTrue("Il doit y avoir 2 slides dans le slideshow", ss.getNumberOfSlides() == 2);
     }

    @Test //(expected = SlideshowOutOfBoundsException.class) // we can do more specific SlideshowOutOfBoundException) but gonna add lot
    public void getSlideAtIndexWithTooBigIndexTest() {//throws SlideshowIndexOutOfBoundsException {
        // given (data initialize)
        Slideshow ss = new Slideshow();
        // when (on action)
        ss.getSlideAtIndex(ss.getNumberOfSlides());
        // then (test)
        //assertTrue("Il doit y avoir 2 slides dans le slideshow", ss.getNumberOfSlides() == 2);
    }

    @Test (expected = NullPointerException.class) // we can do more specific SlideshowOutOfBoundException) but gonna add lot
    public void addSlideNullTest() throws NullPointerException {
        // given (data initialize)
        Slideshow ss = new Slideshow();
        // when (on action)
        ss.addSlide(null);
        // then (test)
        //assertTrue("Il doit y avoir 2 slides dans le slideshow", ss.getNumberOfSlides() == 2);
    }

}