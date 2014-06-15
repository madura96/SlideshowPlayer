package com.humanbooster.slideshowplayer.controller;

import com.humanbooster.slideshowplayer.model.Slide;
import com.humanbooster.slideshowplayer.model.Slideshow;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class SlideshowControllerTest {
    int totalSlideChanged = 0;

    @Test
    public void nextSlideTest() throws Exception {
        // given
        Slideshow ss = new Slideshow();
        Slide s1 = new Slide();
        Slide s2 = new Slide();
        ss.addSlide(s1);
        ss.addSlide(s2);

        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);

        // when
        Slide nextSlide = sc.nextSlide();

        // then
        assertTrue("Le slide courant est bien le numéro 2", nextSlide.equals(s2));
    }

    @Test (expected = Exception.class) // signifie qu'il n'y aura pas d'erreur s'il y a ce type d'exception
    public void nextSlideIfNoSlideshowHasBeenLoadedTest() throws Exception {
        // given
        SlideshowController sc = new SlideshowController();
        // when
        sc.nextSlide();
        //then

    }

    @Test (expected = Exception.class)
    public void nextSlideIfSlideshowIsEmptyTest() throws Exception {
        // given
        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(new Slideshow());
        // when
        sc.nextSlide();
        //then

    }

    @Test (expected = Exception.class)
    public void nextSlideIfCurrentSlideIsTheLastSlideAndOnlyOneSlideTest() throws Exception {
        // given
        Slideshow ss = new Slideshow();
        Slide s1 = new Slide();
        ss.addSlide(s1);

        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);

        // when
        sc.nextSlide();

        //then
    }


    @Test
    public void getCurrentSlideTest() throws Exception {
        // given
        Slideshow ss = new Slideshow();
        Slide s1 = new Slide();
        ss.addSlide(s1);

        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);

        // when
        Slide currentSlide = sc.getCurrentSlide();

        // then
        assertTrue("Le slide courant est bien le numéro 1", currentSlide.equals(s1));
    }


    @Test (expected = Exception.class) // signifie qu'il n'y aura pas d'erreur s'il y a ce type d'exception
    public void getCurrentSlideIfNoSlideshowHasBeenLoadedTest() throws Exception {
        // given
        SlideshowController sc = new SlideshowController();
        // when
        sc.nextSlide();
        //then

    }

    @Test (expected = Exception.class)
    public void getCurrentSlideIfSlideshowIsEmptyTest() throws Exception {
        // given
        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(new Slideshow());
        // when
        sc.nextSlide();
        //then
    }

    @Test
    public void getCurrentSlideAfterNextSlideTest() throws Exception {
        // given (data initialize)
        int numberOfSlides = 10;
        Slideshow ss = new Slideshow();
        // when (on action)
        ArrayList<Slide> slides = new ArrayList<>();
        for (int i = 0; i <numberOfSlides ; i++) {
            slides.add(new Slide());
           ss.addSlide(slides.get(i));
        }
        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);

        for (int i = 1; i <sc.getSlideShow().getNumberOfSlides() ; i++) {
            // when
            Slide nextSlide = sc.nextSlide();
            Slide currentSlide = sc.getCurrentSlide();
            // then
            assertTrue("Slide at index " + i + " must be " + slides.get(i), currentSlide == slides.get(i) );
        }
    }


    @Test
    public void previousSlideTest() throws Exception {
        // given
        Slideshow ss = new Slideshow();
        Slide s1 = new Slide();
        Slide s2 = new Slide();
        ss.addSlide(s1);
        ss.addSlide(s2);

        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);

        // when
        sc.nextSlide(); // on est au 2eme slide
        Slide previousSlide = sc.previousSlide(); // on doit revenir au 1er slide

        // then
        assertTrue("Le slide courant est bien le premier", previousSlide.equals(s1));
    }

    @Test (expected = Exception.class) // signifie qu'il n'y aura pas d'erreur s'il y a ce type d'exception
    public void previousSlideIfNoSlideshowHasBeenLoadedTest() throws Exception {
        // given
        SlideshowController sc = new SlideshowController();
        // when
        sc.previousSlide();
        //then

    }

    @Test (expected = Exception.class)
    public void previousSlideIfSlideshowIsEmptyTest() throws Exception {
        // given
        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(new Slideshow());
        // when
        sc.previousSlide();
        //then

    }

    @Test (expected = Exception.class)
    public void previousSlideIfCurrentSlideIsTheFirstSlideAndOnlyOneSlideTest() throws Exception {
        // given
        Slideshow ss = new Slideshow();
        Slide s1 = new Slide();
        ss.addSlide(s1);

        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);

        // when
        sc.previousSlide();

        //then
    }

    @Test
    public void getCurrentSlideBeforePreviousSlideTest() throws Exception {
        // given (data initialize)
        int numberOfSlides = 10;
        Slideshow ss = new Slideshow();
        // when (on action)
        ArrayList<Slide> slides = new ArrayList<>();
        for (int i = 0; i <numberOfSlides ; i++) {
            slides.add(new Slide());
            ss.addSlide(slides.get(i));
        }
        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);

        sc.setCurrentSlide(sc.getSlideShow().getNumberOfSlides()-1);
        for (int i = sc.getSlideShow().getNumberOfSlides()-2; i>0 ; i--) {
            // when
            Slide previousSlide = sc.previousSlide();
            Slide currentSlide = sc.getCurrentSlide();
            // then
            assertTrue("Slide at index " + i + " must be " + slides.get(i), currentSlide == slides.get(i) );
        }
    }

    @Test
    public void playTest() throws Exception {
        // given (data initialize)
        int numberOfSlides = 10;
        Slideshow ss = new Slideshow();
        ArrayList<Slide> slides = new ArrayList<>();
        for (int i = 0; i <numberOfSlides ; i++) {
            slides.add(new Slide());
            //slides[i] = new Slide();
            ss.addSlide(slides.get(i));
        }
        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);

        // create object from interface just for the test, this is called double-test
        CurrentSlideChangedListener listener = new CurrentSlideChangedListener() {
            private int expectedCurrentSlideIndex = 0;
            @Override
            public void currentSlideChanged(SlideshowController source, Slide oldSlide, Slide newSlide) {
                expectedCurrentSlideIndex++;
                totalSlideChanged++;
                // verify if the new slide is the next slide
                assertEquals("expected next slide is " + slides.get(expectedCurrentSlideIndex),
                        slides.get(expectedCurrentSlideIndex), newSlide);
                // the equivalent line of the above live
                //slides.get(expectedCurrentSlideIndex).equals(newSlide);

                // we should not do this coz it acts on different methods that we aren't sure if they work
                //source.getCurrentSlide().equals(newSlide);

                // if we want to verify the call every x seconds, save a timestamp of precedent call,
                // then that less than x seconds + deltaTime has passed between the old and the new call
            }
        };
        sc.addCurrentSlideChangedListener(listener);

        // when
        sc.play();

        // then
        // trouver un moyen d'attendre que play() soit arrivé à la fin
        // s'il prend trop de temps (timeout) on considère qu'il a échoué
        Thread.sleep(10 * 1000 * 2); // nb_slides * frequence de changement de slids * marge d'erreur

        assertEquals("on doit avoir traversé 10 slides", 10, totalSlideChanged + 1);
    }

    @Test
    public void pauseTest() throws Exception {
        // given (data initialize)
        int numberOfSlides = 10;
        Slideshow ss = new Slideshow();
        ArrayList<Slide> slides = new ArrayList<>();
        for (int i = 0; i <numberOfSlides ; i++) {
            slides.add(new Slide());
            ss.addSlide(slides.get(i));
        }
        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);

        // create object from interface just for the test, this is called double-test
        CurrentSlideChangedListener listener = new CurrentSlideChangedListener() {
            private int expectedCurrentSlideIndex = 0;
            @Override
            public void currentSlideChanged(SlideshowController source, Slide oldSlide, Slide newSlide) {
                expectedCurrentSlideIndex++;
                totalSlideChanged++;
                // verify if the new slide is the next slide
                assertEquals("expected next slide is " + slides.get(expectedCurrentSlideIndex),
                        slides.get(expectedCurrentSlideIndex), newSlide);
              }
        };
        sc.addCurrentSlideChangedListener(listener);

        // when
        sc.play();

        // then
        // wait till play() has done some next slides
        Thread.sleep(3 * 1000 * 2); // nb_slides * frequence de changement de slids * marge d'erreur
        sc.pause();
        Slide slideAfterPause = sc.getCurrentSlide();
        // wait a little while to be sure see if the current slide is still the same
        Thread.sleep(1000);
        Slide slideAfterPauseAndSleep = sc.getCurrentSlide();

        assertEquals("slide should stay the same on pause and after a while", slideAfterPause, slideAfterPauseAndSleep);
    }

    /*
    @Test (expected = SlideshowControllerStatusException)
    public void pauseTestWithoutPlay() throws Exception {
        // given (data initialize)
        int numberOfSlides = 10;
        Slideshow ss = new Slideshow();
        ArrayList<Slide> slides = new ArrayList<>();
        for (int i = 0; i <numberOfSlides ; i++) {
            slides.add(new Slide());
            ss.addSlide(slides.get(i));
        }
        SlideshowController sc = new SlideshowController();
        sc.setSlideshow(ss);

        // create object from interface just for the test, this is called double-test
        CurrentSlideChangedListener listener = new CurrentSlideChangedListener() {
            private int expectedCurrentSlideIndex = 0;
            @Override
            public void currentSlideChanged(SlideshowController source, Slide oldSlide, Slide newSlide) {
                expectedCurrentSlideIndex++;
                totalSlideChanged++;
                // verify if the new slide is the next slide
                assertEquals("expected next slide is " + slides.get(expectedCurrentSlideIndex),
                        slides.get(expectedCurrentSlideIndex), newSlide);
            }
        };
        sc.addCurrentSlideChangedListener(listener);

        // when
        sc.play();

        // then
        // wait till play() has done some next slides
        Thread.sleep(3 * 1000 * 2); // nb_slides * frequence de changement de slids * marge d'erreur
        sc.pause();
        Slide slideAfterPause = sc.getCurrentSlide();
        // wait a little while to be sure see if the current slide is still the same
        Thread.sleep(1000);
        Slide slideAfterPauseAndSleep = sc.getCurrentSlide();

        assertEquals("the status must be PAUSED", SlideshowController.STATUS.PAUSED, sc.getStatus());
        assertEquals("slide should stay the same on pause and after a while", slideAfterPause, slideAfterPauseAndSleep);
    }
*/
    // TODO ajouter un test ou le slideshow a déja des slides au départ
    // TODO ajouter un test pour le dépassement du tableau
    // TODO tester nextSlide si trou dans l'ordre du slideshow
}