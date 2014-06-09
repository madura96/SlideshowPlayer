package com.humanbooster.slideshowplayer.view.javafx;

import com.humanbooster.slideshowplayer.controller.CurrentSlideChangedListener;
import com.humanbooster.slideshowplayer.controller.SlideshowController;
import com.humanbooster.slideshowplayer.model.Slide;
import com.humanbooster.slideshowplayer.view.javafx.control.SlideView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.*;

/**
 * Gère la logique de l'application, càd :
 * crée des actions en réaction à l'interface utilisateur
 * interagit avec le modèle de données representé par Slideshow, ses sides, files, etc
 * lors du chargement du slideshow, le slide courant est le 1er slide du slideshow
 */
public class UIController implements Initializable, CurrentSlideChangedListener {

    private SlideshowController sc;


    @FXML // container FXMLLoader searches dependency between objects declared here in the controller
    // and those declared in the view loaded from the xml and created already
    private WebView webView;
    @FXML
    private TextArea debugArea;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button firstButton;
    @FXML
    private Button lastButton;
//    @FXML
//    private Button playButton;
//    @FXML
//    private Button pauseButton;
    @FXML
    private Button playPauseButton;
    @FXML
    private Button stopButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private Label statusLabel;

    @FXML
    private Label nslidesLabel;
    @FXML
    private Label indexLabel;

    //@FXML
    //private MenuItem scrollPane;
    //@FXML
    //private MenuItem anchorPane;

    @FXML
    private ListView listView;
    @FXML
    private Button showListButton;
    @FXML
    private MenuItem menuItemHelp;

    @FXML
    private Pane slidePane;

    private SlideView slideView;

//    public SlideshowController() {
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slideView = new SlideView();
        slidePane.getChildren().add(slideView);

        // TODO why sc is null in this method ? it is not created at this stage so it gives exception if accessed !!
        if(sc != null)
            nslidesLabel.setText(sc.toString());//Integer.toString(sc.getSlideShow().getNumberOfSlides()));

    } // end of Initialize


    public SlideshowController getSc() {
        return sc;
    }

    public void setSc(SlideshowController sc) {
        if(this.sc != null) {
            this.sc.removeCurrentSlideChangedListener(this);
        }
        this.sc = sc;

        if(this.sc != null && sc.getSlideShow() != null && sc.getSlideShow().getNumberOfSlides() != 0 ) {

            try {
                slideView.setSlide(sc.getCurrentSlide());
            } catch (Exception e) {
                popupExceptionDialog(e);
            }
        }

        if(this.sc != null) {
            this.sc.addCurrentSlideChangedListener(this);
        }
    }


    @FXML
    public void handleNext(ActionEvent event) {
        try {
            if(! sc.isLastSlide()) {
                sc.nextSlide();
                //statusLabel.setText(sc.getStatus() + ", index=" + sc.getCurrentSlideIndex() + ", next");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handlePrevious(ActionEvent event) {
        try {
            if(! sc.isFirstSlide()) {
                sc.previousSlide();
                //statusLabel.setText(sc.getStatus() + ", index=" + sc.getCurrentSlideIndex() + ", previous");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handlePlayPause(ActionEvent e) {
        try {
            switch(sc.getStatus()) {
                case PLAYING:
                    playPauseButton.setText("Play"); // TODO synchro on SlideshowController status changed event ->
                    // TODO: if reaches the last slide, pause and put button to Play
                    sc.pause();
                    break;
                case PAUSED:
                case STOPPED:
                    playPauseButton.setText("Pause");
                    sc.play();
                    break;
            }
            statusLabel.setText(sc.getStatus() + ", index=" + sc.getCurrentSlideIndex());
        } catch (Exception ex) {
            popupExceptionDialog(ex);
        }
    }

    @FXML
    public void handleStop(ActionEvent event) {
       try {
            sc.stop();
            playPauseButton.setText("Play");
            statusLabel.setText(sc.getStatus() + ", index=" + sc.getCurrentSlideIndex());
       } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleFirst(ActionEvent event) {
        try {
            sc.setCurrentSlide(0);
            //playPauseButton.setText("Play");
            statusLabel.setText(sc.getStatus() + ", index=" + sc.getCurrentSlideIndex());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLast(ActionEvent event) {
        try {
            sc.setCurrentSlide(sc.getSlideShow().getNumberOfSlides()-1);
            playPauseButton.setText("Play");
            sc.pause();
            statusLabel.setText(sc.getStatus() + ", index=" + sc.getCurrentSlideIndex());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void currentSlideChanged(SlideshowController source, Slide oldSlide, Slide newSlide) {
        if(newSlide != oldSlide) { //TODO this verification should be done in the controller event, not in the listeners
            Platform.runLater(() -> {
                slideView.setSlide(newSlide);

                debugArea.setText(
                        "\n"
                                + "*** Current slide index: " + source.getCurrentSlideIndex()
                                + " from " + source.getSlideShow().getNumberOfSlides() + "***"
                                + "\n"
                                + newSlide.toString()
                                + debugArea.getText()
                );
                //nslidesLabel.setText("Number of slides: " + sc.getSlideShow().getNumberOfSlides());
                if (source.isLastSlide()) { // if last slide, pause and set the user button to "Play"
                    try {
                        playPauseButton.setText("Play");
                        source.pause();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                indexLabel.setText("Slide: " + sc.getCurrentSlideIndex() + "/" + sc.getSlideShow().getNumberOfSlides());
                statusLabel.setText(sc.getStatus() + ", index=" + sc.getCurrentSlideIndex());


                // TODO : initialize by displaying the first slide displaySlide(0) : diplay all elements
                // TODO -> displaySlide(currentSlideIndex) : display all elements

            });
        }
    } // end currentSlideChanged()

    private void popupExceptionDialog(Exception e) {
        //TODO pop exception dialogue
        System.err.println(e.getMessage());
        e.printStackTrace();
    }

} // end class UIController

//    sc.currentSlideIndexProperty().addListener(new ChangeListener(){
//        @Override public void changed(ObservableValue o,Object oldVal,
//                Object newVal){
//            System.out.println("Electric bill has changed!");
//        }
//    });

    //    SlideshowController scc = new SlideshowController();
//    //getSc();
//    scc.
//    sc.Property().addListener(
//            new ChangeListener<State>() {
//        public void changed(ObservableValue ov, State oldState, State newState) {
//            if (newState == State.SUCCEEDED) {
//                //stage.setTitle(webEngine.getLocation());
//                statusLabel.setText(webView.getEngine().getLocation() + " | " + newState);
//            }
//        }
//    });


//    public void handlePlay(ActionEvent event) {
//        try {
//            sc.play();
//            statusLabel.setText("play");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void handlePause(ActionEvent event) {
//        try {
//            sc.pause();
//            statusLabel.setText("pause");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

