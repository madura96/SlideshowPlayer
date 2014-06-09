package com.humanbooster.slideshowplayer.model;

/**
 * Created by HumanBooster on 22/05/2014.
 */
public class SlideshowIndexOutOfBoundsException extends RuntimeException { // extending to RunTimeEx pass to unchecked exception
    public SlideshowIndexOutOfBoundsException(String message) {
        super(message);
    }

    protected SlideshowIndexOutOfBoundsException(String message, Throwable cause,
                                                 boolean enableSuppression,
                                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

