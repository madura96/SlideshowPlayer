package com.humanbooster.slideshowplayer.model;

import javafx.scene.control.ScrollPane;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by HumanBooster on 20/05/2014.
 */
public class Slide {


    private Set<SlideElement> slideElements = new HashSet<>();

//    public Slide(HashSet<SlideElement> slideElements) {
//        this.slideElements = slideElements;
//    }

    public Set<SlideElement> getSlideElements() {
        return Collections.unmodifiableSet(slideElements); // avoid modifying it from the extern, only readable
        // return new HashSet<>(slideElements); // another way to avoid modifying the collection from extern, by giving a copy of it
    }

    /**
     * Add a {@link SlideElement} to our slide
     *
     * // TODO test to throw exception if the slideElement exist already in the slide, no doublons!
     * @param se
     * @throws java.lang.NullPointerException if the {@link SlideElement} is {@code null}
     */
    public void addSlideElement(SlideElement se) {
        Objects.requireNonNull(se); // TODO unitary test of NPE (NullPointerException)
        slideElements.add(se);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getCanonicalName())
          .append("(")
          .append(System.lineSeparator());

        for (SlideElement slideElement : slideElements) {
            sb.append("\t")
            .append(slideElement)
            .append(System.lineSeparator());
        }

        sb.append(")");
        return sb.toString();
    }
}
