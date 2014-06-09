package com.humanbooster.slideshowplayer.model;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class SlideTest {

    // TODO write test to garantee that no slideElement in new Slide()
    @Test
    public void oneSlidelementTest() throws Exception {
        // given
        Slide slide = new Slide();
        SlideElement se = new TextSlideElement(0,0,0,0);

        // when
        slide.addSlideElement(se);

        // then
        Set<SlideElement> slideElements = slide.getSlideElements();
        assertTrue("Il doit y avoir un slideElement dans le slide", slideElements.size() == 1);
        assertTrue("l'élément dans a collection doit être celui qu'on a ajouté", slideElements.contains(se));
    }

}